package net.plugin.code;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class WaypointInteractEvent implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
    	
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        
        Object filePath = (plugin.getDataFolder() + "/" + name + ".yml")
        
        if (block == null || block.getType() != Material.AIR) {
            return;
        }

        Location loc = block.getLocation();

        if (!lootBox.hasLooted(playerUUID)) {
            LootBoxOpen.openThisLootBox(player, loc);
            String sound = "BLOCK_CHEST_OPEN";
            SoundCategory category = SoundCategory.MASTER;
            float volume = 1;
            float pitch = 0.2f;
            loc.getWorld().playSound(loc, sound, category, volume, pitch);
        }
    }

}
