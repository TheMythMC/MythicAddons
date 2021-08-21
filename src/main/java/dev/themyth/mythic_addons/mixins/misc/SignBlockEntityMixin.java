package dev.themyth.mythic_addons.mixins.misc;

import dev.themyth.mythic_addons.MythicAddonsSettings;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignBlockEntity.class)
public abstract class SignBlockEntityMixin {
    @Shadow
    private boolean editable;

    @Inject(method="onActivate", at= @At("HEAD"))
    private void onActivate(ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (MythicAddonsSettings.editableSigns && player.isSneaking()) {
            editable = true;
            player.openEditSignScreen( (SignBlockEntity) (Object) this);
        }
    }
}
