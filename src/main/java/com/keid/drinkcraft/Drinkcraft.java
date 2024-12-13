package com.keid.drinkcraft;

import com.keid.drinkcraft.networking.packetowo.*;
import com.keid.drinkcraft.server.RandomDistributor;
import com.keid.drinkcraft.util.*;
import com.keid.drinkcraft.util.Drinkcraft_Config;
import io.wispforest.owo.network.OwoNetChannel;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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

	public static final Drinkcraft_Config CONFIG = Drinkcraft_Config.createAndLoad();

	//
	public static final Identifier DIRT_BROKEN = new Identifier(MOD_ID, "dirt_broken");
	public static final Identifier TOTAL_SIPS = new Identifier(MOD_ID, "total_sips");
	public static final Identifier INITIAL_SYNC = new Identifier(MOD_ID, "initial_sync");

	public static final OwoNetChannel DRINKCRAFTOWOCHANNEL = OwoNetChannel.create(new Identifier(MOD_ID, "drinkcraftowonet"));

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

		//Networking stuff
		DRINKCRAFTOWOCHANNEL.registerServerbound(SipsPacket.class, (message, access) -> {
			switch (message.action()) {
				case "add":
					SipsHelperNew.addSips(access.player(), message.sips());
					break;
				case "remove":
					SipsHelperNew.removeSips(access.player(), message.sips());
					break;
				case "random":
					RandomDistributor.go(access.player(), message.sips());
					break;
				case "all":
					SipsHelperNew.addAllSips(access.player().server, message.sips());
					break;
				case "truerandom":
					RandomDistributor.trueRandpom(access.player(), message.sips());
					break;
			}
		});

		DRINKCRAFTOWOCHANNEL.registerServerbound(SipsSyncC2SPacket.class, (message, access) -> {
			SipsHelperNew.returnSync(access.player());
		});
		DRINKCRAFTOWOCHANNEL.registerServerbound(SipsTotalC2S.class, (message, access) -> {
			SipsHelperNew.getAndSyncTotalSips(access.player());
		});
		DRINKCRAFTOWOCHANNEL.registerServerbound(totalSipsResetPacket.class, (message, access) -> {
			SipsHelperNew.totalSipsReset(access.player());
		});
		DRINKCRAFTOWOCHANNEL.registerServerbound(SyncAllPacket.class, (message, access) -> {
				SipsHelperNew.returnSync(access.player());
				SipsHelperNew.getAndSyncTotalSips(access.player());
				SipsHelperNew.returnSyncPoints(access.player());
		});

		//registers all blocks that trigger events
		CustomBlocksFactory.registerCustomBlocks();

		CustomSounds.initialize();

		FieldRegistrationHandler.register(ItemInit.class, MOD_ID,false);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.add(ItemInit.TOMEOFCASCADE) );

		LootTableRegister.registerLootTables();
		LootTableRegister.registerMobLootTables();



		//todo: clean up





		ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
			if(entity instanceof PlayerEntity) {
				int sips = CONFIG.sips_on_Death();

				ServerPlayerEntity player = (ServerPlayerEntity) entity;

				PlayerManager playerManager = server.getPlayerManager();
				playerManager.broadcast(Text.literal(player.getEntityName() + " died").formatted(Formatting.DARK_RED), false);

				ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
				server.execute(() -> {
					SipsHelperNew.addSips(player, sips);
				});
			}
		});






		//todo remove when no longer needed
		//olny here copy/paste
		//PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
		//			if (state.getBlock() == Blocks.GRASS_BLOCK || state.getBlock() == Blocks.DIRT) {
		//				StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(world.getServer());
		//				// Increment the amount of dirt blocks that have been broken
		//				serverState.totalDirtBlocksBroken += 1;
		//
		//				PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
		//				playerState.dirtBlocksBroken += 1;
		//
		//				// Send a packet to the client
		//				MinecraftServer server = world.getServer();
		//
		//				PacketByteBuf data = PacketByteBufs.create();
		//				data.writeInt(serverState.totalDirtBlocksBroken);
		//				data.writeInt(playerState.dirtBlocksBroken);
		//
		//				ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
		//				server.execute(() -> {
		//					ServerPlayNetworking.send(playerEntity, DIRT_BROKEN, data);
		//				});
		//			}
		//		});


		////Event on Item pickup
		//		PlayerPickupItemCallback.EVENT.register((inventory, slot, stack) -> {
		//			// Do some logic here
		//			if (Items.DIAMOND.equals(stack.getItem())) {
		//				System.out.println("detected diamond");
		//			}
		//			return ActionResult.PASS;
		//		});



	}
}