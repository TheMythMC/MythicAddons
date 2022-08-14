package dev.themyth.mythic_addons.commands;

import com.mojang.brigadier.CommandDispatcher;
import dev.themyth.mythic_addons.util.PlayerBackup;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class DebugCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("debug").executes(ctx -> {
            PlayerBackup.backupPlayerData();
            return 0;
        }));
    }
}
