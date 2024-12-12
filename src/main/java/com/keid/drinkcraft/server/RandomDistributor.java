package com.keid.drinkcraft.server;


import com.keid.drinkcraft.util.IEntityDataSaver;
import com.keid.drinkcraft.util.SipsHelperNew;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class RandomDistributor {

    /**
     * Gives a random player on the server sips.
     * Player who is mentioned will never get the sips expect when nobody else is online
     *
     * @param player ServerPlayerEntity, (will be filtered out)
     * @param sips   amount of sips to distribute
     */
    public static void go(ServerPlayerEntity player, int sips) {

        Collection<ServerPlayerEntity> unfilteredCollection = PlayerLookup.all(player.getServer());

        //TEST send list of all players in chat
        //for (ServerPlayerEntity unfiltered : unfilteredCollection) {
        //    player.sendMessage(Text.literal(unfiltered.getEntityName()).formatted(Formatting.GREEN));
        //}

        Collection<ServerPlayerEntity> filteredCollection = filterCollection(unfilteredCollection, player);

        //TEST send filtered list in chat
        //for (ServerPlayerEntity filtered : filteredCollection) {
        //    player.sendMessage(Text.literal(filtered.getEntityName()).formatted(Formatting.DARK_RED));
        //}

        ServerPlayerEntity victim = getRandomPlayer(filteredCollection);

        //TEST
        //player.sendMessage(Text.literal(victim.getEntityName()).formatted(Formatting.GOLD));

        sendSips(victim, sips);
    }

    //exclude player from Collection
    public static <ServerPlayerEntity> Collection<ServerPlayerEntity> filterCollection(Collection<ServerPlayerEntity> originalCollection, ServerPlayerEntity player) {
        Collection<ServerPlayerEntity> filteredCollection = new ArrayList<>();

        for (ServerPlayerEntity item : originalCollection) {
            if (!item.equals(player)) {
                filteredCollection.add(item);
            }
        }
        if (!filteredCollection.isEmpty()) {
            return filteredCollection;

        } else {
            Collection<ServerPlayerEntity> onlyOne = new ArrayList<ServerPlayerEntity>() {{
                add(player);
            }};
            return onlyOne;
        }

    }

    //get random Player from now filtered List
    public static <T> T getRandomPlayer(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Collection must not be null or empty");
        }
        int randomIndex = new Random().nextInt(collection.size());
        return collection.stream().skip(randomIndex).findFirst().orElse(null);
    }


    //send sips Package
    public static void sendSips(ServerPlayerEntity player, int sips) {
        SipsHelperNew.addSips(player, sips);
    }
}