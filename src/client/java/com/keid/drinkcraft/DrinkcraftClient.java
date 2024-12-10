package com.keid.drinkcraft;

import com.keid.drinkcraft.networking.ModMessages;
import com.keid.drinkcraft.networking.ModMessagesClient;
import com.keid.drinkcraft.util.IEntityDataSaver;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import static com.keid.drinkcraft.Drinkcraft.MOD_ID;
import static com.keid.drinkcraft.Drinkcraft.world;


public class DrinkcraftClient implements ClientModInitializer {

	public static final String MOD_ID = "drinkcraft";
	public static Identifier HUD = new Identifier("drinkcraft", "hud");

	//Keybindings
	KeyBinding binding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.drinkcraft.GUI", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_K, // The keycode of the key
			"category.drinkcraft.gui_cat" // The translation key of the keybinding's category.
	));
	KeyBinding binding2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.drinkcraft.HUD", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_I, // The keycode of the key
			"category.drinkcraft.gui_cat" // The translation key of the keybinding's category.
	));
	KeyBinding binding3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.drinkcraft.drink", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_U, // The keycode of the key
			"category.drinkcraft.gui_cat" // The translation key of the keybinding's category.
	));

	public static PlayerData playerData = new PlayerData();
	public static int sipsInt;
	public static int sipsTotal;
	public static boolean hudToggle = false;



	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		//Keybindings
		//
		// opens GUI
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (binding1.wasPressed()) {
					MinecraftClient.getInstance().setScreen(new MainScreen());
					//System.out.println(KeyBindingHelper.getBoundKeyOf(binding1));
			}
		});
		// HUD toggle
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (binding2.wasPressed()) {
				if (hudToggle == false){
					hudToggle = true;
					ClientPlayNetworking.send(ModMessages.SIPSSYNC, PacketByteBufs.create());
					client.player.sendMessage(Text.literal("HUD toggled on"), false);
				}else {
					hudToggle = false;
					ClientPlayNetworking.send(ModMessages.SIPSSYNC, PacketByteBufs.create());
					client.player.sendMessage(Text.literal("HUD toggled off"), false);
					Hud.remove(HUD);
				}
			}
		});
		// Dirk a sip
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (binding3.wasPressed()) {
				ClientPlayNetworking.send(ModMessages.SIPSDEC, PacketByteBufs.create());
				world.playSound(null, MinecraftClient.getInstance().player.getBlockPos(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS,
						0.5F, world.random.nextFloat() * 0.1F + 0.9F);
			}
		});



		//lets us receive packets from server
		ModMessagesClient.registerS2CMessages();


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