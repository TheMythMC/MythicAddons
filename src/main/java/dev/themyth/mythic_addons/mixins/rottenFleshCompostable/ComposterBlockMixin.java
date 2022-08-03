package dev.themyth.mythic_addons.mixins.rottenFleshCompostable;

import dev.themyth.mythic_addons.MythicAddonsSettings;
import dev.themyth.mythic_addons.mixins.accessor.ComposterBlockAccessor;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComposterBlock.class)
public abstract class ComposterBlockMixin {

   @Inject(method = "registerDefaultCompostableItems", at = @At("HEAD"))
   private static void registerIf(CallbackInfo ci) {
       if (MythicAddonsSettings.rottenFleshCompostable) {
            ComposterBlockAccessor.registerCompostableItem(0.3f, Items.ROTTEN_FLESH);
       }
   }
}
