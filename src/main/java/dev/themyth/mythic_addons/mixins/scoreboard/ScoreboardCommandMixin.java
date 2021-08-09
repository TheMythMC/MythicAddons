package dev.themyth.mythic_addons.mixins.scoreboard;

import com.mojang.brigadier.builder.ArgumentBuilder;
import dev.themyth.mythic_addons.MythicAddonSettings;
import dev.themyth.mythic_addons.MythicAddonsExtension;
import dev.themyth.mythic_addons.util.StatHelper;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.server.command.ScoreboardCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



// Stolen from quick carpet
// https://github.com/QuickCarpet/QuickCarpet
@Mixin(ScoreboardCommand.class)
public class ScoreboardCommandMixin {
    private static final Logger LOGGER = LogManager.getLogger();
    @Redirect(method = "executeAddObjective", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/Scoreboard;getNullableObjective(Ljava/lang/String;)Lnet/minecraft/scoreboard/ScoreboardObjective;"))
    private static ScoreboardObjective redirectExecuteAddObjective(Scoreboard scoreboard, String name) {
        ScoreboardObjective objective = scoreboard.getNullableObjective(name);
        if ( objective != null && MythicAddonSettings.betterStatistics) {
            LOGGER.log(Level.ALL,"initializing stats helper");
            StatHelper.initialize(scoreboard, MythicAddonsExtension.getInstance().getMinecraftServer(), objective);
        }
        return objective;
    }
}
