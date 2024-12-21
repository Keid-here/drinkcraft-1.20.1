package com.keid.drinkcraft.items;

import blue.endless.jankson.annotation.Nullable;
import com.keid.drinkcraft.Drinkcraft;
import com.keid.drinkcraft.networking.packetowo.ScratchS2C;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

import static com.keid.drinkcraft.Drinkcraft.MOD_ID;
import static net.minecraft.item.ToolMaterials.NETHERITE;

public class KyoneticSword extends SwordItem {
    public KyoneticSword() {
        super(NETHERITE, 3, 1f,new Settings().rarity(Rarity.EPIC));
    }
    @Override
    public float getAttackDamage() {
        return 3F;
    }


    @Override
    public int getEnchantability() {
        return 20;
    }

    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.DIAMOND);

    }

    public int getDurability() {
        return 455;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("how did we get here?").formatted(Formatting.LIGHT_PURPLE));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (!user.isOnGround()){
            System.out.println("liftoff");
            if (!user.hasNoGravity()) {
                user.setNoGravity(true);
            } else {
                user.setNoGravity(false);
                user.addVelocity(new Vec3d(0.5, 20, 0.5));
            }
        }


        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
