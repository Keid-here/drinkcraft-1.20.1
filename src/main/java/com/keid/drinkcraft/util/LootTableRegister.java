package com.keid.drinkcraft.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.Drinkcraft.CONFIG;

public class LootTableRegister {
    private static final Identifier JUNGLE_TEMPLE_ID =
            new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier CREEPER_ID =
            new Identifier("minecraft", "entities/creeper");

    public static float tomeOfCascadeChance = CONFIG.loot_chance_Tome_Of_Cascade();


    public static void registerLootTables() {


        modifyLootTablesTomes(JUNGLE_TEMPLE_ID,(ItemInit.TOMEOFCASCADE), tomeOfCascadeChance, 1.0f, 1.0f);
    }

    public static void modifyLootTablesTomes(Identifier loottable,ItemConvertible item, float chance, float min, float max) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(loottable.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(chance)) // 1f = Drops 100% of the time
                        .with(ItemEntry.builder(item))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)).build()); // between

                tableBuilder.pool(poolBuilder.build());
            }

            //todo delete, testing purposes only
            if(CREEPER_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ItemInit.TOMEOFCASCADE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
