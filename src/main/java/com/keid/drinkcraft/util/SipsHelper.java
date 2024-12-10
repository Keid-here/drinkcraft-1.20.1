package com.keid.drinkcraft.util;

import io.wispforest.owo.config.annotation.Config;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.message.SentMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;

import java.util.Collection;

import static com.keid.drinkcraft.Drinkcraft.CONFIG;
import static com.keid.drinkcraft.networking.ModMessages.SIPS;
import static com.keid.drinkcraft.networking.ModMessages.SIPSSYNC;

public class SipsHelper {
    public static int addSips(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int sips = nbt.getInt("sips");
        if(sips + amount >= CONFIG.max_Sips_per_Player()) {
            sips = 30;
        } else {
            sips += amount;
        }

        nbt.putInt("sips", sips);

        //todo make own helper for total
        //add to total sips
        //int sipsTotal = nbt.getInt("sipsTotal");
        //sipsTotal += amount;
        //nbt.putInt("sipsTotal", sipsTotal);


        //send message in chat
        ServerPlayerEntity guy = (ServerPlayerEntity) player;
        MinecraftServer server = ((ServerPlayerEntity) player).getServer();

        // sync the data
        syncSips(sips, (ServerPlayerEntity) player);

        // todo put somewhere else
        // syncTotalSipsSender(sipsTotal, (ServerPlayerEntity) player);

        //server.sendMessage(Text.literal("sent " + amount + " sips to " + guy.getEntityName()).formatted(Formatting.AQUA, Formatting.BOLD));
        PlayerManager playerManager = server.getPlayerManager();
        playerManager.broadcast(Text.literal("added " + amount + " sips to " + guy.getEntityName()).formatted(Formatting.AQUA), false);

        //TEST
        // only here for copy paste reasons xD
        //((ServerPlayerEntity) player).sendMessage(Text.literal("sent " + amount + " sips to " + guy.getEntityName()).formatted(Formatting.AQUA, Formatting.BOLD), false);

        return sips;
    }

    public static int removeSips(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int sips = nbt.getInt("sips");
        if(sips - amount < 0) {
            sips = 0;
        } else {
            sips -= amount;
        }
        nbt.putInt("sips", sips);

        syncSips(sips, (ServerPlayerEntity) player);

        return sips;
    }

    public static void syncSips(int sips, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(sips);
        ServerPlayNetworking.send(player, SIPSSYNC, buffer);
    }

    public static void syncTotalSipsSender(int sipstotal, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(sipstotal);
        ServerPlayNetworking.send(player, SIPSSYNC, buffer);
    }

    public static void returnSync(IEntityDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        int sips = nbt.getInt("sips");

        syncSips(sips, (ServerPlayerEntity) player);
    }

    public static void totalSipsReset(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt("sipsTotal", 0);
        syncTotalSipsSender(0, (ServerPlayerEntity) player);
    }

    public static void addAllSips(MinecraftServer server, int amount) {

        Collection<ServerPlayerEntity> allPlayersCollection = PlayerLookup.all(server);

        for (ServerPlayerEntity item : allPlayersCollection) {
            addSips((IEntityDataSaver) item, amount);
        }

        PlayerManager playerManager = server.getPlayerManager();
        playerManager.broadcast(Text.literal("added " + amount + " sips to everyone").formatted(Formatting.RED), false);

    }
}
