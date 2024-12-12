package com.keid.drinkcraft.util;

import com.keid.drinkcraft.server.RandomDistributor;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.registry.Registries;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.Drinkcraft.CONFIG;
import static com.keid.drinkcraft.Drinkcraft.server;

public class CustomBlocksFactory {

    public static void registerCustomBlocks() {
        String input = CONFIG.custom_blocks();

        String[] blocksSplit = splitString(input);

        for (String block : blocksSplit) {
            String[] elements = block.split(":");

            String blockNameSpace = elements[0];
            String blockName = elements[1];
            int sips = Integer.parseInt(elements[2]);
            registerBlockEvents(blockNameSpace, blockName, sips);
        }
    }

    public static String[] splitString(String input) { if (input == null || !input.contains(":")) {
        throw new IllegalArgumentException("Input must be in the form 'namespace:item'");
    }
        return input.split(",");
    }

    public static void registerBlockEvents(String id, String name, int sips) {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
            if (state.getBlock() == Registries.BLOCK.get(Identifier.of(id,name))) {

                PlayerManager playerManager = server.getPlayerManager();
                playerManager.broadcast((Text.literal(player.getEntityName() + " found ").append(Text.translatable(Registries.BLOCK.get(Identifier.of(id,name)).getTranslationKey())).formatted(Formatting.AQUA)), false);


                ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
                server.execute(() -> {
                    RandomDistributor.go(playerEntity, sips);
                });
            }
        });
    }
}

