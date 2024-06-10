package net.plugin.code;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.command.TabCompleter;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.io.File;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.File;

public class WaypointPlugin extends JavaPlugin {

    private static WaypointPlugin instance;

    private GlobalManager manager;

    public static WaypointPlugin getInstance() {
        return instance;
    }
    
    private static WaypointPlugin plugin;
    

    @Override
    public void onEnable() {
    	PluginManager pluginManager = getServer().getPluginManager();
        instance = this;
        manager = GlobalManager.getManager(instance);
        String directoryPath = (plugin.getDataFolder() + "/Waypoints");
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs();
        }
        Permission perm1 = pluginManager.getPermission("wp.create");
        if (perm1 == null) {
        	Permission permcreate = new Permission("wp.create");
        	permcreate.setDescription("Allows players to create waypoints using /wpcreate command.");
            getServer().getPluginManager().addPermission(permcreate);
            permcreate.setDefault(PermissionDefault.OP);
        }
        Permission perm2 = pluginManager.getPermission("wp.delete");
        if (perm2 == null) {
        	Permission permdelete = new Permission("wp.delete");
        	permdelete.setDescription("Allows players to delete existing waypoints using /wpdelete command.");
            getServer().getPluginManager().addPermission(permdelete);
            permdelete.setDefault(PermissionDefault.OP);
        }
        TabCompleter cmpltr = getCommand("wp").getTabCompleter();
        if (cmpltr == null) {
        	getCommand("wp").setTabCompleter(new TabComplete());
        }
        getLogger().info("WaypointPlugin is enabled.");
    }
    

    public GlobalManager getGlobalManager() {
        return manager;
    }


    @Override
    public void onDisable() {
    	getLogger().info("WaypointPlugin is disabled.");
    }

}
