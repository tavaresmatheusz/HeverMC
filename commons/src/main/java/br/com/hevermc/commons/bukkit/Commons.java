package br.com.hevermc.commons.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.hevermc.commons.bukkit.command.commons.loader.CommandLoader;
import br.com.hevermc.commons.bukkit.listeners.GeneralListeners;
import lombok.Getter;

public class Commons extends JavaPlugin {

	@Getter
	public static Manager manager = new Manager();
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new GeneralListeners(), this);
		new CommandLoader();
		getManager().setup();
		super.onEnable();
	}
	
}
