package fr.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {
	
	private static Scoreboard scoreboard;
	private static Objective objective;
	
	public static void init() {
		// J'ai remplacé getNewScoreboard() par getMainScoreboard() :D
		scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		objective = scoreboard.registerNewObjective("scoreboard", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("Bienvenue");
		objective.getScore(Bukkit.getOfflinePlayer(ChatColor.AQUA + "sur MParty")).setScore(1);
	}
	
	public static void set(Player player) {
		player.setScoreboard(scoreboard);
	}
}
