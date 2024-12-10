package com.keid.drinkcraft.networking;

import com.keid.drinkcraft.networking.packet.*;
import com.keid.drinkcraft.networking.packet.RandomDistroC2SPacket;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier EXAMPLE_ID  = new Identifier("example");
    public static final Identifier SIPS  = new Identifier("sips");
    public static final Identifier SIPSDEC  = new Identifier("sipsdec");
    public static final Identifier SIPSSYNC = new Identifier("sipssync");
    public static final Identifier RANDOMDIISTRO = new Identifier("randomdistro");
    public static final Identifier TOTALSIPSRESET = new Identifier("totalsipsreset");

    public static void registerC2SMessages() {
        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(SIPS, SipsC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(SIPSDEC, SipsDecC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(RANDOMDIISTRO, RandomDistroC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(SIPSSYNC, SipsSyncC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(TOTALSIPSRESET, SipsSyncC2SPacket::receive);
    }
}
