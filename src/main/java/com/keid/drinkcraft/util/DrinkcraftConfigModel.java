package com.keid.drinkcraft.util;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Config(name = "drinkcraft-config", wrapperName = "Drinkcraft_Config")
public class DrinkcraftConfigModel {
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int max_Sips_per_Player = 30;

    public int sips_on_Death = 10;

    public int sipsPerDiamond = 5;
}
