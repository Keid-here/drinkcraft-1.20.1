package com.keid.drinkcraft.util;

import com.keid.drinkcraft.gambling.Jackpot;
import com.keid.drinkcraft.gambling.Neutral;
import com.keid.drinkcraft.gambling.Punishment;
import com.keid.drinkcraft.gambling.Reward;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PriceHelper {
    public static void go(String type, int amount, ServerPlayerEntity player){
        switch (type){
            case "punishment":
                player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " hit a punishment").formatted(Formatting.RED), false);
                Punishment.go(amount, player);
                break;
            case "neutral":
                player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " hit a neutral").formatted(Formatting.GRAY), false);
                Neutral.go(amount, player);
                break;
            case "reward":
                player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " hit a win").formatted(Formatting.LIGHT_PURPLE), false);
                Reward.go(amount, player);
                break;
            case "jackpot":
                player.getServer().getPlayerManager().broadcast(Text.literal(player.getEntityName() + " hit the jackpot!!").formatted(Formatting.GOLD).formatted(Formatting.BOLD), false);
                Jackpot.go(amount, player);
                break;
        }
    }
}
