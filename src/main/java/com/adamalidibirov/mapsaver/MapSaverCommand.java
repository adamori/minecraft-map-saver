package com.adamalidibirov.mapsaver;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MapSaverCommand implements CommandExecutor {

    private final MapSaver plugin;

    public MapSaverCommand(MapSaver plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("savemap")) {
            // Check for the required arguments
            if (args.length < 6 || args.length > 8) {
                sender.sendMessage("Usage: /savemap <startX> <startY> <startZ> <endX> <endY> <endZ> <worldName> <bufferSize:4096>");
                return true;
            }

            // Parse the arguments as integers
            int startX = Integer.parseInt(args[0]);
            int startY = Integer.parseInt(args[1]);
            int startZ = Integer.parseInt(args[2]);
            int endX = Integer.parseInt(args[3]);
            int endY = Integer.parseInt(args[4]);
            int endZ = Integer.parseInt(args[5]);

            String worldName = "world";
            int bufferSize = 4096;
            if (args.length == 7)
                worldName = args[6];
            if (args.length == 8)
                bufferSize = Integer.parseInt(args[7]);

            // Get the world and call the function
            plugin.getBlockInfoInRangeAsync(sender.getServer().getWorld(worldName), startX, startY, startZ, endX, endY, endZ, bufferSize);

            sender.sendMessage("Block information saving added to queue.");
            return true;
        }
        return false;
    }
}
