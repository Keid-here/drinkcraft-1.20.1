package com.keid.drinkcraft.networking.packetowo;

import net.minecraft.util.Identifier;

public record PointsSyncPacket (int points, Identifier aMinecraftClass) {
}