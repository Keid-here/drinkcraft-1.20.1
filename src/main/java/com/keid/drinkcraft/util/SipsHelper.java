package com.keid.drinkcraft.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.keid.drinkcraft.networking.ModMessages.SIPS;
import static com.keid.drinkcraft.networking.ModMessages.SIPSSYNC;

public class SipsHelper {
    public static int addSips(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int sips = nbt.getInt("sips");
        if(sips + amount >= 30) {
            sips = 30;
        } else {
            sips += amount;
        }

        nbt.putInt("sips", sips);

        // sync the data
        syncSips(sips, (ServerPlayerEntity) player);

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
}
