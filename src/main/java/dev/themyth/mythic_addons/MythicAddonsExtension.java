package dev.themyth.mythic_addons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import dev.themyth.mythic_addons.commands.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.WorldSavePath;

import java.nio.file.Path;

public class MythicAddonsExtension implements CarpetExtension {
    public static final String fancyName = "Mythic Addons";
    private  MinecraftServer server;
    public static void noop() { }
    private static final MythicAddonsExtension extension = new MythicAddonsExtension();
    static
    {
        CarpetServer.manageExtension(extension);
        extension.onGameStarted();
    }

    @Override
    public void onGameStarted()
    {
        // Let's have our own settings class independent of carpet.conf
        CarpetServer.settingsManager.parseSettingsClass(MythicAddonsSettings.class);
    }

    public void setMinecraftServer(MinecraftServer server) {
        this.server = server;
    }

    public static MythicAddonsExtension getInstance() {
        if (extension == null) throw new IllegalStateException("No MythicAddons instance found");
        return extension;
    }

    public MinecraftServer getMinecraftServer() {
        if (this.server == null) throw new IllegalStateException("No server instance found");

        return this.server;
    }

    public static Path getConfigFile(WorldSavePath name) {
        return getInstance().getMinecraftServer().getSavePath(name);
    }


    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        CommandRegion.register(dispatcher);
        CommandCraftingTable.register(dispatcher);
        CommandEnderChest.register(dispatcher);
        CommandUnicorn.register(dispatcher);
    }


}
