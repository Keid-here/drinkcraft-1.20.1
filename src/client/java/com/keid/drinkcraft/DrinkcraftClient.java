package com.keid.drinkcraft;

import com.keid.drinkcraft.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;


public class DrinkcraftClient implements ClientModInitializer {

	//Keybindings
	KeyBinding binding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.drinkcraft.GUI", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_K, // The keycode of the key
			"category.drinkcraft.gui_cat" // The translation key of the keybinding's category.
	));

	public static PlayerData playerData = new PlayerData();


	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		//Keybinding opens GUI
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (binding1.wasPressed()) {
					MinecraftClient.getInstance().setScreen(new MainScreen());
					//System.out.println(KeyBindingHelper.getBoundKeyOf(binding1));
			}
		});

		//ModMessages.registerS2CMessages();

		ClientPlayNetworking.registerGlobalReceiver(Drinkcraft.DIRT_BROKEN, (client, handler, buf, responseSender) -> {
			int totalDirtBlocksBroken = buf.readInt();
			int playerSpecificDirtBlocksBroken = buf.readInt();

			client.execute(() -> {
				client.player.sendMessage(Text.literal("Total dirt blocks broken: " + totalDirtBlocksBroken));
				client.player.sendMessage(Text.literal("Player specific dirt blocks broken: " + playerSpecificDirtBlocksBroken));
			});
		});

		ClientPlayNetworking.registerGlobalReceiver(Drinkcraft.TOTAL_SIPS, (client, handler, buf, responseSender) -> {
			int totalSips = buf.readInt();
			int playerSpecificsips = buf.readInt();

			client.execute(() -> {
				client.player.sendMessage(Text.literal("Total dirt blocks broken: " + totalSips));
				client.player.sendMessage(Text.literal("Player specific dirt blocks broken: " + playerSpecificsips));
			});
		});


		ClientPlayNetworking.registerGlobalReceiver(Drinkcraft.INITIAL_SYNC, (client, handler, buf, responseSender) -> {
			playerData.dirtBlocksBroken = buf.readInt();
			playerData.sipsPending = buf.readInt();

			client.execute(() -> {
				client.player.sendMessage(Text.literal("Initial specific dirt blocks broken: " + playerData.dirtBlocksBroken));
				client.player.sendMessage(Text.literal("Sips left: " + playerData.sipsPending));
			});
		});


	}
}