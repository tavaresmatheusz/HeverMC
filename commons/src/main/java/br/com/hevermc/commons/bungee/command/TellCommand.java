package br.com.hevermc.commons.bungee.command;

import java.util.HashSet;
import java.util.Set;

import br.com.hevermc.commons.bungee.Commons;
import br.com.hevermc.commons.bungee.account.HeverPlayer;
import br.com.hevermc.commons.bungee.command.common.HeverCommand;
import br.com.hevermc.commons.enums.Groups;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;

public class TellCommand extends HeverCommand implements TabExecutor {

	public TellCommand() {
		super("tell", "msg");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (isPlayer(sender)) {
			ProxiedPlayer p = toPlayer(sender);
			if (args.length < 2) {
				p.sendMessage(TextComponent.fromLegacyText("§e§lTELL §fVocê deve utilizar §b/tell <alvo> <mensagem>"));
			} else {
				ProxiedPlayer t = Commons.getInstance().getProxy().getPlayer(args[0]);
				if (t == null) {
					p.sendMessage(TextComponent.fromLegacyText("§e§lTELL §fEsse jogador está §4§lOFFLINE§f!"));
				} else if (p.getName().equalsIgnoreCase(args[0])) {
					p.sendMessage(TextComponent.fromLegacyText("§e§lTELL §fVocê não pode enviar mensagem privada para §c§lVOCÊ MESMOf!"));
				} else {
					String message;
					StringBuilder sb = new StringBuilder();
					for (int i = 1; i < args.length; i++)
						sb.append(args[i]).append(" ");
					message = sb.toString();
					if (message.contains(".com") ||message.contains(". com") || message.contains(".cc") || message.contains(".tk")) {
						Commons.getInstance().getProxy().getPlayers().forEach(ps -> {
							HeverPlayer hp = toHeverPlayer(ps);
							if (hp.groupIsLarger(Groups.MOD)) {
								ps.sendMessage(TextComponent.fromLegacyText("§7§o[O jogador " + p.getName()
										+ " tentou enviar \"" + message + "\" para " + t.getName() + "]"));
							}
						});
						return;
					}
					p.sendMessage(TextComponent
							.fromLegacyText("§7[§e" + p.getName() + " §f» §e" + t.getName() + "§7] " + message));
					t.sendMessage(TextComponent
							.fromLegacyText("§7[§e" + p.getName() + " §f» §e" + t.getName() + "§7] " + message));
					Commons.getManager().reply.put(t, p);
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
