package dev.themyth.mythic_addons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import dev.themyth.mythic_addons.commands.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.WorldSavePath;

import java.nio.file.Path;

public class MythicAddonsExtension implements CarpetExtension
{
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
        CarpetServer.settingsManager.parseSettingsClass(MythicAddonSettings.class);
    }

    public void setMinecraftServer(MinecraftServer server) {
        this.server = server;
    }

    public static MythicAddonsExtension getInstance() {
        return extension;
    }

    public MinecraftServer getMinecraftServer() {
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
