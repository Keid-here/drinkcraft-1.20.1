package com.keid.drinkcraft.networking.packet;

import com.keid.drinkcraft.util.SipsHelper;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import com.keid.drinkcraft.util.IEntityDataSaver;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.keid.drinkcraft.util.SipsHelper.returnSync;


public class SipsSyncC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        returnSync(((IEntityDataSaver) player));

    }
}
