package io.github.felix3621.unknown_traveler;

import com.mojang.logging.LogUtils;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import io.github.felix3621.unknown_traveler.dimensions.DimensionTypes;
import io.github.felix3621.unknown_traveler.item.ModItems;
import io.github.felix3621.unknown_traveler.network.Network;
import io.github.felix3621.unknown_traveler.world.WorldRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(UnknownTraveler.MODID)
public class UnknownTraveler {
    public static final String MODID = "unknown_traveler";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final ResourceLocation TARDIS_LOC = new ResourceLocation(UnknownTraveler.MODID, "tardis");

    public UnknownTraveler() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        WorldRegistry.CHUNK_GENERATORS.register(modEventBus);

        modEventBus.addListener(CreativeTab::registerTab);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
        Network.registerPackets();
        DimensionTypes.register();
    }
}
