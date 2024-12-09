package com.keid.drinkcraft.datagen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DrinkcraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		//pack.addProvider(LangGenerator::new);

		//pack.addProvider(ModModelGenerator::new);
		//pack.addProvider(ModRecipeProvider::new);
	}
}
