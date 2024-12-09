package com.keid.drinkcraft;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.FlowLayout;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class GiveScreen extends BaseUIModelScreen<FlowLayout> {
    public GiveScreen(MainScreen mainScreen) {
        super(FlowLayout.class, DataSource.asset(new Identifier("drinkcraft", "give_ui_model")));
    }
    @Override
    protected void build(FlowLayout rootComponent){

    }

}
