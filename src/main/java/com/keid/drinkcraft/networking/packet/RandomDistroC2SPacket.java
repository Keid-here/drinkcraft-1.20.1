package com.keid.drinkcraft.networking.packet;

import com.keid.drinkcraft.server.RandomDistributor;
import com.keid.drinkcraft.util.IEntityDataSaver;
import com.keid.drinkcraft.util.SipsHelper;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class RandomDistroC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        System.out.println("RandomDistorC2SPacket received");

        int sips = buf.readInt();

        RandomDistributor.go(player, sips);

        // outputting the current thirst level of player
        player.sendMessage(Text.literal("Sips: " + ((IEntityDataSaver) player).getPersistentData().getInt("sips"))
                .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
    }
}
