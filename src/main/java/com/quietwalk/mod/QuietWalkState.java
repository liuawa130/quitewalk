package com.quietwalk.mod;

import com.quietwalk.mod.config.QuietWalkConfig;
import net.minecraft.world.entity.LivingEntity;
import java.util.UUID;

public final class QuietWalkState {

    private static volatile UUID quietWalkerId = null;

    public static void setQuietWalking(UUID playerId, boolean walking) {
        quietWalkerId = walking ? playerId : null;
    }

    public static boolean isQuietWalking(UUID entityId) {
        UUID id = quietWalkerId;
        return id != null && id.equals(entityId);
    }

    public static boolean shouldSilence(LivingEntity entity) {
        return (QuietWalkConfig.SILENT_SNEAK.get() && entity.isShiftKeyDown())
                || (QuietWalkConfig.SILENT_QUIET_WALK.get() && isQuietWalking(entity.getUUID()));
    }
}
