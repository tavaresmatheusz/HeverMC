package br.com.hevermc.lobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.hevermc.lobby.command.commons.loader.CommandLoader;
import br.com.hevermc.lobby.listeners.GeneralListener;
import lombok.Getter;

public class Lobby extends JavaPlugin {

	@Getter
	public static Manager manager = new Manager();
	@Getter
	public static Lobby instance;

	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(new GeneralListener(), this);
		new CommandLoader();
		getManager().setup();
		super.onEnable();
	}

}