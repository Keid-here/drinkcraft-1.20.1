package com.keid.drinkcraft.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;


public class ExampleC2SPacket {
    // Everything here happens ONLY on the Server!
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        System.out.println("ExampleC2SPacket received");
    }
}
