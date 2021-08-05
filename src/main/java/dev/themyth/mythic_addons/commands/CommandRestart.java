package dev.themyth.mythic_addons.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import dev.themyth.mythic_addons.MythicAddonSettings;
import net.fabricmc.loader.launch.knot.KnotServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import static net.minecraft.server.command.CommandManager.literal;

public class CommandRestart {

    public static String[] args;

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register( literal("restart").requires( player -> SettingsManager.canUseCommand(player, MythicAddonSettings.commandRestart) && player.hasPermissionLevel(2))
                .executes( ctx -> {
                    restart();
                    return 1;
                }));
    }

    public static void restart() {
        StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home")).append(File.separator).append("bin").append(File.separator).append("java ");
        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            cmd.append(jvmArg).append(" ");
        }
        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        cmd.append(KnotServer.class.getName()).append(" ");
        for (String arg : args) {
            cmd.append(arg).append(" ");
        }
        try {
            Runtime.getRuntime().exec(cmd.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}

