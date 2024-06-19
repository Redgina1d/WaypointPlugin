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

import javax.print.CancelablePrintJob;

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
		case "cnms": {
			if (args.length > 0) {
				return cnms(sender, args);
			} else {
				return false;
			}
		}
		default:
			return false;
		}
    }
    private GlobalManager globalManager;
    
    private boolean cnms(CommandSender sender, String[] args) {
    	Player player = (Player) sender;
    	ConsoleCommandSender console = Bukkit.getConsoleSender();
    	if (player.hasPermission("cnms.send")) {
    		console.sendMessage(args);
    	} else {return true;}
		return true;
    }
    
	/// COMMAND TO ASK FOR WAYPOINT CREATION ///
    private boolean wp(CommandSender sender, String[] args) {
    	Player player = (Player) sender;
        if (args[0] == "create") {
        	if (player.hasPermission("wp.create")) {
        		Object[] parsed_args = new Object[10];
        		if (args[1] == null) {player.sendMessage("wp_err"); return true;} else {
        			parsed_args[0] = args[1];
        		}
        		if (args[2] == "SpawnOnTop")	{
        			parsed_args[1] = "top";
        		} if (args[2] == "SpawnInFromt") {
        			parsed_args[1] = "front";
        		} else {player.sendMessage("wp_err"); return true;}
        		if (args[3] == "Default") {
        			parsed_args[2] = "default";
        			parsed_args[3] = null;
        		} if (args[3] == "ItemInHand") {
        			if (player.getItemInHand().getType() != Material.AIR) {
        				ItemMeta meta = player.getItemInHand().getItemMeta();
            			Material icon = player.getItemInHand().getType();
            			parsed_args[2] = icon;
            			if (meta.hasCustomModelData()) {
            				parsed_args[3] = meta.getCustomModelData();
            			} else {
            				parsed_args[3] = null;
            			}
        			} else {player.sendMessage("wp_err"); return true;}
        			
        		} else {player.sendMessage("wp_err"); return true;}
        		if (args[4] == "north" || args[4] == "south" || args[4] == "west" || args[4] == "east") {
        			parsed_args[4] = args[4];
        		} else {player.sendMessage("wp_err"); return true;}
        		if (Bukkit.getWorld(args[5]) != null) {
        			String worldsString = args[5];
        			parsed_args[5] = worldsString;
        			double world_x_and_z_limite = (Bukkit.getWorld(worldsString).getWorldBorder().getSize() / 2);
        			if (Integer.parseInt(args[6]) > 0 && (Integer.parseInt(args[6]) < world_x_and_z_limite) || Integer.parseInt(args[6]) < 0 && (Integer.parseInt(args[6]) < world_x_and_z_limite) ) {
        				parsed_args[6] = (args[6]);
        			} else {player.sendMessage("wp_err"); return true;}
        			if (Integer.parseInt(args[7]) > 0 && (Integer.parseInt(args[7]) < Bukkit.getWorld(args[5]).getMaxHeight()) || (Integer.parseInt(args[7]) < 0 && (Integer.parseInt(args[7]) < Bukkit.getWorld(args[5]).getMinHeight()))) {
        				parsed_args[7] = (args[7]);
        			} else {player.sendMessage("wp_err"); return true;}
        			if (Integer.parseInt(args[8]) > 0 && (Integer.parseInt(args[8]) < world_x_and_z_limite) || Integer.parseInt(args[8]) < 0 && (Integer.parseInt(args[8]) < world_x_and_z_limite) ) {
        				parsed_args[8] = (args[8]);
        			} else {player.sendMessage("wp_err"); return true;}
        		if (isDouble(args[9])) {
        			Double yay = Double.parseDouble(args[9]);
        			if (yay < 180 && yay > -180) {
        				parsed_args[9] = args[9];
        			} else {player.sendMessage("wp_err"); return false;}
        		} else {player.sendMessage("wp_err"); return false;}
        		if (isDouble(args[10])) {
        			Double pit = Double.parseDouble(args[10]);
        			if (pit < 90 && pit > -90) {
        				parsed_args[10] = args[10];
        			} else {player.sendMessage("wp_err"); return false;}
        		} else {player.sendMessage("wp_err"); return false;}
        		} else {player.sendMessage("wp_err"); return false;}
        		byte m = 0;
        		for (int i = 0; i < parsed_args.length; i++) {
        			if (parsed_args[i] != null) {
        				m++;
        			}
        		} if (m == 11) {
        			return true;
        		}
        	}
        } if (args[0] == "delete") {
        	if (player.hasPermission("wp.delete")) {
        	
        	return true;
        	} else {player.sendMessage("wp_err"); return false;}
        } else {player.sendMessage("wp_err"); return false;}
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
    public void sendMessage(CommandSender sender, String message, MessageType type) {
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
