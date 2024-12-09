package com.keid.drinkcraft.server;

import com.keid.drinkcraft.Drinkcraft;
import com.keid.drinkcraft.PlayerData;
import com.keid.drinkcraft.StateSaverAndLoader;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import static com.keid.drinkcraft.Drinkcraft.*;

public class EventHandler {

    public static void increase(PlayerEntity player) {

            PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
            playerState.sipsPending+= 1;

            // Send a packet to the client
            MinecraftServer server = world.getServer();

            PacketByteBuf data = PacketByteBufs.create();
            data.writeInt(playerState.sipsPending);

            ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
            server.execute(() -> {
                ServerPlayNetworking.send(playerEntity, TOTAL_SIPS, data);
            });
    }
}
