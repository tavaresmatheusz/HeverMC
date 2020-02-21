package br.com.hevermc.commons.bungee.command;

import java.util.HashSet;
import java.util.Set;

import br.com.hevermc.commons.bungee.Commons;
import br.com.hevermc.commons.bungee.command.common.HeverCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;

public class ReplyCommand extends HeverCommand implements TabExecutor {

	public ReplyCommand() {
		super("reply", "r");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (isPlayer(sender)) {
			ProxiedPlayer p = toPlayer(sender);
			if (args.length == 0) {
				p.sendMessage(TextComponent.fromLegacyText("�aVoc� deve usar �e/r <mensagem>"));
			} else {
				if (!Commons.getManager().reply.containsKey(p)) {
					p.sendMessage(TextComponent.fromLegacyText("�cN�o h� mensagens para voc� responder."));
				} else {
					ProxiedPlayer t = Commons.getManager().reply.get(p);
					if (t == null) {
						p.sendMessage(TextComponent.fromLegacyText("�cEste jogador est� offline."));
					} else {
						String message;
						StringBuilder sb = new StringBuilder();
						for (int i = 1; i < args.length; i++)
							sb.append(args[i]).append(" ");
						message = sb.toString();
						p.sendMessage(TextComponent
								.fromLegacyText("�7[�e" + p.getName() + " �f� �e" + t.getName() + "�7] " + message));
						t.sendMessage(TextComponent
								.fromLegacyText("�7[�e" + p.getName() + " �f� �e" + t.getName() + "�7] " + message));
						Commons.getManager().reply.put(t, p);
					}
				}
			}
		}

	}

	public Iterable<String> onTabComplete(final CommandSender cs, final String[] args) {
		Set<String> match = new HashSet<String>();
		if (args.length == 1) {
			String search = args[0].toLowerCase();
			for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
				if (player.getName().toLowerCase().startsWith(search)) {
					match.add(player.getName());
				}
			}
		}
		return match;
	}

}