package br.com.hevermc.lobby.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import br.com.hevermc.commons.bukkit.Commons;
import br.com.hevermc.commons.bukkit.api.ItemConstructor;

public class Servers {

	public Servers(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "�a�lServers");
		inv.setItem(11,
				new ItemConstructor(new ItemStack(Material.DIAMOND_SWORD), "�aKitPvP",
						Arrays.asList("", " �fModo �a�lKITPVP �fcom �6�lSIMULATOR�f, ",
								" �ftreine suas habilidade com nossa warp �c�lLAVA�F!", " ",
								"�3" + Commons.getManager().getBackend().getRedis().get("kitpvp").replace("on:", "") + " �fjogando agora!")).create());

		inv.setItem(13,
				new ItemConstructor(new ItemStack(Material.IRON_FENCE), "�cGladiator",
						Arrays.asList(" ", " �fModo �a�lGLADIATOR�f, este modo ",
								" �fest� em �c�lDESENVOLVIMENTO�f, aguarde para jogar.", " ")).create());
		inv.setItem(15,
				new ItemConstructor(new ItemStack(Material.MUSHROOM_SOUP), "�cDoubleKit",
						Arrays.asList(" ", " �fModo �a�lDOUBLEKIT�f, este modo ",
								" �fest� em �c�lDESENVOLVIMENTO�f, aguarde para jogar.", " ")).create());

		p.openInventory(inv);
	}

}
