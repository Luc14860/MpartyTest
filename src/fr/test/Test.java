package fr.test;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin {
	
	public final static Logger logger = Logger.getLogger("Minecraft");
	
	public static List<Block> glassBlocks = new LinkedList();
	public static List<String> ride = new LinkedList();
	
	public void onEnable() {
		logger.info("Starting Test...");
		
		Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
		
		ScoreboardManager.init();
		
		saveDefaultConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("ride")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("ride.command")) {
					sender.sendMessage(ChatColor.BLUE + "Vous pourrez monter sur la prochaine créature !");
					ride.add(((Player) sender).getDisplayName());
				}
			}
		}
		
		return false;
	}
	
	public void onDisable() {
		logger.info("Stopping Test...");
	}
	
	public String getInfosReponse() {
		return getConfig().getString("infos");
	}
}
