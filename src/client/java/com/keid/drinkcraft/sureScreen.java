package com.keid.drinkcraft;

import com.keid.drinkcraft.networking.packetowo.SipsPacket;
import com.keid.drinkcraft.networking.packetowo.totalSipsResetPacket;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.Drinkcraft.MOD_ID;

public class sureScreen extends BaseUIModelScreen<FlowLayout> {


    public sureScreen(MainScreen mainScreen) {
        super(FlowLayout.class, DataSource.asset(new Identifier("drinkcraft", "sure")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {

        rootComponent.childById(ButtonComponent.class, "yes").onPress(button -> {
                    Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new totalSipsResetPacket(new Identifier(MOD_ID, "drinkcraftowonet")));
                    this.client.currentScreen.close();
                });

        rootComponent.childById(ButtonComponent.class, "no").onPress(button -> this.client.setScreen(new MainScreen()));
    }
}

