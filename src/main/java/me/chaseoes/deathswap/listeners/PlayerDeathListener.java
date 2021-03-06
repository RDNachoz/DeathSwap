package me.chaseoes.deathswap.listeners;

import me.chaseoes.deathswap.DSGame;
import me.chaseoes.deathswap.DeathSwap;
import me.chaseoes.deathswap.metadata.DSMetadata;
import me.chaseoes.deathswap.metadata.MetadataHelper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
        DSMetadata meta = MetadataHelper.getDSMetadata(event.getEntity());
		if (meta.isIngame()) {
            DSGame g = meta.getCurrentGame();
            event.setDeathMessage(null);
            if (meta.getDeathBlame() != null) {
                g.broadcast(DeathSwap.getInstance().format(meta.getDeathBlame() + " killed " + event.getEntity().getName() + "!"));
                Player player = Bukkit.getPlayerExact(meta.getDeathBlame());
                if (player != null && MetadataHelper.getDSMetadata(player).isIngame() && MetadataHelper.getDSMetadata(player).getCurrentGame().getName().equals(g.getName())) {
                    MetadataHelper.getDSMetadata(player).incSwapKills();
                }
            } else {
                g.broadcast(DeathSwap.getInstance().format(event.getEntity().getName() + " killed himself!"));
            }
			g.leaveGame(event.getEntity());
		}
	}

}
