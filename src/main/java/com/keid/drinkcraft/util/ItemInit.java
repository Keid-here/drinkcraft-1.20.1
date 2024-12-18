package com.keid.drinkcraft.util;

import com.keid.drinkcraft.items.*;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;

public class ItemInit implements ItemRegistryContainer {

    public static final Item TOMEOFCASCADE = new TomeOfCascade();

    public static final Item TOMEOFRAIN = new TomeOfRain();

    public static final Item TOMEOFWIND = new TomeOfWind();

    public static final Item LUCKYTICKET = new LuckyTicket();

    public static final Item SUPERLUCKYTICKET = new SuperLuckyTicket();

}
