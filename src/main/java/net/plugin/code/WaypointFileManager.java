package net.plugin.code;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

import org.apache.maven.artifact.repository.metadata.Plugin;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.Logger;

public class WaypointFileManager {
	
	private static WaypointPlugin plugin;
	
	Object dir = (plugin.getDataFolder() + "/Waypoints");

	public void WaypointFileCreate(String name, String spawn, Object icon, String rotation, String world, Double x, Double y, Double z, Float yaw, Float pitch) {
        // Way.
        Object filePath = (dir + "/" + name + ".yml");
        // Data.
        Map<String, Object> data = new HashMap<>();
    	data.put("waypoint", new HashMap<String, Object>() {{
    		put ("ingame_name", "wp_capital");
    		put ("spawn", spawn);
    		put ("gui_icon", icon);
    		put ("rotation", rotation);
    		put("location", new HashMap<String, Object>() {{
    			put("world", world);
    			put("x", x);
    			put("y", y);
    			put("z", z);
    			put("yaw", yaw);
    			put("pitch", pitch);
    		}});
    	}});
    	DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml wp_file = new Yaml(options);
        try (FileWriter writer = new FileWriter((String) filePath)) {
        	wp_file.dump(data, writer);
            System.out.println(": " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
