package com.quietwalk.mod.mixin;

import com.quietwalk.mod.config.QuietWalkConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public abstract class MixinProjectile {

    private static final ThreadLocal<Entity> SHOOTER = new ThreadLocal<>();

    @Inject(method = "shootFromRotation", at = @At("HEAD"))
    private void quietwalk$captureShooter(Entity shooter, float x, float y, float z, float speed, float inaccuracy, CallbackInfo ci) {
        SHOOTER.set(shooter);
    }

    @ModifyArg(method = "shootFromRotation", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/projectile/Projectile;shoot(DDDFF)V"), index = 4)
    private float quietwalk$reduceSpread(float inaccuracy) {
        Entity shooter = SHOOTER.get();
        if (shooter instanceof LivingEntity le && le.isShiftKeyDown()
                && ((Entity)(Object) this).getClass().getName().startsWith("com.tacz.guns."))
            return inaccuracy * QuietWalkConfig.SNEAK_ACCURACY_FACTOR.get().floatValue();
        return inaccuracy;
    }

    @Inject(method = "shootFromRotation", at = @At("RETURN"))
    private void quietwalk$clearShooter(CallbackInfo ci) {
        SHOOTER.remove();
    }
}
