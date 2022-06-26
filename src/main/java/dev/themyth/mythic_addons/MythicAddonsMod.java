package dev.themyth.mythic_addons;

import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MythicAddonsMod implements ModInitializer {
    private static String version;

    public static String getVersion() {
        return version;
    }

    @Override
    public void onInitialize() {
        // Actually load the mod
        MythicAddonsExtension.loadExtension();

        // Tell people we are using the mod when they run /carpet
        version = FabricLoader.getInstance()
                .getModContainer("mythic-addons")
                .orElseThrow(RuntimeException::new)
                .getMetadata()
                .getVersion().getFriendlyString();
    }
}
