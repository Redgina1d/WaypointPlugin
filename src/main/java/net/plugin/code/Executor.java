package net.plugin.code;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.data.type.PitcherCrop;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;

import ink.anh.api.lingo.Translator;
import ink.anh.api.messages.MessageType;
import ink.anh.api.messages.Messenger;
import ink.anh.api.utils.LangUtils;
import io.papermc.paper.chat.ChatRenderer.Default;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.sisu.inject.TypeArguments;


public class Executor implements CommandExecutor {

    private GlobalManager globalManager;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		switch (label.toLowerCase()) {
		/// COMMANDS ///
		case "wp": {
			if (args.length > 0) {
				return wp(sender, args);
			} else {
				return false;
			}
		}
		case "narrate": {
			if (args.length > 0) {
				return narrate(sender, args);
			} else {
				return false;
			}
		}
		/// COMMANDS ///
		default:
			return false;
		}
    }
	/// COMMAND TO SAY SMTHG TO SENDER ///
	private boolean narrate(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOnline())	{
				if (args.length > 1) {
					String message = String.join(" ", args).toString();
					player.sendMessage(message);
				} else {
					String message = args.toString();
					player.sendMessage(message);
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	/// COMMAND TO ASK FOR WAYPOINT CREATION ///
    private boolean wp(CommandSender sender, String[] args) {
    	Player player = (Player) sender;
        if (args[0] == "create") {
        	if (player.hasPermission("wp.create")) {
        		String[] parsed_args = new String[10];
        		if (args[1] == null) {player.sendMessage("wp_err"); return false;} else {
        			parsed_args[0] = args[1];
        		}
        		if (args[2] == "SpawnOnTop")	{
        			parsed_args[1] = "top";
        		} if (args[2] == "SpawnInFromt") {
        			parsed_args[1] = "front";
        		} else {player.sendMessage("wp_err"); return false;}
        		if (args[3] == "Default") {
        			parsed_args[1] = "default";
        			Object icon_cmd = null;
        		} if (args[3] == "ItemInHand") {
        			if (player.getItemInHand().getType() != Material.AIR) {
        				ItemMeta meta = player.getItemInHand().getItemMeta();
            			Material icon = player.getItemInHand().getType();
            			if (meta.hasCustomModelData()) {
            				Object icon_cmd = meta.getCustomModelData();
            			} else {
            				Object icon_cmd = null;
            			}
        			} else {player.sendMessage("wp_err"); return false;}
        			
        		} else {player.sendMessage("wp_err"); return false;}
        		if (args[4] == "north" || args[4] == "south" || args[4] == "west" || args[4] == "east") {
        			String blockrotation = args[4];
        		} else {player.sendMessage("wp_err"); return false;}
        		if (Bukkit.getWorld(args[5]) != null) {
        			String worldsString = args[5];
        			double world_x_and_z_limite = (Bukkit.getWorld(worldsString).getWorldBorder().getSize() / 2);
        			if (Integer.parseInt(args[6]) > 0 && (Integer.parseInt(args[6]) < world_x_and_z_limite) || Integer.parseInt(args[6]) < 0 && (Integer.parseInt(args[6]) < world_x_and_z_limite) ) {
        				Object x_loc = (args[6]);
        			} else {player.sendMessage("wp_err"); return false;}
        			if (Integer.parseInt(args[7]) > 0 && (Integer.parseInt(args[7]) < Bukkit.getWorld(args[5]).getMaxHeight()) || (Integer.parseInt(args[7]) < 0 && (Integer.parseInt(args[7]) < Bukkit.getWorld(args[5]).getMinHeight()))) {
        				Object y_loc = (args[7]);
        			} else {player.sendMessage("wp_err"); return false;}
        			if (Integer.parseInt(args[8]) > 0 && (Integer.parseInt(args[8]) < world_x_and_z_limite) || Integer.parseInt(args[8]) < 0 && (Integer.parseInt(args[8]) < world_x_and_z_limite) ) {
        				Object z_loc = (args[8]);
        			} else {player.sendMessage("wp_err"); return false;}
        		if (isDouble(args[9])) {
        			Double yay = Double.parseDouble(args[9]);
        			if (yay < 180 && yay > -180) {
        				Object yaw = args[9];
        			} else {player.sendMessage("wp_err"); return false;}
        		} else {player.sendMessage("wp_err"); return false;}
        		if (isDouble(args[10])) {
        			Double pit = Double.parseDouble(args[10]);
        			if (pit < 90 && pit > -90) {
        				Object pitch = args[10];
        			} else {player.sendMessage("wp_err"); return false;}
        		} else {player.sendMessage("wp_err"); return false;}
        	}else {player.sendMessage("wp_err"); return false;}
        		
        	
        if (args[0] == "delete") {
            if (player.hasPermission("wp.delete")) {
            	
            	return true;
            }
        } else {
        	player.sendMessage("err_wp_unsufficient_args");
			return false;
        }
    }
		return false;
        }
    }
    private boolean wpfinalcreate(CommandSender sender) {
    	Player player = (Player) sender;
    	if (player.hasPermission("myplugin.mycommand")) {
    		return true;
    	}
    	return false;
    }

    /*
    // Checks if the player has the required permissions to execute the command.
    private String[] checkPlayerPermissions(CommandSender sender, String permission) {
        if (sender instanceof ConsoleCommandSender) {
            return null; // Console can always execute the command.
        }

        String[] langs = new String[]{null};

        if (sender instanceof Player) {
            Player player = (Player) sender;
            langs = LangUtils.getPlayerLanguage(player); // Gets the player's language.

            if (!player.hasPermission(permission)) {
                // Inform the player if they lack the required permission.
                sendMessage(sender, Translator.translateKyeWorld(globalManager, "err_not_have_permission", langs), MessageType.ERROR);
                return langs;
            }
        }

        return langs;
    }
    */
    // Sends a message to the command sender.
    private void sendMessage(CommandSender sender, String message, MessageType type) {
        Messenger.sendMessage(globalManager, sender, message, type);
    }
    public static boolean isDouble(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
