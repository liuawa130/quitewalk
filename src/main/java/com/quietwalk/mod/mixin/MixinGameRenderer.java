package com.quietwalk.mod.mixin;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Redirect(method = "getFov", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/util/Mth;lerp(FFF)F"))
    private float quietwalk$cancelFovModifier(float partialTicks, float oldFov, float fov) {
        return 1.0F;
    }
}
