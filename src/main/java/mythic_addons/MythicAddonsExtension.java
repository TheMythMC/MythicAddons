package mythic_addons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import mythic_addons.commands.CommandRegion;
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
    }


}
