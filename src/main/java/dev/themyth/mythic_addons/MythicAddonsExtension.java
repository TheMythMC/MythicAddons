package dev.themyth.mythic_addons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import dev.themyth.mythic_addons.commands.CommandCraftingTable;
import dev.themyth.mythic_addons.commands.CommandEnderChest;
import dev.themyth.mythic_addons.commands.CommandRegion;
import dev.themyth.mythic_addons.commands.CommandUnicorn;
import net.minecraft.server.command.ServerCommandSource;

public class MythicAddonsExtension implements CarpetExtension
{
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

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        CommandRegion.register(dispatcher);
        CommandCraftingTable.register(dispatcher);
        CommandEnderChest.register(dispatcher);
        CommandUnicorn.register(dispatcher);
    }


}
