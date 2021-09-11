package dev.themyth.mythic_addons.mixins.carpet.hooks;

import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import carpet.utils.Translations;
import dev.themyth.mythic_addons.MythicAddonsExtension;
import dev.themyth.mythic_addons.MythicAddonsMod;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SettingsManager.class)
public class SettingsManagerMixin {
    @SuppressWarnings("DefaultAnnotationParam")
    @Inject(
            method = "listAllSettings",
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = "stringValue=ui.version",
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lcarpet/settings/SettingsManager;getCategories()Ljava/lang/Iterable;",
                    ordinal = 0,
                    remap = false
            ),
            remap = false
    )
    private void printAdditionVersion(ServerCommandSource source, CallbackInfoReturnable<Integer> cir) {
        Messenger.m(source,
                String.format("g %s ", MythicAddonsExtension.fancyName),
                String.format("g %s: ", Translations.tr("ui.version", "version")),
                String.format("g %s", MythicAddonsMod.getVersion())
                );
    }
}
