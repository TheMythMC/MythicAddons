package dev.themyth.mythic_addons.mixins.stackableShulkers;

import carpet.helpers.InventoryHelper;
import dev.themyth.mythic_addons.MythicAddonsSettings;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @Inject(method = "canMergeItems", at = @At("HEAD"), cancellable = true)
    private static void canMergeItems(ItemStack first, ItemStack second, CallbackInfoReturnable<Boolean> cir) {
        if (first.getItem() instanceof BlockItem &&
            ((BlockItem) first.getItem()).getBlock() instanceof ShulkerBoxBlock &&
            !InventoryHelper.shulkerBoxHasItems(first)&&
            second.getItem() instanceof BlockItem &&
            ((BlockItem) second.getItem()).getBlock() instanceof ShulkerBoxBlock &&
            !InventoryHelper.shulkerBoxHasItems(second) &&
            !MythicAddonsSettings.shulkerBoxesStackingInHoppers)
            // I have absolutely no idea why its false here but ok minecraft
            // nvm I figured it out... silly me
            cir.setReturnValue(false);
    }
}
