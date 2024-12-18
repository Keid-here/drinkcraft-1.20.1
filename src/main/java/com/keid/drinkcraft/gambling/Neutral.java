package com.keid.drinkcraft.gambling;

import com.keid.drinkcraft.server.RandomDistributor;
import com.keid.drinkcraft.util.SipsHelperNew;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.concurrent.ThreadLocalRandom;

public class Neutral {
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

                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " gave someone sips").formatted(Formatting.BOLD), false);
                    RandomDistributor.trueRandpom(player, randomSips);

            }
            else {
                int minI = 1;
                int maxI = 9; // amount of rewards in the pool
                int randomItem = ThreadLocalRandom.current().nextInt(minI, maxI + 1);

                switch (randomItem){
                    case 1:
                        // free planks
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " got wood").formatted(Formatting.DARK_GREEN), false);
                        for (int ia = 0; ia < ThreadLocalRandom.current().nextInt(24, 48); ia++) {
                            ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:oak_planks")));
                            boolean wasAdded = player.getInventory().insertStack(diamond);
                            if (!wasAdded) {
                                // If the player's inventory is full, drop the diamond at the player's location
                                player.dropItem(diamond, false); }
                        }
                        break;

                    case 2:
                        // free sticks
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled hundreds of sticks").formatted(Formatting.GOLD), false);
                        for (int ia = 0; ia < ThreadLocalRandom.current().nextInt(100, 300); ia++) {
                            ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:stick")));
                            boolean wasAdded = player.getInventory().insertStack(diamond);
                            if (!wasAdded) {
                                // If the player's inventory is full, drop the diamond at the player's location
                                player.dropItem(diamond, false); }
                        }
                        break;

                    case 3:
                        //free saturation
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled saturation").formatted(Formatting.DARK_GREEN), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.SATURATION, 1200, 2));
                        break;

                    case 4:
                        // free healing
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " healing").formatted(Formatting.GRAY), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.REGENERATION, 100, 2));
                        break;

                    case 5:
                        // free jump
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled bunny legs").formatted(Formatting.GRAY), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.JUMP_BOOST, 2400, 2));
                        break;

                    case 6:
                        //free stone axe
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free stone axe").formatted(Formatting.GRAY), false);
                        ItemStack diamondAxe = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:stone_axe")));
                        boolean wasAddedDiamondAxe = player.getInventory().insertStack(diamondAxe);
                        if (!wasAddedDiamondAxe) {
                            player.dropItem(diamondAxe, false); }
                        break;

                    case 7:
                        // free dead bushes
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free dead bushes").formatted(Formatting.GRAY), false);
                        for (int ia = 0; ia < ThreadLocalRandom.current().nextInt(16, 48); ia++) {
                            ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:dead_bush")));
                            boolean wasAddedGold = player.getInventory().insertStack(diamond);
                            if (!wasAddedGold) {
                                // If the player's inventory is full, drop the diamond at the player's location
                                player.dropItem(diamond, false); }
                        }
                        break;

                    case 8:
                        //free tomb of wind
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled a singular nether brick").formatted(Formatting.GRAY), false);
                        ItemStack tombOfWind = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:nether_brick")));
                        boolean wasAddedTombOfWind = player.getInventory().insertStack(tombOfWind);
                        if (!wasAddedTombOfWind) {
                            player.dropItem(tombOfWind, false); }
                        break;

                    case 9:
                        //free tome of rain
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled a free pufferfish (dead)").formatted(Formatting.GRAY), false);
                        ItemStack tomeOfRain = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:pufferfish")));
                        boolean wasAddedTomeOfRain = player.getInventory().insertStack(tomeOfRain);
                        if (!wasAddedTomeOfRain) {
                            player.dropItem(tomeOfRain, false); }





                }






            }


        }

    }
}
