package com.keid.drinkcraft.items;

import blue.endless.jankson.annotation.Nullable;
import com.keid.drinkcraft.Drinkcraft;
import com.keid.drinkcraft.networking.packetowo.PointsSyncPacket;
import com.keid.drinkcraft.networking.packetowo.ScratchS2C;
import com.keid.drinkcraft.networking.packetowo.SipsPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.keid.drinkcraft.Drinkcraft.*;

public class LuckyTicket extends Item {

    public LuckyTicket() {
        super(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1).fireproof());
    }

    @Override
    @Environment(EnvType.CLIENT)
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        Drinkcraft.DRINKCRAFTOWOCHANNEL.serverHandle(user).send(new ScratchS2C("lucky", new Identifier(MOD_ID, "drinkcraftowonet")));

        ItemStack heldStack = user.getStackInHand(hand);
        heldStack.decrement(1);

        return TypedActionResult.success(heldStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("test your luck").formatted(Formatting.GOLD));
    }
}
