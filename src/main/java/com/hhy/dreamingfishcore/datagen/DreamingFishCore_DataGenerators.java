package com.hhy.dreamingfishcore.datagen;

import com.hhy.dreamingfishcore.DreamingFishCore;
import com.hhy.dreamingfishcore.datagen.lang.EnUsLanguageProvider;
import com.hhy.dreamingfishcore.datagen.lang.ZhCnLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DreamingFishCore.MODID)
public class DreamingFishCore_DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> providerCompletableFuture = event.getLookupProvider();

        dataGenerator.addProvider(event.includeServer(), new ModItemModelProvider(packOutput, existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new ZhCnLanguageProvider(dataGenerator, DreamingFishCore.MODID));
        dataGenerator.addProvider(event.includeServer(), new EnUsLanguageProvider(dataGenerator, DreamingFishCore.MODID));
    }
}
