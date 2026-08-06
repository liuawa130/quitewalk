package com.quietwalk.mod.mixin;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class MixinLocalPlayer {

    @ModifyArg(method = "aiStep", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/player/Input;tick(ZF)V"), index = 1)
    private float quietwalk$cancelSneakSlowdown(float sneakMultiplier) {
        return ((LocalPlayer)(Object) this).isShiftKeyDown() ? 1.0F : sneakMultiplier;
    }

    @Inject(method = "aiStep", at = @At("RETURN"))
    private void quietwalk$cancelSprint(CallbackInfo ci) {
        ((LocalPlayer)(Object) this).setSprinting(false);
    }
}
