package dev.themyth.mythic_addons.mixins.init;


import dev.themyth.mythic_addons.MythicAddonsExtension;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(method = "<init>", at=@At("RETURN"))
    private void loadExtension(CallbackInfo ci) {
        MythicAddonsExtension.noop();
        MythicAddonsExtension.getInstance().setMinecraftServer((MinecraftServer) (Object) this);
    }
}
