package fr.test;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
	
	private Test plugin;
	
	public EventListener(Test plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ScoreboardManager.set(player);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		
		if(event.getLine(0).equalsIgnoreCase("421")) {
			if(event.getLine(1).equalsIgnoreCase("421")) {
				if(event.getLine(2).equalsIgnoreCase("421")) {
					if(event.getLine(3).equalsIgnoreCase("421")) {
						String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
						
						event.setLine(0, Character.toString(chars.charAt(new Random().nextInt(26))));
						event.setLine(1, "");
						event.setLine(2, "");
						event.setLine(3, "");
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new SignSchreduler(player), 200L);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() != null) {
			if((event.getClickedBlock().getType() == Material.STAINED_GLASS) && (event.getClickedBlock().getData() == ((byte) 4))) {
				if(!Test.glassBlocks.contains(event.getClickedBlock())) {
					Test.glassBlocks.add(event.getClickedBlock());
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new GlassSchreduler(plugin), 4L);
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getBlock().getType() == Material.STAINED_GLASS) {
			int i = 0;
			
			while(i < Test.glassBlocks.size()) {
				if(Test.glassBlocks.get(i).getLocation().getWorld().getName().equalsIgnoreCase(event.getBlock().getLocation().getWorld().getName())) {
					if(Test.glassBlocks.get(i).getLocation().getX() == event.getBlock().getLocation().getX()) {
						if(Test.glassBlocks.get(i).getLocation().getY() == event.getBlock().getLocation().getY()) {
							if(Test.glassBlocks.get(i).getLocation().getZ() == event.getBlock().getLocation().getZ()) {
								Test.glassBlocks.remove(i);
							}
						}
					}
				}
				
				i++;
			}
			
			i = 0;
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDeath(EntityDeathEvent event) {
		Player killer = event.getEntity().getKiller();
		
		if(killer != null) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new NoteSchreduler(plugin, killer), 60L);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(event.getMessage().equalsIgnoreCase("infos")) {
			event.setCancelled(true);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getInfosReponse()));
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		
		if(player.getItemInHand().getType() == Material.SADDLE) {
			if(Test.ride.contains(player)) {
				Test.ride.remove(player);
				event.getRightClicked().setPassenger(player);
			}
		}
	}
}
