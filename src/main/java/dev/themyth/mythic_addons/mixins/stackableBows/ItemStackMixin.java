package dev.themyth.mythic_addons.mixins.stackableBows;

import dev.themyth.mythic_addons.MythicAddonsSettings;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    // Using an inject because you can't do it the same way carpet does stackable shulkers because of mixin conflicts, this will (hopefully) ensure that no conflicts ensue
    @Inject(
            method = "getMaxCount",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getMaxCount(CallbackInfoReturnable<Integer> cir) {
        // You can use this for everything, just add a this.getItem() instanceof <Item Class Here>
        if (this.getItem() instanceof BowItem && MythicAddonsSettings.stackableBows) {
            cir.setReturnValue(64);
        }
    }
}
