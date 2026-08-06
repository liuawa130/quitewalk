package com.quietwalk.mod.mixin;

import com.quietwalk.mod.QuietWalkState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "eu.ha3.presencefootsteps.sound.generator.TerrestrialStepSoundGenerator")
public class MixinPFCompat {

    @Inject(method = "generateFootsteps", at = @At("HEAD"), cancellable = true)
    private void quietwalk$silentPFSteps(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (QuietWalkState.shouldSilence(entity)) cir.setReturnValue(false);
    }
}
