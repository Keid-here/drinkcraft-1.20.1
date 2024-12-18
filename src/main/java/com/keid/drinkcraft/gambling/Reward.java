package com.keid.drinkcraft.gambling;

import com.keid.drinkcraft.server.RandomDistributor;
import com.keid.drinkcraft.util.SipsHelperNew;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.concurrent.ThreadLocalRandom;

public class Reward {
    public static void go(int rolls, ServerPlayerEntity player){
        System.out.println("reward");

        for (int i = 0; i < rolls; i++) {
            System.out.println("rolled");
            //sips or items
            int min = 1;
            int max = 100;
            int randoms = ThreadLocalRandom.current().nextInt(min, max + 1);

            if (randoms >= 70){
                int mins = 1;
                int maxs = 100;
                int randomsA = ThreadLocalRandom.current().nextInt(mins, maxs + 1);
                int minsips = 1;
                int maxsips = 5;
                int randomSips = ThreadLocalRandom.current().nextInt(minsips, maxsips + 1);

                if (randomsA >= 50){
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " gave someone else sips").formatted(Formatting.BOLD), false);
                    RandomDistributor.go(player, randomSips);
                } else {
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " gave everyone else sips").formatted(Formatting.RED), false);
                    RandomDistributor.goAllButPlayer(player, randomSips);
                }
            }
            else {
                int minI = 1;
                int maxI = 9; // amount of rewards in the pool
                int randomItem = ThreadLocalRandom.current().nextInt(minI, maxI + 1);

                switch (randomItem){
                    case 1:
                        // cooked chicken
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free KFC").formatted(Formatting.YELLOW), false);
                        for (int ia = 0; ia < ThreadLocalRandom.current().nextInt(8, 32); ia++) {
                            ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:cooked_chicken")));
                            boolean wasAdded = player.getInventory().insertStack(diamond);
                            if (!wasAdded) {
                                // If the player's inventory is full, drop the diamond at the player's location
                                player.dropItem(diamond, false); }
                        }
                        break;

                    case 2:
                        // free diamonds
                            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free diamonds").formatted(Formatting.GOLD), false);
                            for (int ia = 0; ia < ThreadLocalRandom.current().nextInt(1, 10); ia++) {
                                ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:diamond")));
                                boolean wasAdded = player.getInventory().insertStack(diamond);
                                if (!wasAdded) {
                                    // If the player's inventory is full, drop the diamond at the player's location
                                    player.dropItem(diamond, false); }
                            }
                            break;

                    case 3:
                        //free totem of undying
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free totem of undying").formatted(Formatting.DARK_GREEN), false);
                        ItemStack totem = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:totem_of_undying")));
                        boolean wasAdded = player.getInventory().insertStack(totem);
                        if (!wasAdded) {
                            player.dropItem(totem, false); }
                        break;

                    case 4:
                        // free golden apple
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free golden apple").formatted(Formatting.GOLD), false);
                        ItemStack goldenApple = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:golden_apple")));
                        boolean wasAddedGoldenApple = player.getInventory().insertStack(goldenApple);
                        if (!wasAddedGoldenApple) {
                            player.dropItem(goldenApple, false); }
                        break;

                    case 5:
                        // free diamond pickaxe
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free diamond pickaxe").formatted(Formatting.GOLD), false);
                        ItemStack diamondPickaxe = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:diamond_pickaxe")));
                        boolean wasAddedDiamondPickaxe = player.getInventory().insertStack(diamondPickaxe);
                        if (!wasAddedDiamondPickaxe) {
                            player.dropItem(diamondPickaxe, false); }
                        break;

                        case 6:
                            //free diamond axe
                            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free diamond axe").formatted(Formatting.GOLD), false);
                            ItemStack diamondAxe = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:diamond_axe")));
                            boolean wasAddedDiamondAxe = player.getInventory().insertStack(diamondAxe);
                            if (!wasAddedDiamondAxe) {
                                player.dropItem(diamondAxe, false); }
                        break;

                    case 7:
                        // free diamonds
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free gold ingots").formatted(Formatting.GOLD), false);
                        for (int ia = 0; ia < ThreadLocalRandom.current().nextInt(16, 48); ia++) {
                            ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:gold_ingot")));
                            boolean wasAddedGold = player.getInventory().insertStack(diamond);
                            if (!wasAddedGold) {
                                // If the player's inventory is full, drop the diamond at the player's location
                                player.dropItem(diamond, false); }
                        }
                        break;

                        case 8:
                            //free tomb of wind
                            player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free tome of wind").formatted(Formatting.GOLD), false);
                            ItemStack tombOfWind = new ItemStack(Registries.ITEM.get(new Identifier("drinkcraft:tomeofwind")));
                            boolean wasAddedTombOfWind = player.getInventory().insertStack(tombOfWind);
                            if (!wasAddedTombOfWind) {
                                player.dropItem(tombOfWind, false); }
                            break;

                    case 9:
                        //free tome of rain
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free tome of rain").formatted(Formatting.GOLD), false);
                        ItemStack tomeOfRain = new ItemStack(Registries.ITEM.get(new Identifier("drinkcraft:tomeofrain")));
                        boolean wasAddedTomeOfRain = player.getInventory().insertStack(tomeOfRain);
                        if (!wasAddedTomeOfRain) {
                            player.dropItem(tomeOfRain, false); }





                }






                }


            }

        }
}
