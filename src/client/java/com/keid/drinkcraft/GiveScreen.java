package com.keid.drinkcraft;

import com.keid.drinkcraft.networking.packetowo.SipsPacket;
import com.keid.drinkcraft.networking.packetowo.shopPacket;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.Drinkcraft.CONFIG;
import static com.keid.drinkcraft.Drinkcraft.MOD_ID;
import static com.keid.drinkcraft.DrinkcraftClient.points;
import static com.keid.drinkcraft.DrinkcraftClient.sipsInt;

public class GiveScreen extends BaseUIModelScreen<FlowLayout> {
    public GiveScreen(MainScreen mainScreen) {
        super(FlowLayout.class, DataSource.asset(new Identifier("drinkcraft", "give_ui_model")));
    }
    @Override
    protected void build(FlowLayout rootComponent){

        rootComponent.childById(ButtonComponent.class, "buy_lucky").onPress(button -> {
            System.out.println("buy lucky");
            Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new shopPacket("lucky", new Identifier(MOD_ID, "drinkcraftowonet")));
            this.client.currentScreen.close();
                });


        rootComponent.childById(ButtonComponent.class, "buy_superlucky").onPress(button -> {
            System.out.println("buy superlucky");
            Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new shopPacket("superlucky", new Identifier(MOD_ID, "drinkcraftowonet")));
            this.client.currentScreen.close();
        });


        rootComponent.childById(ButtonComponent.class, "buy_lucky").setMessage(Text.empty().append(String.valueOf(CONFIG.lucky_ticket_price())));
        rootComponent.childById(ButtonComponent.class, "buy_superlucky").setMessage(Text.empty().append(String.valueOf(CONFIG.superlucky_ticket_price())));

        rootComponent.childById(ButtonComponent.class, "buy_superlucky").active(points >= CONFIG.superlucky_ticket_price());
        rootComponent.childById(ButtonComponent.class, "buy_lucky").active(points >= CONFIG.lucky_ticket_price());



        rootComponent.childById(ButtonComponent.class, "overview_button").onPress(button -> this.client.setScreen(new MainScreen()));

    }

}
