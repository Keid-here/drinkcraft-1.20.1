package com.keid.drinkcraft.util;

import com.keid.drinkcraft.Drinkcraft;
import com.keid.drinkcraft.networking.packetowo.PointsSyncPacket;
import com.keid.drinkcraft.networking.packetowo.SipsSyncPacket;
import com.keid.drinkcraft.networking.packetowo.SipsTotalC2S;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Collection;

import static com.keid.drinkcraft.Drinkcraft.CONFIG;
import static com.keid.drinkcraft.Drinkcraft.MOD_ID;
import static com.keid.drinkcraft.util.CustomSounds.ADDSOUND;


import com.keid.drinkcraft.util.CustomSounds;

public class SipsHelperNew {
    /**
     * adds sips to the player
     * @param player
     * @param amount amount of sips added
     * @return
     */
    public static int addSips(ServerPlayerEntity player, int amount) {
        var playerEntity = ((IEntityDataSaver) player);

        NbtCompound nbt = playerEntity.getPersistentData();

        int sips = nbt.getInt("sips");


        if(sips + amount >= CONFIG.max_Sips_per_Player()) {
            sips = CONFIG.max_Sips_per_Player();
        } else {
            sips += amount;
        }
        nbt.putInt("sips", sips);

        //add total Sips
        int sipsTotal = nbt.getInt("sipsTotal");
        sipsTotal += amount;
        nbt.putInt("sipsTotal", sipsTotal);

        // sync the data
        syncSips(sips, player);
        totalSipsSync(sipsTotal, player);

        player.getServer().getPlayerManager().broadcast(Text.literal("added " + amount + " sips to " + player.getEntityName()).formatted(Formatting.AQUA), false);
        player.playSound(ADDSOUND, SoundCategory.AMBIENT, 0.7f, 1f);
        return sips;
    }

    /**
     * removes sips from the player
     * @param player
     * @param amount amount of sips to remove
     * @return
     */
    public static int removeSips(ServerPlayerEntity player, int amount) {
        var playerEntity = ((IEntityDataSaver) player);

        NbtCompound nbt = playerEntity.getPersistentData();
        int sips = nbt.getInt("sips");
        if(sips - amount < 0) {
            sips = 0;
        } else {
            sips -= amount;

            int points = nbt.getInt("points");
            points ++;
            nbt.putInt("points", points);
            syncPoints(points, player);


            player.playSound(SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.AMBIENT, 0.1f, 1f);

        }
        nbt.putInt("sips", sips);

        syncSips(sips, player);



        return sips;
    }

    // sends sips to the player sipsInt to be displayed
    public static void syncSips(int sips, ServerPlayerEntity player) {
        Drinkcraft.DRINKCRAFTOWOCHANNEL.serverHandle(player).send(new SipsSyncPacket(sips, new Identifier(MOD_ID, "drinkcraftowonet")));
    }

    public static void returnSync(ServerPlayerEntity player){
        var playerEntity = ((IEntityDataSaver) player);

        NbtCompound nbt = playerEntity.getPersistentData();
        int sips = nbt.getInt("sips");

        syncSips(sips, player);
    }

    public static void totalSipsReset(ServerPlayerEntity player) {
        var playerEntity = ((IEntityDataSaver) player);

        NbtCompound nbt = playerEntity.getPersistentData();
        nbt.putInt("sipsTotal", 0);
        totalSipsSync(0, player);
        player.sendMessage(Text.literal("Total Sip Counter reset" ), true);
    }

    public static void addAllSips(MinecraftServer server, int amount) {
        Collection<ServerPlayerEntity> allPlayersCollection = PlayerLookup.all(server);

        for (ServerPlayerEntity player : allPlayersCollection) {
            addSips(player, amount);
        }
        PlayerManager playerManager = server.getPlayerManager();
        playerManager.broadcast(Text.literal("added " + amount + " sips to everyone").formatted(Formatting.RED), false);
    }

    public static void totalSipsSync(int sips, ServerPlayerEntity player) {
        Drinkcraft.DRINKCRAFTOWOCHANNEL.serverHandle(player).send(new SipsTotalC2S(sips, new Identifier(MOD_ID, "drinkcraftowonet")));
    }

    public static void getAndSyncTotalSips(ServerPlayerEntity player) {
        var playerEntity = ((IEntityDataSaver) player);

        NbtCompound nbt = playerEntity.getPersistentData();
        int totalSips = nbt.getInt("sipsTotal");

        totalSipsSync(totalSips, player);
    }

    //Points section

    public static void syncPoints(int points, ServerPlayerEntity player) {
        Drinkcraft.DRINKCRAFTOWOCHANNEL.serverHandle(player).send(new PointsSyncPacket(points, new Identifier(MOD_ID, "drinkcraftowonet")));
    }

    public static void returnSyncPoints(ServerPlayerEntity player){
        var playerEntity = ((IEntityDataSaver) player);

        NbtCompound nbt = playerEntity.getPersistentData();
        int points = nbt.getInt("points");

        syncPoints(points, player);
    }
}

