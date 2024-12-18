package com.keid.drinkcraft.util;

import com.keid.drinkcraft.server.RandomDistributor;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.concurrent.ThreadLocalRandom;

public class GamblingHelper {

    public static void gamble(ServerPlayerEntity player) {

        int min = 1;
        int max = 100;

        int random = ThreadLocalRandom.current().nextInt(min, max + 1);

        System.out.println(random);

        if (isBetween(random, 1, 10)){
            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled Chaos!").formatted(Formatting.BOLD), false);
            RandomDistributor.trueRandpom(player, (ThreadLocalRandom.current().nextInt(1, 10)));
        }
        else if (isBetween(random, 11, 20)){
            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " fucked someone over!!").formatted(Formatting.DARK_GREEN), false);
            RandomDistributor.go(player, (ThreadLocalRandom.current().nextInt(1, 7)));
            }
        else if (isBetween(random, 21, 30)){
            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled and lost").formatted(Formatting.RED), false);
            SipsHelperNew.addSips(player, (ThreadLocalRandom.current().nextInt(1, 10)));
        }
        else if (isBetween(random, 31, 35)){
            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free diamonds").formatted(Formatting.GOLD), false);
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 10); i++) {
            ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:diamond")));
            boolean wasAdded = player.getInventory().insertStack(diamond);
            if (!wasAdded) {
                // If the player's inventory is full, drop the diamond at the player's location
                player.dropItem(diamond, false); }
            }
        }
        else if (isBetween(random, 36, 50)){
            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free KFC").formatted(Formatting.YELLOW), false);
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(8, 32); i++) {
                ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:cooked_chicken")));
                boolean wasAdded = player.getInventory().insertStack(diamond);
                if (!wasAdded) {
                    // If the player's inventory is full, drop the diamond at the player's location
                    player.dropItem(diamond, false); }
            }
        }
        else if(isBetween(random, 51, 60)){
            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " doubled their points").formatted(Formatting.GOLD), false);
            SipsHelperNew.addPoints(player, 60);
        }
        else if (isBetween(random, 61, 70)){
            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free KFC").formatted(Formatting.YELLOW), false);
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(8, 32); i++) {
                ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:cooked_chicken")));
                boolean wasAdded = player.getInventory().insertStack(diamond);
                if (!wasAdded) {
                    // If the player's inventory is full, drop the diamond at the player's location
                    player.dropItem(diamond, false); }
            }
        }
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;}

}
