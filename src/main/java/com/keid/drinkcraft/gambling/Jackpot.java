package com.keid.drinkcraft.gambling;

import com.keid.drinkcraft.server.RandomDistributor;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.concurrent.ThreadLocalRandom;

public class Jackpot {
    public static void go(int rolls, ServerPlayerEntity player) {



        int minsips = 5;
        int maxsips = 10;
        int randomSips = ThreadLocalRandom.current().nextInt(minsips, maxsips + 1);

        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " gave everyone but themselves sips").formatted(Formatting.BOLD).formatted(Formatting.GOLD), false);
        RandomDistributor.goAllButPlayer(player, randomSips);


        for (int i = 0; i < rolls; i++) {

            int minI = 1;
            int maxI = 6; // amount of rewards in the pool
            int randomItem = ThreadLocalRandom.current().nextInt(minI, maxI + 1);

            switch (randomItem) {
                case 1:
                    // free netherite
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + "rolled netherite").formatted(Formatting.DARK_GREEN), false);
                    for (int ia = 0; ia < ThreadLocalRandom.current().nextInt(1, 5); ia++) {
                        ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:netherite_ingot")));
                        boolean wasAdded = player.getInventory().insertStack(diamond);
                        if (!wasAdded) {
                            // If the player's inventory is full, drop the diamond at the player's location
                            player.dropItem(diamond, false);
                        }
                    }
                    break;

                case 2:
                    // free elytra
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled an elytra").formatted(Formatting.GOLD), false);
                    ItemStack diamondAxe = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:elytra")));
                    boolean wasAddedDiamondAxe = player.getInventory().insertStack(diamondAxe);
                    if (!wasAddedDiamondAxe) {
                        player.dropItem(diamondAxe, false);
                    }
                    break;

                case 3:
                    //free effects
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled steroids").formatted(Formatting.GOLD), false);
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.STRENGTH, 24000, 4));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.REGENERATION, 24000, 3));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.ABSORPTION, 24000, 3));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 24000, 3));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.SPEED, 24000, 1));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.SATURATION, 24000, 5));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.HASTE, 24000, 3));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 24000, 4));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.RESISTANCE, 24000, 3));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.NIGHT_VISION, 24000, 2));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.WATER_BREATHING, 24000, 2));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 24000, 2));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.LUCK, 24000, 10));
                    break;

                case 4:
                    // free diamonds
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled a lot of diamonds").formatted(Formatting.GOLD), false);
                    for (int ia = 0; ia < ThreadLocalRandom.current().nextInt(32, 128); ia++) {
                        ItemStack diamond = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:diamond")));
                        boolean wasAdded = player.getInventory().insertStack(diamond);
                        if (!wasAdded) {
                            // If the player's inventory is full, drop the diamond at the player's location
                            player.dropItem(diamond, false);
                        }
                    }
                    break;

                case 5:
                    // free jump
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " leveled up").formatted(Formatting.GOLD), false);
                    player.addExperienceLevels(150);
                    break;

                case 6:
                    //free nether star
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled a free nether star").formatted(Formatting.GOLD), false);
                    ItemStack diamondAxeA = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:nether_star")));
                    boolean wasAddedDiamondAxeA = player.getInventory().insertStack(diamondAxeA);
                    if (!wasAddedDiamondAxeA) {
                        player.dropItem(diamondAxeA, false);
                    }
                    break;

                case 7:
                    // free dead bushes
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled free dead bushes").formatted(Formatting.GRAY), false);
                    break;

                case 8:
                    //free tomb of wind
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled a singular nether brick").formatted(Formatting.GRAY), false);
                    ItemStack tombOfWind = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:netherbrick")));
                    boolean wasAddedTombOfWind = player.getInventory().insertStack(tombOfWind);
                    if (!wasAddedTombOfWind) {
                        player.dropItem(tombOfWind, false);
                    }
                    break;

                case 9:
                    //free tome of rain
                    player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled a free pufferfish (dead)").formatted(Formatting.GRAY), false);
                    ItemStack tomeOfRain = new ItemStack(Registries.ITEM.get(new Identifier("minecraft:fish")));
                    boolean wasAddedTomeOfRain = player.getInventory().insertStack(tomeOfRain);
                    if (!wasAddedTomeOfRain) {
                        player.dropItem(tomeOfRain, false);
                    }


            }
        }
    }
}


