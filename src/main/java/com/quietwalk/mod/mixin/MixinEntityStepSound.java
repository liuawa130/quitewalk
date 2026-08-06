package com.quietwalk.mod.mixin;

import com.quietwalk.mod.QuietWalkState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MixinEntityStepSound {

    @Inject(method = "playStepSound", at = @At("HEAD"), cancellable = true)
    private void quietwalk$silentStep(BlockPos pos, BlockState state, CallbackInfo ci) {
        if (QuietWalkState.shouldSilence((Player)(Object) this)) ci.cancel();
    }
}
