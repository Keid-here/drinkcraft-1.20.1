package com.keid.drinkcraft.networking.packet;

import com.keid.drinkcraft.DrinkcraftClient;
import com.keid.drinkcraft.util.IEntityDataSaver;
import com.keid.drinkcraft.util.SipsHelperClient;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.HorizontalAlignment;
import io.wispforest.owo.ui.core.Insets;
import io.wispforest.owo.ui.core.Positioning;
import io.wispforest.owo.ui.core.Sizing;
import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.DrinkcraftClient.*;


public class SipsSyncS2CPacket {

    public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {

        ((IEntityDataSaver) minecraftClient.player).getPersistentData().putInt("sips", packetByteBuf.readInt());

        //TEST
        // sync package confirm
        System.out.println("SipsSyncS2CPacket received");

       sipsInt = ((IEntityDataSaver) minecraftClient.player).getPersistentData().getInt("sips");

       //todo give total sips its own packet
       sipsTotal = ((IEntityDataSaver) minecraftClient.player).getPersistentData().getInt("sipsTotal");


       if (hudToggle == true) {
           Hud.remove(HUD);
           Hud.add(HUD, () ->
                   Containers.verticalFlow(Sizing.content(), Sizing.content())
                           .child(Components.label(
                                   Text.empty()
                                           .append(Text.literal("Sips: ").formatted(Formatting.AQUA))
                                           .append(String.valueOf(sipsInt))
                                           .fillStyle(Style.EMPTY.withColor(Formatting.AQUA))).horizontalTextAlignment(HorizontalAlignment.CENTER).shadow(true))
                           //.surface(Surface.flat(0x77000000).and(Surface.outline(0xFF121212)))
                           .padding(Insets.of(5))
                           .positioning(Positioning.relative(50, 5))
           );
       }


       //todo remove
       minecraftClient.player.sendMessage(Text.literal("Sips: " + ((IEntityDataSaver) minecraftClient.player).getPersistentData().getInt("sips"))
                .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);

    }
}


