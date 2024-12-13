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

import static com.keid.drinkcraft.Drinkcraft.MOD_ID;
import static com.keid.drinkcraft.Drinkcraft.server;

public class TomeOfCascade extends Item{

    public TomeOfCascade() {
        super(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1).fireproof());
    }

    @Override
    @Environment(EnvType.CLIENT)
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        PlayerManager playerManager = server.getPlayerManager();
        playerManager.broadcast(Text.literal("The Tome of Cascade was used").formatted(Formatting.RED), false);

        System.out.println("used");
        Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new SipsPacket("all", 13, new Identifier(MOD_ID, "drinkcraftowonet")));

        ItemStack heldStack = user.getStackInHand(hand);
        heldStack.decrement(1);

        return TypedActionResult.success(heldStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("make everyone drink").formatted(Formatting.RED));
    }


}
