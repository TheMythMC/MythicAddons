package dev.themyth.mythic_addons.mixins.mendfinity;

import dev.themyth.mythic_addons.MythicAddonsSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InfinityEnchantment.class)
public class InfinityEnchantmentMixin {
    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
    public void canAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
        if (other instanceof MendingEnchantment && MythicAddonsSettings.mendFinity) {
            cir.setReturnValue(true);
        }
    }
}
