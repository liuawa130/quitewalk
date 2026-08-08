package com.quietwalk.mod.mixin;

import com.quietwalk.mod.config.QuietWalkConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Projectile.class)
public abstract class MixinProjectile {

    @ModifyArg(method = "shootFromRotation", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/projectile/Projectile;shoot(DDDFF)V"), index = 4)
    private float quietwalk$reduceSpread(float inaccuracy) {
        Entity owner = ((Projectile)(Object) this).getOwner();
        if (owner instanceof LivingEntity le && le.isShiftKeyDown()
                && ((Entity)(Object) this).getClass().getName().startsWith("com.tacz.guns."))
            return inaccuracy * QuietWalkConfig.SNEAK_ACCURACY_FACTOR.get().floatValue();
        return inaccuracy;
    }
}
