package com.quietwalk.mod.mixin;

import com.quietwalk.mod.config.QuietWalkConfig;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.tacz.guns.client.animation.statemachine.GunAnimationStateContext")
public abstract class MixinTaczGunPose {

    @Inject(method = "shouldSlide", at = @At("HEAD"), cancellable = true)
    private void quietwalk$cancelSlide(CallbackInfoReturnable<Boolean> cir) {
        if (QuietWalkConfig.SNEAK_KEEP_GUN_POSE.get()) {
            var player = Minecraft.getInstance().player;
            if (player != null && player.isShiftKeyDown())
                cir.setReturnValue(false);
        }
    }
}
