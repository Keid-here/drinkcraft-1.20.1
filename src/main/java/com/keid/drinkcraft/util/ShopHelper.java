package com.keid.drinkcraft.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.Drinkcraft.CONFIG;
import static com.keid.drinkcraft.Drinkcraft.server;

public class ShopHelper {

    public static void buy(String type, ServerPlayerEntity player ){
        System.out.println(type);
        System.out.println("shop received");

        var playerEntity = ((IEntityDataSaver) player);
        NbtCompound nbt = playerEntity.getPersistentData();
        int points = nbt.getInt("points");

        switch (type){
            case "lucky":
                if (points >= CONFIG.lucky_ticket_price()){
                    SipsHelperNew.removePoints(player, CONFIG.lucky_ticket_price());

                    ItemStack ticket = new ItemStack(Registries.ITEM.get(new Identifier("drinkcraft:luckyticket")));
                    boolean wasAdded = player.getInventory().insertStack(ticket);
                    if (!wasAdded) {
                        player.dropItem(ticket, false); }
                }
                else {
                server.sendMessage(Text.literal("you don't have enough points!"));
                }
                break;

            case "superlucky":
                if (points >= CONFIG.superlucky_ticket_price()){
                    SipsHelperNew.removePoints(player, CONFIG.superlucky_ticket_price());

                    ItemStack ticket = new ItemStack(Registries.ITEM.get(new Identifier("drinkcraft:superluckyticket")));
                    boolean wasAdded = player.getInventory().insertStack(ticket);
                    if (!wasAdded) {
                        player.dropItem(ticket, false); }
                }
                else {
                    server.sendMessage(Text.literal("you don't have enough points!"));
                }
                break;


        }



    }
}
