package br.com.hevermc.lobby.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import br.com.hevermc.commons.bukkit.account.HeverPlayer;
import br.com.hevermc.commons.bukkit.account.loader.PlayerLoader;
import br.com.hevermc.commons.bukkit.api.ItemConstructor;
import br.com.hevermc.commons.enums.Groups;
import br.com.hevermc.commons.enums.Tags;

public class Profile {

	public Profile(Player p) {
		Inventory inv = Bukkit.createInventory(null, 4 * 9, "�a" + p.getName());
		HeverPlayer hp = PlayerLoader.getHP(p.getName());
		@SuppressWarnings("deprecation")
		ItemStack skull = new ItemStack(397, 1, (short) 3);
		SkullMeta skullm = (SkullMeta) skull.getItemMeta();
		skullm.setOwner(p.getName());
		skullm.setDisplayName((hp.getGroup() == Groups.MEMBRO ? "" : Tags.getTags(hp.getGroup()).getPrefix() + " ") + Tags.getTags(hp.getGroup()).getColor()
				+ p.getName() + hp.getSuffix());
		skull.setItemMeta((ItemMeta) skullm);

		inv.setItem(13, skull);
		inv.setItem(19,
				new ItemConstructor(new ItemStack(Material.DIAMOND_SWORD), "�a�lKitPvP",
						Arrays.asList(" ", "�fSuas estat�sticas nesse modo de jogo s�o: ", " ",
								" �aKills: �2" + hp.getPvp_kills() + " ", " �cDeaths: �c" + hp.getPvp_deaths() + " ",
								" �eKillStreak: �6" + hp.getPvp_ks() + " ", " ", "�apvp.nestymc.com.br")).create());
		inv.setItem(20,
				new ItemConstructor(new ItemStack(Material.MUSHROOM_SOUP), "�a�lHardcoreGames",
						Arrays.asList(" ", "�fSuas estat�sticas nesse modo de jogo s�o: ", " ",
								" �aKills: �2" + hp.getHg_kills() + " ", " �cDeaths: �c" + hp.getHg_deaths() + " ",
								" �eWins: �6" + hp.getHg_wins() + " ", " ", "�ahg.nestymc.com.br")).create());
		inv.setItem(25,
				new ItemConstructor(new ItemStack(Material.BOOK), "�e�lInforma��es sobre sua conta",
						Arrays.asList(" ", "�fTodas informa��es sobre sua conta: ", " ",
								" �fGrupo: " + Tags.getTags(hp.getGroup()).getColor() + hp.getGroup().getName(),
								" �fRank: " + hp.getRank().getColor() + hp.getRank().toString(), " ",
								" �fCash: �3" + hp.getCash(), " �fXp: �e" + hp.getXp(), " ")).create());

		p.openInventory(inv);
	}
}
