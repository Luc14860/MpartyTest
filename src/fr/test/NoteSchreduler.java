package fr.test;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class NoteSchreduler implements Runnable {
	
	private Plugin plugin;
	private Player killer;
	
	private int note = 0;
	
	public NoteSchreduler(Plugin plugin, Player killer) {
		this.plugin = plugin;
		this.killer = killer;
	}
	
	@Override
	public void run() {
		if(note < 4) {
			killer.playNote(killer.getLocation(), (byte) 0, (byte) note);
			note++;
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new NoteSchreduler(plugin, killer), 60L);
		}
	}
}
