package br.com.hevermc.commons.bungee.account.loader;

import br.com.hevermc.commons.bungee.Commons;
import br.com.hevermc.commons.bungee.account.HeverPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerLoader {
	
	public static HeverPlayer getHP(ProxiedPlayer p) {
		if (!Commons.getManager().heverplayer.containsKey(p.getName().toLowerCase()) && p != null) {
			Commons.getManager().heverplayer.put(p.getName().toLowerCase(),
					new HeverPlayer(p.getName().toLowerCase(), p.getAddress().getHostName()));
			Commons.getManager().heverplayer.get(p.getName().toLowerCase()).load();
		}
		if (Commons.getManager().getBackend().getRedis().contains(p.getName().toLowerCase() + ":updateBungee")) {
			Commons.getManager().heverplayer.get(p.getName().toLowerCase()).load();
			Commons.getManager().getBackend().getRedis().del(p.getName().toLowerCase() + ":updateBungee");
		}
		return Commons.getManager().heverplayer.get(p.getName().toLowerCase());
	}

	public static HeverPlayer getHP(String name) {
		if (!Commons.getManager().heverplayer.containsKey(name.toLowerCase()) && name != null) {
			Commons.getManager().heverplayer.put(name.toLowerCase(), new HeverPlayer(name.toLowerCase(), "0.0.0.0"));
			Commons.getManager().heverplayer.get(name.toLowerCase()).load();
		}
		if (Commons.getManager().getBackend().getRedis().contains(name.toLowerCase() + ":updateBungee")) {
			Commons.getManager().heverplayer.get(name.toLowerCase()).load();
			Commons.getManager().getBackend().getRedis().del(name.toLowerCase() + ":updateBungee");
		}
		return Commons.getManager().heverplayer.get(name.toLowerCase());
	}
	
	public static void forceLoadAccount(HeverPlayer hp) {
		hp.load();
	}

	public static void unload(String name) {
		if (Commons.getManager().heverplayer.containsKey(name.toLowerCase()) && name != null) {
			Commons.getManager().heverplayer.get(name.toLowerCase()).quit();
			Commons.getManager().heverplayer.remove(name.toLowerCase());
		}
	}

}
