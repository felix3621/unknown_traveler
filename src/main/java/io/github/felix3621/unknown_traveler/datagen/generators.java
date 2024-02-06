package io.github.felix3621.unknown_traveler.datagen;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnknownTraveler.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class generators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModItemModelProvider>) output -> new ModItemModelProvider(output, existingFileHelper)
        );
        generator.addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModBlockstateProvider>) output -> new ModBlockstateProvider(output, existingFileHelper)
        );
    }
}
