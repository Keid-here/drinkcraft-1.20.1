package com.keid.drinkcraft.util;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.keid.drinkcraft.Drinkcraft.MOD_ID;
import static com.keid.drinkcraft.Drinkcraft.LOGGER;

public class CustomSounds {
    private CustomSounds() {}

    public static final SoundEvent ADDSOUND = registerSound("addsound");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void initialize() {
        LOGGER.info("Registering " + MOD_ID + " Sounds");
    }
}




