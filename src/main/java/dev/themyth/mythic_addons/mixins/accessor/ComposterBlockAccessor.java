package dev.themyth.mythic_addons.mixins.accessor;

import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ComposterBlock.class)
public interface ComposterBlockAccessor {
    @Invoker(value = "registerCompostableItem", remap = true)
    static void registerCompostableItem(float x, ItemConvertible item) {
        throw new AbstractMethodError();
    }
}
