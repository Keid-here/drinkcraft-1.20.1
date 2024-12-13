package com.keid.drinkcraft.util;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Config(name = "drinkcraft-config", wrapperName = "Drinkcraft_Config")
public class DrinkcraftConfigModel {
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int max_Sips_per_Player = 30;

    public int sips_on_Death = 10;

    public float tome_chance = 0.1f;
    public float tome_mobchance = 0.05f;

    //Blocks by id and sips to be added. must be in the correct form: "modid_id:block_id:sips,..."
    public String custom_blocks = "minecraft:diamond_ore:5,minecraft:deepslate_diamond_ore:5,minecraft:deepslate_emerald_ore:10,minecraft:emerald_ore:5,minecraft:ancient_debris:10";

    public int max_Sips_per_Cascade = 13;
    public int min_Sips_per_Cascade = 3;
    public int max_Sips_per_Rain = 10;
    public int min_Sips_per_Rain = 2;
    public int max_Sips_per_Wind = 11;
    public int min_Sips_per_Wind = 1;
}
