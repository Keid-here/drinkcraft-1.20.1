package com.keid.drinkcraft.gambling;

import com.keid.drinkcraft.effects.FishCurseEffect;
import com.keid.drinkcraft.effects.SuperInsomnia;
import com.keid.drinkcraft.server.RandomDistributor;
import com.keid.drinkcraft.util.SipsHelperNew;
import com.keid.drinkcraft.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.concurrent.ThreadLocalRandom;

import static com.keid.drinkcraft.Drinkcraft.*;

public class Punishment {
    public static void go(int rolls, ServerPlayerEntity player) {

        for (int i = 0; i < rolls; i++) {

            //sips or items
            int min = 1;
            int max = 100;
            int randoms = ThreadLocalRandom.current().nextInt(min, max + 1);

            if (randoms >= 90) {
                int minsips = 1;
                int maxsips = 5;
                int randomSips = ThreadLocalRandom.current().nextInt(minsips, maxsips + 1);

                player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled and lost").formatted(Formatting.RED), false);
                SipsHelperNew.addSips(player, randomSips);

            } else {
                int minI = 1;
                int maxI = 15; // amount of rewards in the pool
                int randomItem = ThreadLocalRandom.current().nextInt(minI, maxI + 1);

                switch (randomItem) {
                    case 1:
                        // drop inventory
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " stumbled and spilled their inventory").formatted(Formatting.RED), false);
                        player.getInventory().dropAll();
                        break;

                    case 2:
                        // slowness
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " got really tired").formatted(Formatting.RED), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.SLOWNESS, 1200, 2));
                        break;

                    case 3:
                        //reduce health
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " cut themselves on the sharp ticket paper").formatted(Formatting.RED), false);
                        player.setHealth(2);
                        break;

                    case 4:
                        // hunger
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " got very hungry").formatted(Formatting.RED), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.HUNGER, 1200, 3));
                        break;

                    case 5:
                        // bad omen
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " has a bad feeling").formatted(Formatting.RED), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.BAD_OMEN, 108000, 3));
                        break;

                    case 6:
                        //astronaut
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " hebt an").formatted(Formatting.RED), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.LEVITATION, 400, 1));
                        break;

                    case 7:
                        // blindness
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " lost their glasses").formatted(Formatting.GRAY), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.BLINDNESS, 1200, 1));
                        break;

                    case 8:
                        //free nighttime
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " set the time to night").formatted(Formatting.RED), false);
                        player.getServerWorld().setTimeOfDay(13000);
                        break;

                    case 9:
                        //free rain
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled epic rain").formatted(Formatting.DARK_BLUE), false);
                        player.getServerWorld().setWeather(0, 2400, true, false);
                        break;

                    case 10:
                        //jump challenge
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled the do not try to jump challenge!").formatted(Formatting.RED).formatted(Formatting.BOLD), false);
                        player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.JUMP_BOOST, 1200, 9999999));
                        break;

                    case 11:
                        //Curse of the Fish
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled Curse of the Fish, get to water QUICK!!!").formatted(Formatting.RED).formatted(Formatting.BOLD), false);
                        player.addStatusEffect(new StatusEffectInstance(FISHCURSE, 3600));
                        break;

                    case 12:
                        //Clumsy
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled Clumsy").formatted(Formatting.RED), false);
                        player.addStatusEffect(new StatusEffectInstance(CLUMSY, 3600));
                        break;

                    case 13:
                        //Super Insomnia
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled Super Insomnia").formatted(Formatting.RED), false);
                        player.addStatusEffect(new StatusEffectInstance(SUPERINSOMNIA, 24000));
                        break;
                    case 14:
                        //fragile
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled Fragile Curse, better not take damage").formatted(Formatting.RED), false);
                        player.setHealth(player.getHealth());
                        player.addStatusEffect(new StatusEffectInstance(FRAGILE, 24000));
                        break;
                    case 15:
                        //Curse of no decent
                        player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " rolled the Curse of no decent, better not go down anywhere").formatted(Formatting.RED), false);
                        player.addStatusEffect(new StatusEffectInstance(GREENTEA, 24000));
                        break;


                }


            }


        }

    }
}

