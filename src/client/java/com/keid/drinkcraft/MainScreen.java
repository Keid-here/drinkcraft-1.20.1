package com.keid.drinkcraft;


import com.keid.drinkcraft.networking.packetowo.SipsPacket;
import com.keid.drinkcraft.networking.packetowo.totalSipsResetPacket;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.Drinkcraft.MOD_ID;
import static com.keid.drinkcraft.DrinkcraftClient.*;


public class MainScreen extends BaseUIModelScreen<FlowLayout> {


    public MainScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("drinkcraft", "main_ui_model")));
    }


    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "the-button").onPress(button -> {
            Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new SipsPacket("add", 1, new Identifier(MOD_ID, "drinkcraftowonet")));
            rootComponent.childById(LabelComponent.class, "sipsCounter").text(Text.empty().append("Sips left to drink: " + sipsInt));
        });

        rootComponent.childById(ButtonComponent.class, "Sip_Drinking_B").onPress(button -> {
            Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new SipsPacket("remove", 1, new Identifier(MOD_ID, "drinkcraftowonet")));
            rootComponent.childById(LabelComponent.class, "sipsCounter").text(Text.empty().append("Sips left to drink: " + sipsInt));
        });

        rootComponent.childById(ButtonComponent.class, "Sip_Drinking_R").onPress(button -> {
            int sips = 10;
            Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new SipsPacket("random", sips, new Identifier(MOD_ID, "drinkcraftowonet")));
            rootComponent.childById(LabelComponent.class, "sipsCounter").text(Text.empty().append("Sips left to drink: " + sipsInt));
        });

        rootComponent.childById(ButtonComponent.class, "totalSipsReset").onPress(button -> {
            Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new totalSipsResetPacket(new Identifier(MOD_ID, "drinkcraftowonet")));
            rootComponent.childById(LabelComponent.class, "totalSipsCounter").text(Text.empty().append("total sips: 0"));
        });


        rootComponent.childById(LabelComponent.class, "sipsCounter").text(Text.empty()
                .append(Text.literal("Sips left to drink: "))
                .append(String.valueOf(sipsInt))
        );
        rootComponent.childById(LabelComponent.class, "totalSipsCounter").text(Text.empty()
                .append(Text.literal("total sips: "))
                .append(String.valueOf(sipsTotal))
        );
        rootComponent.childById(LabelComponent.class, "pointsCounter").text(Text.empty()
                .append(String.valueOf(points))
                .append(Text.literal(" Points"))

        );


        //.text(Text.empty()
        //      .append(Text.literal("Sips left to drink: "+ (((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("sips")))));


        rootComponent.childById(ButtonComponent.class, "next_menu").onPress(button -> this.client.setScreen(new GiveScreen(this)));
    }

    //public static void updateSips


}
