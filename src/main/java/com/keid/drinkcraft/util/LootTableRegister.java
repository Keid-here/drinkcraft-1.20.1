package com.keid.drinkcraft.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.Drinkcraft.CONFIG;
import java.util.List;
import java.util.Arrays;

public class LootTableRegister {

    private static final Identifier JUNGLE_TEMPLE_ID =
            new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier MINESHAFT_ID =
            new Identifier("minecraft", "chests/abandoned_mineshaft");
    private static final Identifier BASTION_ID =
            new Identifier("minecraft", "chests/bastion_treasure");
    private static final Identifier BURRIED_ID =
            new Identifier("minecraft", "chests/buried_treasure");
    private static final Identifier PYRAMID_ID =
            new Identifier("minecraft", "chests/desert_pyramid");
    private static final Identifier WOODLAND_MANSION_ID =
            new Identifier("minecraft", "chests/woodland_mansion");
    private static final Identifier END_CITY_ID =
            new Identifier("minecraft", "chests/end_city_treasure");
    private static final Identifier PILLAGER_OUTPOST_ID =
            new Identifier("minecraft", "chests/pillager_outpost");
    private static final Identifier SHIPWRECK_ID =
            new Identifier("minecraft", "chests/shipwreck_supply");
    private static final Identifier STRONGHOLD_ID =
            new Identifier("minecraft", "chests/stronghold_library");
    private static final Identifier RUINED_PORTAL_ID =
            new Identifier("minecraft", "chests/ruined_portal");
    private static final Identifier SHIPWRECK_TREASURE_ID =
            new Identifier("minecraft", "chests/shipwreck_treasure");
    private static final Identifier SIMPLE_DUNGEON_ID =
            new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier STRONGHOLD_CROSSING_ID =
            new Identifier("minecraft", "chests/stronghold_crossing");
    private static final Identifier STRONGHOLD_CORRIDOR_ID =
            new Identifier("minecraft", "chests/stronghold_corridor");

    private static final Identifier VILLAGE_ID =
            new Identifier("minecraft", "chests/village/village_plains");
    private static final Identifier VILLAGE_SNOWY_ID =
            new Identifier("minecraft", "chests/village/village_snowy");
    private static final Identifier VILLAGE_TAIGA_ID =
            new Identifier("minecraft", "chests/village/village_taiga");
    private static final Identifier VILLAGE_DESERT_ID =
            new Identifier("minecraft", "chests/village/village_desert");
    private static final Identifier VILLAGE_SAVANNA_ID =
            new Identifier("minecraft", "chests/village/village_savanna");
    private static final Identifier VILLAGE_WEAPONSMA_ID =
            new Identifier("minecraft", "chests/village/village_weaponsmith");
    

    private static final Identifier CREEPER_ID =
            new Identifier("minecraft", "entities/creeper");
    private static final Identifier ZOMBIE_ID =
            new Identifier("minecraft", "entities/zombie");
    private static final Identifier SKELETON_ID =
            new Identifier("minecraft", "entities/skeleton");
    private static final Identifier SPIDER_ID =
            new Identifier("minecraft", "entities/spider");
    private static final Identifier ENDERMAN_ID =
            new Identifier("minecraft", "entities/enderman");
    private static final Identifier GHAST_ID =
            new Identifier("minecraft", "entities/ghast");
    private static final Identifier MAGMA_CUBE_ID =
            new Identifier("minecraft", "entities/magma_cube");
    private static final Identifier BLAZE_ID =
            new Identifier("minecraft", "entities/blaze");
    private static final Identifier PIGLIN_ID =
            new Identifier("minecraft", "entities/piglin");
    private static final Identifier PIGLIN_BRUTE_ID =
            new Identifier("minecraft", "entities/piglin_brute");
    private static final Identifier WITHER_SKELETON_ID =
            new Identifier("minecraft", "entities/wither_skeleton");
    private static final Identifier HOGLIN_ID =
            new Identifier("minecraft", "entities/hoglin");
    private static final Identifier ZOGLIN_ID =
            new Identifier("minecraft", "entities/zoglin");
    private static final Identifier SLIME_ID =
            new Identifier("minecraft", "entities/slime");
    private static final Identifier DROWNED_ID =
            new Identifier("minecraft", "entities/drowned");
    private static final Identifier WITCH_ID =
            new Identifier("minecraft", "entities/witch");
    private static final Identifier HUSK_ID =
            new Identifier("minecraft", "entities/husk");
    private static final Identifier STRAY_ID =
            new Identifier("minecraft", "entities/stray");
    private static final Identifier PHANTOM_ID =
            new Identifier("minecraft", "entities/phantom");
    private static final Identifier GUARDIAN_ID =
            new Identifier("minecraft", "entities/guardian");
    private static final Identifier EVOKER_ID =
            new Identifier("minecraft", "entities/evoker");
    private static final Identifier PILLAGER_ID =
            new Identifier("minecraft", "entities/pillager");
    private static final Identifier VINDICATOR_ID =
            new Identifier("minecraft", "entities/vindicator");
    private static final Identifier RAVAGER_ID =
            new Identifier("minecraft", "entities/ravager");
    

    public static float chance = CONFIG.tome_chance();
    public static float mobchance = CONFIG.tome_mobchance();


    // add CREEPER_ID for testing

    public static List<Identifier> getAllIdentifiers() {
        return Arrays.asList(
            JUNGLE_TEMPLE_ID, MINESHAFT_ID, BASTION_ID, BURRIED_ID,
            PYRAMID_ID, WOODLAND_MANSION_ID, END_CITY_ID, PILLAGER_OUTPOST_ID,
            SHIPWRECK_ID, STRONGHOLD_ID, RUINED_PORTAL_ID, SHIPWRECK_TREASURE_ID,
            SIMPLE_DUNGEON_ID, STRONGHOLD_CROSSING_ID, STRONGHOLD_CORRIDOR_ID,
            VILLAGE_ID, VILLAGE_SNOWY_ID, VILLAGE_TAIGA_ID, VILLAGE_DESERT_ID,
            VILLAGE_SAVANNA_ID, VILLAGE_WEAPONSMA_ID,
            CREEPER_ID, ZOMBIE_ID, SKELETON_ID, SPIDER_ID, ENDERMAN_ID,
            GHAST_ID, MAGMA_CUBE_ID, BLAZE_ID, PIGLIN_ID, PIGLIN_BRUTE_ID,
            WITHER_SKELETON_ID, HOGLIN_ID, ZOGLIN_ID, SLIME_ID, DROWNED_ID,
            WITCH_ID, HUSK_ID, STRAY_ID, PHANTOM_ID, GUARDIAN_ID, EVOKER_ID,
            PILLAGER_ID, VINDICATOR_ID, RAVAGER_ID
            );
    }
    
    public static List<Identifier> getAllMobIdentifiers() {
        return Arrays.asList(
            CREEPER_ID, ZOMBIE_ID, SKELETON_ID, SPIDER_ID, ENDERMAN_ID,
            GHAST_ID, MAGMA_CUBE_ID, BLAZE_ID, PIGLIN_ID, PIGLIN_BRUTE_ID,
            WITHER_SKELETON_ID, HOGLIN_ID, ZOGLIN_ID, SLIME_ID, DROWNED_ID,
            WITCH_ID, HUSK_ID, STRAY_ID, PHANTOM_ID, GUARDIAN_ID, EVOKER_ID,
            PILLAGER_ID, VINDICATOR_ID, RAVAGER_ID
        );
    }

    public static List<Item> allItems() {
        return Arrays.asList(
                ItemInit.TOMEOFCASCADE,ItemInit.TOMEOFRAIN,ItemInit.TOMEOFWIND );
    }


    public static void registerLootTables() {
        for (Item item : allItems()) {
            for (Identifier id : getAllIdentifiers()) {
                modifyLootTablesTomes(id,item, chance, 1.0f, 1.0f);
            }
        }
    }

    public static void registerMobLootTables() {
        for (Item item : allItems()) {
            for (Identifier id : getAllMobIdentifiers()) {
                modifyLootTablesTomes(id,item, mobchance, 1.0f, 1.0f);
            }
        }
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

        });
    }
}
