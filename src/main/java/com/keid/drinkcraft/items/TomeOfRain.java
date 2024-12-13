package com.keid.drinkcraft.items;

import blue.endless.jankson.annotation.Nullable;
import com.keid.drinkcraft.Drinkcraft;
import com.keid.drinkcraft.networking.packetowo.SipsPacket;
import com.keid.drinkcraft.util.ItemInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.server.PlayerManager;
import net.minecraft.util.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.keid.drinkcraft.Drinkcraft.*;
import static com.keid.drinkcraft.Drinkcraft.CONFIG;

public class TomeOfRain extends Item{

    public TomeOfRain() {
        super(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1).fireproof());
    }

    @Override
    @Environment(EnvType.CLIENT)
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        int min = CONFIG.min_Sips_per_Rain();
        int max = CONFIG.max_Sips_per_Rain();

        int randomsips = ThreadLocalRandom.current().nextInt(min, max + 1);


        PlayerManager playerManager = server.getPlayerManager();
        playerManager.broadcast(Text.literal("The Tome of Rain was used").formatted(Formatting.BLUE), false);

        System.out.println("used");
        Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new SipsPacket("random", randomsips, new Identifier(MOD_ID, "drinkcraftowonet")));

        ItemStack heldStack = user.getStackInHand(hand);
        heldStack.decrement(1);

        return TypedActionResult.success(heldStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("make someone else drink").formatted(Formatting.BLUE));
    }


}
