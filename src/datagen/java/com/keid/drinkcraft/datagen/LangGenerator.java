package com.keid.drinkcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;


/*public class LangGenerator extends FabricLanguageProvider {
    private LangGenerator(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataGenerator, "en_us", registryLookup)
        ;}

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

         Blocks
        Arrays.stream(ModBlocks.values()).forEach(value -> translationBuilder.add(value.getBlock(), WordUtils.capitalizeFully(value.getId().replace("_", " "))));

         Items
        Arrays.stream(ModItems.values()).forEach(value -> translationBuilder.add(value.getItem(), WordUtils.capitalizeFully(value.getId().replace("_", " "))));
        Arrays.stream(ModSpawnEggItems.values()).forEach(value -> translationBuilder.add(value.getItem(), WordUtils.capitalizeFully(value.getId().replace("_", " "))));

          Game Rule Category
        translationBuilder.add("gamerule.category." + MyMod.MOD_ID, "My Mod");

         Game Rules
        Arrays.stream(ModBooleanGameRules.values()).forEach(value -> translationBuilder.add(value.getKey().getTranslationKey(), value.getDescription()));
        Arrays.stream(ModIntegerGameRules.values()).forEach(value -> translationBuilder.add(value.getKey().getTranslationKey(), value.getDescription()));

         Item Groups
        Arrays.stream(ModItemGroups.values()).forEach(value -> translationBuilder.add(value.getItemGroup(), WordUtils.capitalizeFully(value.getId().replace("_", " "))));

         Entity Types
        Arrays.stream(ModMobEntityTypes.values()).forEach(value -> translationBuilder.add(value.getEntityType(), WordUtils.capitalizeFully(value.getId().replace("_", " "))));

         Status effects
        Arrays.stream(ModStatusEffects.values()).forEach(value -> translationBuilder.add(value.getStatusEffect(), WordUtils.capitalizeFully(value.getId().replace("_", " "))));


        try {
            Path existingFilePath = dataGenerator.getModContainer().findPath("assets/drinkcraft/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
*/