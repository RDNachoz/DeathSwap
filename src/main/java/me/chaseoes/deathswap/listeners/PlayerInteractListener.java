package me.chaseoes.deathswap.listeners;

import me.chaseoes.deathswap.DeathSwap;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.hasBlock()) {
			if (event.getClickedBlock().getType() == Material.WALL_SIGN || event.getClickedBlock().getType() == Material.SIGN || event.getClickedBlock().getType() == Material.SIGN_POST) {
				Sign s = (Sign) event.getClickedBlock().getState();
				if (s.getLine(1).equalsIgnoreCase("Click to play:")) {
					String map = s.getLine(2).replace("Map: ", "");
					if (DeathSwap.getInstance().games.containsKey(map)) {
						event.getPlayer().performCommand("ds join " + map);
						event.setCancelled(true);
					}
				}
			} 
		}
	}

}
