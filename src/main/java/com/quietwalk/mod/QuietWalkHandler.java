package com.quietwalk.mod;

import com.quietwalk.mod.config.QuietWalkConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class QuietWalkHandler {

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        Player player = event.player;

        Minecraft mc = Minecraft.getInstance();
        if (player != mc.player) return;

        boolean sneaking = player.isShiftKeyDown();
        boolean moving = mc.options.keyUp.isDown() || mc.options.keyDown.isDown()
                || mc.options.keyLeft.isDown() || mc.options.keyRight.isDown();
        boolean quietWalking = Screen.hasControlDown() && !sneaking
                && (!QuietWalkConfig.REQUIRE_MOVEMENT_INPUT.get() || moving);

        boolean was = QuietWalkState.isQuietWalking(player.getUUID());
        QuietWalkState.setQuietWalking(player.getUUID(), quietWalking);
        if (quietWalking != was)
            QuietWalkNetwork.CHANNEL.sendToServer(new QuietWalkPacket(quietWalking));
    }
}
