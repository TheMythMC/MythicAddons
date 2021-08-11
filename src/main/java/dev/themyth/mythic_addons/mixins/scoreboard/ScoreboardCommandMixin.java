package dev.themyth.mythic_addons.mixins.scoreboard;

import dev.themyth.mythic_addons.MythicAddonsSettings;
import dev.themyth.mythic_addons.MythicAddonsExtension;
import dev.themyth.mythic_addons.util.StatHelper;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.server.command.ScoreboardCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



// Stolen from quick carpet
// https://github.com/QuickCarpet/QuickCarpet
@Mixin(ScoreboardCommand.class)
public class ScoreboardCommandMixin {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreboardCommandMixin.class);
    @Redirect(method = "executeAddObjective", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/Scoreboard;getNullableObjective(Ljava/lang/String;)Lnet/minecraft/scoreboard/ScoreboardObjective;"))
    private static ScoreboardObjective redirectExecuteAddObjective(Scoreboard scoreboard, String name) {
        ScoreboardObjective objective = scoreboard.getNullableObjective(name);
        if ( objective != null && MythicAddonsSettings.betterStatistics) {
            StatHelper.initialize(scoreboard, MythicAddonsExtension.getInstance().getMinecraftServer(), objective);
        }
        return objective;
    }
}
