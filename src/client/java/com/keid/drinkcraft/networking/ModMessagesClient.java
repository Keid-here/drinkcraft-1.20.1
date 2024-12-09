package com.keid.drinkcraft.networking;

import com.keid.drinkcraft.networking.packet.SipsSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessagesClient {
    public static final Identifier SIPSSYNCC  = new Identifier("sipssync");
    public static final Identifier RANDOMDIISTRO = new Identifier("randomdistro");

    public static void registerS2CMessages() {
        ClientPlayNetworking.registerGlobalReceiver(SIPSSYNCC, SipsSyncS2CPacket::receive);
    }
}
