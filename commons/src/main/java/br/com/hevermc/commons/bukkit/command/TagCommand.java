package br.com.hevermc.commons.bukkit.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.hevermc.commons.bukkit.account.HeverPlayer;
import br.com.hevermc.commons.bukkit.command.commons.HeverCommand;
import br.com.hevermc.commons.enums.Groups;
import br.com.hevermc.commons.enums.Tags;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TagCommand extends HeverCommand {

	public TagCommand() {
		super("tag");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (isPlayer(sender)) {
			Player p = toPlayer(sender);
			HeverPlayer hp = toHeverPlayer(p);
			if (args.length == 0) {
				TextComponent a = new TextComponent("§3§lTAGS §fSuas tags disponíveis são as seguintes: ");
				a.setUnderlined(false);
				for (Tags tags : Tags.values()) {
					if (hp.groupIsLarger(tags.getGroup())) {
						if (hp.getGroup() == tags.getGroup()) {
							TextComponent msg_a = new TextComponent(tags.getColor() + "§l" + tags.toString() + "§f");
							msg_a.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tag " + tags.toString()));
							msg_a.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
									new ComponentBuilder(("§7Exemplo: " + tags.getPrefix() + " " + tags.getColor()
											+ p.getName() + " " + hp.getSuffix())).create()));
							a.addExtra(msg_a);
							continue;
						}
						if (tags.isExclusive()) {
							if (hp.groupIsLarger(Groups.GERENTE) || hp.getGroup() == tags.getGroup()) {
								TextComponent msg_a = new TextComponent(
										tags.getColor() + "§l" + tags.toString() + "§f, ");
								msg_a.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tag " + tags.toString()));
								msg_a.setHoverEvent(
										new HoverEvent(HoverEvent.Action.SHOW_TEXT,
												new ComponentBuilder("§7Exemplo: " + tags.getPrefix() + " "
														+ tags.getColor() + p.getName() + " " + hp.getSuffix())
																.create()));
								a.addExtra(msg_a);
							}
						} else {
							TextComponent msg_a = new TextComponent(tags.getColor() + "§l" + tags.toString() + "§f, ");
							msg_a.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tag " + tags.toString()));
							msg_a.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
									new ComponentBuilder("§7Exemplo: " + tags.getPrefix() + " " + tags.getColor()
											+ p.getName() + " " + hp.getSuffix()).create()));
							a.addExtra(msg_a);
						}
					}
				}
				p.spigot().sendMessage(a);
			} else {
				Tags tag = Tags.getTags(Groups.getGroup(args[0]));
				if (tag == null) {
					p.sendMessage("§3§lTAGS §fEsta tag §4§lNÃO §fexiste!");
				} else if (tag.isExclusive()
						&& !(hp.groupIsLarger(Groups.GERENTE) || hp.groupIsLarger(tag.getGroup()))) {
					p.sendMessage("§3§lTAGS §fEsta tag é §c§lEXCLUSIVA§f!");
				} else if (hp.getTag() == tag) {
					p.sendMessage("§3§lTAGS §fVocê já está §a§lUTILIZANDO §festa tag!");
				} else if (hasGroup(p, tag.getGroup(), true)) {
					toHeverPlayer(p).setTag(tag);
					p.sendMessage("§3§lTAGS §fVocê §a§lALTEROU§F sua tag para " + tag.getColor()
							+ tag.getGroup().getName() + "§a!");
				}
			}
		}
		return false;
	}

}
