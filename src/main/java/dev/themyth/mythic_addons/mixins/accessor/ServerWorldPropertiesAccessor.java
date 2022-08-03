package dev.themyth.mythic_addons.mixins.accessor;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.ServerWorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerWorld.class)
public interface ServerWorldPropertiesAccessor {
    @Accessor("worldProperties")
    ServerWorldProperties getWorldProperties();
}
