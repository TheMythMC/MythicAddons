package dev.themyth.mythic_addons.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import dev.themyth.mythic_addons.MythicAddonsExtension;
import dev.themyth.mythic_addons.MythicAddonsSettings;
import dev.themyth.mythic_addons.util.CameraData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

public class SpectatorCommands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("c")
                .requires(player -> SettingsManager.canUseCommand(player, MythicAddonsSettings.spectatorCommands))
                .executes(ctx -> cameraMode(ctx.getSource(), ctx.getSource().getPlayer())));
        dispatcher.register(CommandManager.literal("s")
                .requires(player -> SettingsManager.canUseCommand(player, MythicAddonsSettings.spectatorCommands))
                .executes(ctx -> serverMode(ctx.getSource(), ctx.getSource().getPlayer())));
    }

    private static int cameraMode(ServerCommandSource source, ServerPlayerEntity target) {
        if (!(hasPermission(source, target)) || target.isSpectator()) return 0;
        MythicAddonsExtension.getInstance().cameraData.put(target.getUuid(), new CameraData(target));
        target.changeGameMode(GameMode.SPECTATOR);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 999999, 0, false, false));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 999999, 0, false, false));

        return 1;
    }

    public static boolean hasPermission(ServerCommandSource s, PlayerEntity target) {
        return SettingsManager.canUseCommand(s, MythicAddonsSettings.spectatorCommands);
    }

    private static int serverMode(ServerCommandSource source, ServerPlayerEntity target) {
        if (!(hasPermission(source, target))) return 0;
        GameMode mode = source.getServer().getDefaultGameMode();
        if (mode == GameMode.SPECTATOR) mode = GameMode.SURVIVAL;
        CameraData data = MythicAddonsExtension.getInstance().cameraData.remove(target.getUuid());
        if (data != null) {
            data.restore(target);
        }
        target.changeGameMode(mode);
        target.removeStatusEffect(StatusEffects.NIGHT_VISION);
        target.removeStatusEffect(StatusEffects.CONDUIT_POWER);
        return 1;
    }

}
