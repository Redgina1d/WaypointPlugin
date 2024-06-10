package net.plugin.code;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // List to store tab completions
        List<String> completions = new ArrayList<>();
        if (sender instanceof Player) {
    		Player player = (Player) sender;
    		// Create or delete [0]
    		if (args.length == 1) {
                completions.add("create");
                completions.add("delete");
    		}
    		// Name [1]
            if (args.length == 2) {
            	completions.add("Name_of_waypoint");
            }
            // Where to teleport player [2]
            if (args.length == 3) {
            	completions.add("SpawnOnTop");
            	completions.add("SpawnInFront");
            }
            // Icon in GUI [3]
            if (args.length == 4) {
            	completions.add("Default");
            	completions.add("ItemInHand");
            }
            // Rotation of waypoint default block [4]
            if (args.length == 5) {
            	completions.add("north");
            	completions.add("south");
            	completions.add("west");
            	completions.add("east");
            }
            // World [5]
            if (args.length == 6) {
            	completions.add(player.getLocation().getWorld().toString());
            }
            // X loc [6]
            if (args.length == 7) {
            	double xloc = Math.floor(player.getLocation().getX());
                completions.add(String.valueOf(xloc));
            }
            // Y loc [7]
            if (args.length == 8) {
            	double yloc = Math.floor(player.getLocation().getY());
            	completions.add(String.valueOf(yloc));
            }
            // Z loc [8]
            if (args.length == 9) {
            	double zloc = Math.floor(player.getLocation().getZ());
            	completions.add(String.valueOf(zloc));
            }
            // Yaw [9]
            if (args.length == 10) {
            	completions.add(String.valueOf(player.getLocation().getYaw()));
            }
            // Pitch [10]
            if (args.length == 11) {
            	completions.add(String.valueOf(player.getLocation().getPitch()));
            }
            return completions;
        }
		return null;
    }
}