package br.com.hevermc.lobby.score;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import br.com.hevermc.commons.bukkit.Commons;
import br.com.hevermc.commons.bukkit.account.HeverPlayer;
import br.com.hevermc.commons.bukkit.account.loader.PlayerLoader;
import br.com.hevermc.commons.enums.Tags;
import br.com.hevermc.lobby.Lobby;

public class ScoreboardManager {

	int i = 0;

	public String effect() {
		String cor1 = "�6�l";
		String cor2 = "�e�l";
		String cor3 = "�f�l";
		if (i == 0) {
			i++;
			return cor1 + "LOBBY";
		}
		if (i == 1) {
			i++;
			return cor2 + "L" + cor1 + "OBBY";
		}
		if (i == 2) {
			i++;
			return cor3 + "L" + cor2 + "O" + cor1 + "BBY";
		}
		if (i == 3) {
			i++;
			return cor3 + "LO" + cor2 + "B" + cor1 + "BY";
		}
		if (i == 4) {
			i++;
			return cor3 + "LOB" + cor2 + "B" + cor1 + "Y";
		}
		if (i == 5) {
			i++;
			return cor3 + "LOBB" + cor2 + "Y";
		}
		if (i == 6) {
			i++;
			return cor3 + "LOBBY";
		}
		if (i == 7) {
			i++;
			return cor3 + "LOBB" + cor2 + "Y";
		}
		if (i == 8) {
			i++;
			return cor3 + "LOB" + cor2 + "B" + cor1 + "Y";
		}

		if (i == 9) {
			i++;
			return cor3 + "LO" + cor2 + "B" + cor1 + "BY";
		}
		if (i == 10) {
			i++;
			return cor3 + "L" + cor2 + "O" + cor1 + "BBY";
		}
		if (i == 11) {
			i++;
			return cor2 + "L" + cor1 + "OBBY";
		}
		if (i == 12) {
			i = 0;
			return cor1 + "LOBBY";
		}
		return cor1 + "LOBBY";
	}

	public void build(Player p) {
		Scoreboard score = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = score.registerNewObjective("lobby", "score");

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("�6�lHEVER�f�lMC");
		LineAdder add = new LineAdder(score, obj);

		add.addLine("  ", "�f              ", " ", 9);
		add.addLine(" ", "�fGrupo: ", "", 8);
		add.addLine(" ", "�fRank: ", "", 7);
		add.addLine("  ", "�f       �a   ", " ", 6);
		add.addLine(" ", "�fCash: ", "", 5);
		add.addLine(" ", "�fExperi�ncia: ", "", 4);
		add.addLine(" �a ", "�f       �b   ", " ", 3);
		add.addLine(" ", "�fOnline: ", "", 2);
		add.addLine(" ", "�fLobby: ", "�e#1", 1);
		add.addLine("  ", " �a  ", "", 0);
		add.addLine("�awww.", "hevermc", ".com.br ", -1);

		p.setScoreboard(score);

		new BukkitRunnable() {

			@Override
			public void run() {
				if (p == null || !p.isOnline()) {
					cancel();
					return;
				}
				
				obj.setDisplayName(effect());
				updateInfos(p);
			}
		}.runTaskTimer(Lobby.getInstance(), 0, 3);
	}
	
	public void updateInfos(Player p) {
		HeverPlayer hp = new PlayerLoader(p).load().getHP();
		Scoreboard score = p.getScoreboard();
		
		Team group = score.getTeam("line8");
		group.setSuffix(Tags.getTags(hp.getGroup()).getColor() + hp.getGroup().getName());
		
		Team rank = score.getTeam("line7");
		rank.setSuffix(hp.getRank().getColor() + hp.getRank().toString());

		Team cash = score.getTeam("line5");
		cash.setSuffix("�3" + hp.getCash());
		
		Team xp = score.getTeam("line4");
		xp.setSuffix("�a" + hp.getXp());

		Team online = score.getTeam("line2");
		Commons.getManager().getBungeeChannel().getPlayerCount("ALL").whenComplete((result, error) -> {
			online.setSuffix("�a" + result.intValue());
		});
		
	}

}
