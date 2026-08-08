package com.quietwalk.mod;

import com.quietwalk.mod.config.QuietWalkConfig;
import net.minecraft.world.entity.LivingEntity;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class QuietWalkState {

    private static final Set<UUID> quietWalkers = ConcurrentHashMap.newKeySet();

    public static void setQuietWalking(UUID playerId, boolean walking) {
        if (walking) quietWalkers.add(playerId);
        else quietWalkers.remove(playerId);
    }

    public static boolean isQuietWalking(UUID entityId) {
        return quietWalkers.contains(entityId);
    }

    public static boolean shouldSilence(LivingEntity entity) {
        return (QuietWalkConfig.SILENT_SNEAK.get() && entity.isShiftKeyDown())
                || (QuietWalkConfig.SILENT_QUIET_WALK.get() && isQuietWalking(entity.getUUID()));
    }
}
