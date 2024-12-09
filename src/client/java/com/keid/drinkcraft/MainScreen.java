package com.keid.drinkcraft;


import com.keid.drinkcraft.networking.ModMessages;
import com.keid.drinkcraft.server.EventHandler;
import com.keid.drinkcraft.util.IEntityDataSaver;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;


public class MainScreen extends BaseUIModelScreen<FlowLayout> {


    public MainScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("drinkcraft", "main_ui_model")));
    }


    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "the-button").onPress(button -> {
            System.out.println("click");

            ClientPlayNetworking.send(ModMessages.SIPS, PacketByteBufs.create());
        });

        rootComponent.childById(ButtonComponent.class, "Sip_Drinking_B").onPress(button -> {
            System.out.println("click 2");

            ClientPlayNetworking.send(ModMessages.SIPSDEC, PacketByteBufs.create());
        });

        rootComponent.childById(ButtonComponent.class, "Sip_Drinking_R").onPress(button -> {
            System.out.println("click 3");

            PacketByteBuf sips = PacketByteBufs.create();
            sips.writeInt(10);

            ClientPlayNetworking.send(ModMessages.RANDOMDIISTRO, sips);
        });

        rootComponent.childById(LabelComponent.class, "sipsCounter").text(Text.empty()
                .append(Text.literal("Sips left to drink: "+ (((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("sips")))));


        rootComponent.childById(ButtonComponent.class, "next_menu").onPress(button -> this.client.setScreen(new GiveScreen(this)));


        /*var binding1 = KeyBindingHelper.getBoundKeyOf()

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBinding. wasPressed()) {
                MinecraftClient.getInstance().setScreen(new MainScreen());
            }
        }); */


    }

}
