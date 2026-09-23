package ru.anclyakeks.mixinfix.mixin;

import net.minecraft.world.entity.ai.navigation.PathNavigation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.minecolonies.core.entity.pathfinding.navigation.MinecoloniesAdvancedPathNavigate", remap = false)
public abstract class MineColoniesNavigatorMixin {
    @Inject(method = "updateNodeReferences", at = @At("HEAD"), cancellable = true, remap = false)
    private void mixinfix$skipMissingPath(CallbackInfo ci) {
        if (((PathNavigation) (Object) this).getPath() == null) {
            ci.cancel();
        }
    }
}
