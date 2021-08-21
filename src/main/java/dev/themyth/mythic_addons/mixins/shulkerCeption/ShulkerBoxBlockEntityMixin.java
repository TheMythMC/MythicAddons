package dev.themyth.mythic_addons.mixins.shulkerCeption;

import dev.themyth.mythic_addons.MythicAddonsSettings;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static carpet.helpers.InventoryHelper.shulkerBoxHasItems;

@Mixin(ShulkerBoxBlockEntity.class)
public class ShulkerBoxBlockEntityMixin {
    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    private void canInsert(int slot, ItemStack stack, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof ShulkerBoxBlock && !shulkerBoxHasItems(stack) && MythicAddonsSettings.emptyShulkerCeption) {
            cir.setReturnValue(true);
        }
    }
}
