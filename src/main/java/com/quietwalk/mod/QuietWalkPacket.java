package com.quietwalk.mod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record QuietWalkPacket(boolean quietWalking) {

    public static void encode(QuietWalkPacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.quietWalking);
    }

    public static QuietWalkPacket decode(FriendlyByteBuf buf) {
        return new QuietWalkPacket(buf.readBoolean());
    }

    public static void handle(QuietWalkPacket msg, Supplier<NetworkEvent.Context> ctx) {
        var context = ctx.get();
        context.enqueueWork(() -> {
            var sender = context.getSender();
            if (sender != null)
                QuietWalkState.setQuietWalking(sender.getUUID(), msg.quietWalking());
        });
        context.setPacketHandled(true);
    }
}
