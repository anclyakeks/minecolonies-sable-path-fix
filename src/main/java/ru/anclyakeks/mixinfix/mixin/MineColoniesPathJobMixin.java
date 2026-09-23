package ru.anclyakeks.mixinfix.mixin;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "com.minecolonies.core.entity.pathfinding.pathjobs.AbstractPathJob", remap = false)
public abstract class MineColoniesPathJobMixin {
    @Redirect(
            method = "handleTargetNotPassable",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;"),
            remap = false)
    private Comparable<?> mixinfix$getPropertyIfPresent(BlockState state, Property<?> property) {
        // A tagged block may not use the vanilla properties expected by MineColonies.
        // Both callers compare the result to an enum, so null skips that path option.
        return state.hasProperty(property) ? state.getValue(property) : null;
    }
}
