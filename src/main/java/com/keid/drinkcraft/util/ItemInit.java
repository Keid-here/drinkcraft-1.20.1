package com.keid.drinkcraft.util;

import com.keid.drinkcraft.Drinkcraft;
import com.keid.drinkcraft.items.TomeOfCascade;
import com.keid.drinkcraft.items.TomeOfRain;
import com.keid.drinkcraft.items.TomeOfWind;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

public class ItemInit implements ItemRegistryContainer {

    public static final Item TOMEOFCASCADE = new TomeOfCascade();

    public static final Item TOMEOFRAIN = new TomeOfRain();

    public static final Item TOMEOFWIND = new TomeOfWind();

}
