package com.keid.drinkcraft.networking.packet;

import com.keid.drinkcraft.util.SipsHelper;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import com.keid.drinkcraft.util.IEntityDataSaver;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;


public class SipsSyncC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {

        ((IEntityDataSaver) player).getPersistentData().putInt("sips", buf.readInt());

        int sips = buf.readInt();

        SipsHelper.syncSips(sips, player);

    }
}
