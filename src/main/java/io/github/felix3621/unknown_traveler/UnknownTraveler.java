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

        modEventBus.addListener(this::registerTab);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
        Network.registerPackets();
        DimensionTypes.register();
    }

    public void registerTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(
                new ResourceLocation(UnknownTraveler.MODID, "tardis"),
                builder ->
                        builder.title(Component.translatable("item_group."+UnknownTraveler.MODID+".tardis"))
                                .icon(() -> new ItemStack(ModBlocks.BLINKING_LIGHTS.get()))
                                .displayItems((params, output) -> {
                                    output.accept(ModBlocks.WHITE_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.ORANGE_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.MAGENTA_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.LIGHT_BLUE_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.YELLOW_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.LIME_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.PINK_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.GRAY_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.LIGHT_GRAY_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.CYAN_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.PURPLE_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.BLUE_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.BROWN_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.GREEN_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.RED_CONCRETE_ROUNDEL.get());
                                    output.accept(ModBlocks.BLACK_CONCRETE_ROUNDEL.get());

                                    output.accept(ModBlocks.FOAM_PIPES.get());

                                    output.accept(ModBlocks.METAL_GRATE.get());
                                    output.accept(ModBlocks.METAL_GRATE_SLAB.get());
                                    output.accept(ModBlocks.METAL_GRATE_STAIRS.get());

                                    output.accept(ModBlocks.TUNGSTEN.get());
                                    output.accept(ModBlocks.TUNGSTEN_SLAB.get());
                                    output.accept(ModBlocks.TUNGSTEN_STAIRS.get());

                                    output.accept(ModBlocks.TUNGSTEN_PLATE.get());
                                    output.accept(ModBlocks.TUNGSTEN_PLATE_SLAB.get());
                                    output.accept(ModBlocks.TUNGSTEN_PLATE_STAIRS.get());

                                    output.accept(ModBlocks.TUNGSTEN_PIPES.get());
                                    output.accept(ModBlocks.TUNGSTEN_PIPES_SLAB.get());
                                    output.accept(ModBlocks.TUNGSTEN_PIPES_STAIRS.get());

                                    output.accept(ModBlocks.BLINKING_LIGHTS .get());

                                    output.accept(ModBlocks.HANGING_WIRES.get());
                                })
        );
    }
}
