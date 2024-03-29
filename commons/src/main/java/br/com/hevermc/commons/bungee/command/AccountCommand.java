package br.com.hevermc.commons.bungee.command;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import br.com.hevermc.commons.bungee.DateUtil;
import br.com.hevermc.commons.bungee.account.HeverPlayer;
import br.com.hevermc.commons.bungee.command.common.HeverCommand;
import br.com.hevermc.commons.enums.Groups;
import br.com.hevermc.commons.enums.Tags;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;

public class AccountCommand extends HeverCommand implements TabExecutor {

	public AccountCommand() {
		super("account");
	}

	public String conta(HeverPlayer hp) {
		return "§f\n§f\n§eNick: §f" + hp.getName() + "\n§eGrupo: " + Tags.getTags(hp.getGroup()).getColor()
				+ hp.getGroup().getName() + "\n§eDuração: "
				+ (hp.getGroupExpireIn() > 0 ? DateUtil.formatDifference(hp.getGroupExpireIn()) : "ETERNO")
				+ "\n\n§eRank: " + hp.getRank().getColor() + hp.getRank().getName() + "\n§eXP: " + hp.getXp()
				+ "\n§eUltimo Login: " + DateUtil.getDate(new Date(hp.getLastLogin())) + "\n§ePrimeiro Login: "
				+ DateUtil.getDate(new Date(hp.getFirstLogin())) + "\n§f";
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			if (requiredGroup(player, Groups.MODPLUS, true)) {
				if (args.length == 0) {
					sender.sendMessage(TextComponent.fromLegacyText(conta(toHeverPlayer(player))));
				} else if (args.length >= 1) {
					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage(TextComponent.fromLegacyText("§cEste jogador se encontra offline!"));
						return;
					}
					sender.sendMessage(TextComponent.fromLegacyText(conta(toHeverPlayer(target))));
				}
			}
		}
	}

	public Iterable<String> onTabComplete(CommandSender cs, String[] args) {
		if ((args.length > 2) || (args.length == 0)) {
			return ImmutableSet.of();
		}
		Set<String> match = new HashSet<>();
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
