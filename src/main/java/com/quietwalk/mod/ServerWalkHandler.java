package com.quietwalk.mod;

import com.quietwalk.mod.config.QuietWalkConfig;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class ServerWalkHandler {

    private static final UUID RUN_SPEED_ID = UUID.fromString("b4e3d2c5-6c7f-5a8b-ad9e-2f3a4b5c6d7e");
    private static final UUID QUIET_WALK_ID = UUID.fromString("a3f2c1d4-5b6e-4f7a-9c8d-1e2f3a4b5c6d");
    private static final UUID SNEAK_SPEED_ID = UUID.fromString("c5f4e3d6-7d8e-6b9c-be0f-3a4b5c6d7e8f");

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        Player player = event.player;
        if (player.level().isClientSide) return;

        AttributeInstance speedAttr = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttr == null) return;

        boolean sneaking = player.isShiftKeyDown();
        boolean quietWalking = QuietWalkState.isQuietWalking(player.getUUID());

        setModifier(speedAttr, RUN_SPEED_ID, "CS2 Run",
                QuietWalkConfig.NORMAL_SPEED_FACTOR.get() - 1.0, true);
        setModifier(speedAttr, QUIET_WALK_ID, "Quiet Walk",
                QuietWalkConfig.SLOW_WALK_FACTOR.get() - 1.0, quietWalking);
        setModifier(speedAttr, SNEAK_SPEED_ID, "CS2 Crouch",
                QuietWalkConfig.SNEAK_SPEED_FACTOR.get() - 1.0, sneaking);
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        QuietWalkState.setQuietWalking(event.getEntity().getUUID(), false);
    }

    private static void setModifier(AttributeInstance attr, UUID id, String name, double amount, boolean active) {
        AttributeModifier existing = attr.getModifier(id);
        if (active) {
            if (existing == null || existing.getAmount() != amount) {
                if (existing != null) attr.removeModifier(id);
                attr.addTransientModifier(new AttributeModifier(id, name, amount, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        } else if (existing != null) {
            attr.removeModifier(id);
        }
    }
}
