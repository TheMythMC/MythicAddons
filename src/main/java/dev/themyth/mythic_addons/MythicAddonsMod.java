package dev.themyth.mythic_addons;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MythicAddonsMod implements ModInitializer {
    private static String version;

    public static String getVersion() {
        return version;
    }

    @Override
    public void onInitialize() {
        version = FabricLoader.getInstance()
                .getModContainer("mythic-addons")
                .orElseThrow(RuntimeException::new)
                .getMetadata()
                .getVersion().getFriendlyString();
    }
}
