package br.com.hevermc.commons.bungee.command;

import java.util.HashSet;
import java.util.Set;

import br.com.hevermc.commons.bungee.Commons;
import br.com.hevermc.commons.bungee.command.common.HeverCommand;
import br.com.hevermc.commons.enums.Groups;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;

public class FindCommand extends HeverCommand implements TabExecutor {

	public FindCommand() {
		super("find");
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (requiredGroup(p, Groups.TRIAL, true)) {
				if (args.length == 0) {
					p.sendMessage(TextComponent.fromLegacyText("§c§lFIND §fVocê deve utilizar §b/find <player>"));
				} else {
					ProxiedPlayer target = Commons.getInstance().getProxy().getPlayer(args[0]);
					if (target == null) {
						p.sendMessage(TextComponent.fromLegacyText("§c§lFIND §fSeu alvo está §4§lOFFLINE§f!"));
					} else {

						p.sendMessage(TextComponent.fromLegacyText("§c§lFIND §fO jogador §b§l" + target.getName()
								+ " §ffoi localizado em §b§l" + target.getServer().getInfo().getName().toUpperCase() + "§f!"));
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
