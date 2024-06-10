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

public class WaypointFileManager {
	
	private static WaypointPlugin plugin;
	
	Object dir = (plugin.getDataFolder() + "/Waypoints");

	public void WaypointFileCreate(String name, String world, Double x, Double y, Double z, Float yaw, Float pitch) {
        // Way.
        Object filePath = (dir + "/" + name + ".yml");
        // Data.
        Map<String, Object> data = new HashMap<>();
    	data.put("waypoint", new HashMap<String, Object>() {{
    		put("ingame_name", "wp_capital");
    		put("location", new HashMap<String, Object>() {{
    			put("world", world);
    			put("x", x);
    			put("y", y);
    			put("z", z);
    			put("yaw", yaw);
    			put("pitch", pitch);
    		}});
    	}});
	}
}	
	
	public class Waypoint {
		
		private String name;
		private String world;
		private Double x;
		private Double y;
		private Double z;
		private Float yaw;
		private Float pitch;
		
		public void WaypointFileCreate(String name, String world, Double x, Double y, Double z, Float yaw, Float pitch) {
	        // Way.
	        Object filePath = (plugin.getDataFolder() + "/" + name + ".yml");
	        // Data.
	        Map<String, Object> data = new HashMap<>();
	    	data.put("waypoint", new HashMap<String, Object>() {{
	    		put("ingame_name", "wp_capital");
	    		put("location", new HashMap<String, Object>() {{
	    			put("world", world);
	    			put("x", x);
	    			put("y", y);
	    			put("z", z);
	    			put("yaw", yaw);
	    			put("pitch", pitch);
	    		}});
	    	}});
			
		}	
	}

        // Way.
        Object filePath = (plugin.getDataFolder() + "/" + name + ".yml");
        
        // Data.
        Map<String, Object> data = new HashMap<>();
    	data.put("waypoint", new HashMap<String, Object>() {{
    		put("ingame_name", "wp_capital");
    		put("location", new HashMap<String, Object>() {{
    			put("world", world);
    			put("x", x);
    			put("y", y);
    			put("z", z);
    			put("yaw", yaw);
    			put("pitch", pitch);
    		}});
    	}});
        
        // Настраиваем параметры для сохранения YAML
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        
        // Записываем данные в YAML файл
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(data, writer);
            System.out.println("YAML файл успешно создан: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
