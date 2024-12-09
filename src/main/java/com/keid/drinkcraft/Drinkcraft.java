package com.keid.drinkcraft;

import com.keid.drinkcraft.events.PlayerPickupItemCallback;
import com.keid.drinkcraft.networking.ModMessages;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class Drinkcraft implements ModInitializer {
	public static final String MOD_ID = "drinkcraft";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//
	public static final Identifier DIRT_BROKEN = new Identifier(MOD_ID, "dirt_broken");
	public static final Identifier TOTAL_SIPS = new Identifier(MOD_ID, "total_sips");
	public static final Identifier INITIAL_SYNC = new Identifier(MOD_ID, "initial_sync");

	public static World world;
	public static MinecraftServer server;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution
		LOGGER.info("Hello Fabric world! xD");

		ServerWorldEvents.LOAD.register((server, world) -> {
			MinecraftServer serverI = server;
			World worldI = world;

			Drinkcraft.world = worldI;
			Drinkcraft.server = serverI;
		});

		ModMessages.registerC2SMessages();


		//Event on Item pickup
		PlayerPickupItemCallback.EVENT.register((inventory, slot, stack) -> {
			// Do some logic here
			if (Items.DIAMOND.equals(stack.getItem())) {
				System.out.println("detected diamond");
			}
			return ActionResult.PASS;
		});

		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			if (state.getBlock() == Blocks.GRASS_BLOCK || state.getBlock() == Blocks.DIRT) {
				StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(world.getServer());
				// Increment the amount of dirt blocks that have been broken
				serverState.totalDirtBlocksBroken += 1;

				PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
				playerState.dirtBlocksBroken += 1;

				// Send a packet to the client
				MinecraftServer server = world.getServer();

				PacketByteBuf data = PacketByteBufs.create();
				data.writeInt(serverState.totalDirtBlocksBroken);
				data.writeInt(playerState.dirtBlocksBroken);

				ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
				server.execute(() -> {
					ServerPlayNetworking.send(playerEntity, DIRT_BROKEN, data);
				});
			}
		});



	}
}