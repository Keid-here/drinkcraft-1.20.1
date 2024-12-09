package com.keid.drinkcraft.server;


import com.keid.drinkcraft.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import static com.keid.drinkcraft.networking.ModMessages.SIPS;
import static java.lang.Math.random;

public class RandomDistributor {
    public static void go(ServerPlayerEntity player, int sips) {

        Collection<ServerPlayerEntity> unfilteredCollection = PlayerLookup.all(player.getServer());

        Collection<ServerPlayerEntity> filteredCollection = filterCollection(unfilteredCollection, player);

        sendSips(randomPlayer(filteredCollection), sips);
    }

    //exclude player from Collection
    public static <ServerPlayerEntity> Collection<ServerPlayerEntity> filterCollection(Collection<ServerPlayerEntity> originalCollection,ServerPlayerEntity player) {
        Collection<ServerPlayerEntity> filteredCollection = new ArrayList<>();

        for (ServerPlayerEntity item : originalCollection) {
            if (!item.equals(player)) {
                filteredCollection.add(item);
            }
        }
        if (!filteredCollection.isEmpty()) {
                Collection<ServerPlayerEntity> onlyOne = new ArrayList<ServerPlayerEntity>(){{add(player);}};
            return onlyOne;
        }else
            return originalCollection;
    }

    //get random Player from now filtered List
    public static <T> T randomPlayer(Collection<T> coll) {
        int num = (int) (random() * coll.size());
        for(T t: coll) if (--num < 0) return t;
        throw new AssertionError();
    }

    //send sips Package
    public static void sendSips(ServerPlayerEntity player, int sips) {
        ServerPlayNetworking.send(player, SIPS, PacketByteBufs.create());
    }
}