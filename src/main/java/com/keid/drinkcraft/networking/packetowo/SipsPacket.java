package com.keid.drinkcraft.networking.packetowo;

import net.minecraft.util.Identifier;

public record SipsPacket (String action, int sips, Identifier aMinecraftClass) {

}
