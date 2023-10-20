package com.adamalidibirov.mapsaver;

import com.opencsv.CSVWriter;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Levi Muniz on 7/29/20.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MapSaver extends JavaPlugin {
  private final Logger log = Logger.getLogger("Minecraft");


  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);
    this.getCommand("savemap").setExecutor(new MapSaverCommand(this));

    saveDefaultConfig();
  }

  public void getBlockInfoInRangeAsync(World world, int startX, int startY, int startZ, int endX, int endY, int endZ, int bufferSize) {
    // Schedule a task to run asynchronously
    Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
      getBlockInfoInRange(world, startX, startY, startZ, endX, endY, endZ, bufferSize);
    });
  }

  public void getBlockInfoInRange(World world, int startX, int startY, int startZ, int endX, int endY, int endZ, int bufferSize) {
    // Define the CSV file path
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String timestamp = dateFormat.format(new Date());

    File csvFile = new File(getDataFolder(),  "block_info_" + timestamp + ".csv");

    try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
      String[] header = new String[]{"x", "y", "z", "name"};
      writer.writeNext(header);

      List<String[]> buffer = new ArrayList<>(); // Buffer to accumulate data
      int blockCount = 0; // Count of processed blocks
      int dataPartsCount = 0; // Count of written parts

      for (int x = startX; x <= endX; x++) {
        for (int y = startY; y <= endY; y++) {
          for (int z = startZ; z <= endZ; z++) {
            Block block = world.getBlockAt(x, y, z);
            String[] blockData = {
                    String.valueOf(x),
                    String.valueOf(y),
                    String.valueOf(z),
                    block.getType().name()
            };
            buffer.add(blockData);
            blockCount++;

            if (buffer.size() >= bufferSize) {
              // Write the buffer to the CSV file
              writer.writeAll(buffer);
              buffer.clear();
              dataPartsCount++;
              logProgress(blockCount, dataPartsCount, (endX - startX + 1) * (endY - startY + 1) * (endZ - startZ + 1));
            }
          }
        }
      }

      // Write any remaining data in the buffer
      if (!buffer.isEmpty()) {
        writer.writeAll(buffer);
        dataPartsCount++;
        logProgress(blockCount, dataPartsCount, (endX - startX + 1) * (endY - startY + 1) * (endZ - startZ + 1));
      }
      log.info("Exporting to csv file finished");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  private void logProgress(int blockCount, int dataPartsCount, int totalBlocks) {
    int remainingBlocks = totalBlocks - blockCount;
    log.info("Blocks Processed: " + blockCount);
    log.info("Data Parts Written: " + dataPartsCount);
    log.info("Blocks Remaining: " + remainingBlocks);
  }
}
