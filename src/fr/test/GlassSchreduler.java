package fr.test;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class GlassSchreduler implements Runnable {
	
	private Plugin plugin;
	
	public GlassSchreduler(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < Test.glassBlocks.size(); i++) {
			if(Test.glassBlocks.get(i).getData() < 15) {
				Test.glassBlocks.get(i).setData((byte) (Test.glassBlocks.get(i).getData() + 1));
			} else {
				Test.glassBlocks.get(i).setData((byte) 0);
			}
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this, 4L);
	}
}
