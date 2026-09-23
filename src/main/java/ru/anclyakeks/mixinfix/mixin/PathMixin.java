package ru.anclyakeks.mixinfix.mixin;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Path.class, priority = 2000)
public abstract class PathMixin {
    @Shadow
    @Final
    private List<Node> nodes;

    @Shadow
    @Final
    private BlockPos target;

    @Inject(method = "getNodePos", at = @At("HEAD"), cancellable = true)
    private void mixinfix$clampOutOfRangeNodeIndex(int index, CallbackInfoReturnable<BlockPos> cir) {
        if (this.nodes.isEmpty()) {
            cir.setReturnValue(this.target);
            return;
        }

        if (index < 0) {
            cir.setReturnValue(this.nodes.getFirst().asBlockPos());
            return;
        }

        if (index >= this.nodes.size()) {
            cir.setReturnValue(this.nodes.getLast().asBlockPos());
        }
    }
}
