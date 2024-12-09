package com.keid.drinkcraft.networking;

import com.keid.drinkcraft.networking.packet.SipsSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessagesClient {
    public static final Identifier SIPSSYNC  = new Identifier("sipssync");
    public static final Identifier RANDOMDIISTRO = new Identifier("randomdistro");

    public static void registerS2CMessages() {
        ClientPlayNetworking.registerGlobalReceiver(SIPSSYNC, SipsSyncS2CPacket::receive);
    }
}
