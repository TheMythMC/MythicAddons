package dev.themyth.mythic_addons.mixins.init;

import dev.themyth.mythic_addons.MythicAddonsExtension;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
@Environment(value=EnvType.CLIENT)
public class MinecraftClientMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void load(CallbackInfo ci) {
        MythicAddonsExtension.noop();
    }
}
