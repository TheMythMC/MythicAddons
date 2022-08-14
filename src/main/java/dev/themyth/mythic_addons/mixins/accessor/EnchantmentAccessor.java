package dev.themyth.mythic_addons.mixins.accessor;

import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Enchantment.class)
interface EnchantmentAccessor {
    @Invoker
    boolean invokeCanAccept(Enchantment other);
}
