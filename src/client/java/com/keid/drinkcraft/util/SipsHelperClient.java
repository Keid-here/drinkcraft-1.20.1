package com.keid.drinkcraft.util;

import com.keid.drinkcraft.DrinkcraftClient;

public class SipsHelperClient {
    //xddShrug
    public static void syncClientSips(int sips){
        DrinkcraftClient.sipsInt = sips;
    }

    public static void  syncTotalSips(int totalSips){
        DrinkcraftClient.sipsTotal = totalSips;
    }

    public static void syncPoints(int points){
        DrinkcraftClient.points = points;
    }
}
