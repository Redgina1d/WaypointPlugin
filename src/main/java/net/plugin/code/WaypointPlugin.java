package net.plugin.code;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.io.File;

public class WaypointPlugin extends JavaPlugin {

    private static WaypointPlugin instance;

    private GlobalManager manager;

    public static WaypointPlugin getInstance() {
        return instance;
    }    

    @Override
    public void onEnable() {
    instance = this;
    instance.getCommand("wp").setExecutor(new Executor());
    instance.getCommand("cnms").setExecutor(new Executor());
    
    PluginManager pluginManager = getServer().getPluginManager();
        manager = GlobalManager.getManager(instance);
        String directoryPath = (instance.getDataFolder() + "/Waypoints");
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
    public void forceExecuteCommand(Player player, String command, String perm) {
        boolean hadPermission = player.hasPermission("your.permission.node");
        if (!hadPermission) {
            player.addAttachment(this, perm, true);
        }
        player.performCommand(command);
        if (!hadPermission) {
            player.addAttachment(this, perm, false);
        }
    }

}