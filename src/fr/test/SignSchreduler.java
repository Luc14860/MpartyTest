package fr.test;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class SignSchreduler implements Runnable {
	
	private Player player;
	
	public SignSchreduler(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
		FireworkEffect effects = FireworkEffect.builder().withColor(Color.RED).withColor(Color.BLUE).with(Type.STAR).build();
		FireworkMeta fireworkMeta = firework.getFireworkMeta();
		fireworkMeta.addEffect(effects);
		firework.setFireworkMeta(fireworkMeta);
	}
}
