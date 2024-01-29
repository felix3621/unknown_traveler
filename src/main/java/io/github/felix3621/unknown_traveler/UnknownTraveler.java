package io.github.felix3621.unknown_traveler;

import com.mojang.logging.LogUtils;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import io.github.felix3621.unknown_traveler.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(UnknownTraveler.MODID)
public class UnknownTraveler {
    public static final String MODID = "unknown_traveler";
    private static final Logger LOGGER = LogUtils.getLogger();

    public UnknownTraveler() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
