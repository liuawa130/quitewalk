package com.quietwalk.mod.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinDisableSprint {

    @Inject(method = "setSprinting", at = @At("HEAD"), cancellable = true)
    private void quietwalk$blockSprint(boolean sprinting, CallbackInfo ci) {
        if (sprinting && (Object) this instanceof Player) ci.cancel();
    }
}
