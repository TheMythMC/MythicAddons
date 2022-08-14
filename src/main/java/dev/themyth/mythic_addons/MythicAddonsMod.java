package dev.themyth.mythic_addons;

import carpet.CarpetServer;
import dev.themyth.mythic_addons.util.PlayerBackup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MythicAddonsMod implements ModInitializer {
    private static String version;

    public static String getVersion() {
        return version;
    }

    @Override
    public void onInitialize() {
        // Actually load the carpet extension
        MythicAddonsExtension.loadExtension();

        // Get the version number for version var
        version = FabricLoader.getInstance()
                .getModContainer("mythic-addons")
                .orElseThrow(RuntimeException::new)
                .getMetadata()
                .getVersion().getFriendlyString();
        // Set up the player data backup
        Executors
                .newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(PlayerBackup::backupPlayerData, 1, 1, TimeUnit.HOURS);
    }
}
