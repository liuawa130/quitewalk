package com.quietwalk.mod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class QuietWalkNetwork {
    private static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(QuietWalkMod.MOD_ID, "main"),
        () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);

    public static void register() {
        CHANNEL.registerMessage(0, QuietWalkPacket.class,
            QuietWalkPacket::encode, QuietWalkPacket::decode, QuietWalkPacket::handle);
    }
}
