package io.github.felix3621.unknown_traveler.block;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.custom.LightBlock;
import io.github.felix3621.unknown_traveler.block.custom.TardisExteriorBlock;
import io.github.felix3621.unknown_traveler.block.custom.TardisExteriorBlockOpen;
import io.github.felix3621.unknown_traveler.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, UnknownTraveler.MODID);

    public static final RegistryObject<Block> TARDIS_EXTERIOR_BLOCK = registerBlockWithoutBlockItem("tardis_exterior_block",
            () -> new TardisExteriorBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(-1.0F, 3600000.0F).noOcclusion()));
    public static final RegistryObject<Block> TARDIS_EXTERIOR_BLOCK_OPEN = registerBlockWithoutBlockItem("tardis_exterior_block_open",
            () -> new TardisExteriorBlockOpen(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(-1.0F, 3600000.0F).noOcclusion()));

    public static final RegistryObject<Block> WHITE_CONCRETE_ROUNDEL = registerBlock("roundel/white_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> ORANGE_CONCRETE_ROUNDEL = registerBlock("roundel/orange_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> MAGENTA_CONCRETE_ROUNDEL = registerBlock("roundel/magenta_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_ROUNDEL = registerBlock("roundel/light_blue_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> YELLOW_CONCRETE_ROUNDEL = registerBlock("roundel/yellow_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> LIME_CONCRETE_ROUNDEL = registerBlock("roundel/lime_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> PINK_CONCRETE_ROUNDEL = registerBlock("roundel/pink_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> GRAY_CONCRETE_ROUNDEL = registerBlock("roundel/gray_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_ROUNDEL = registerBlock("roundel/light_gray_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> CYAN_CONCRETE_ROUNDEL = registerBlock("roundel/cyan_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> PURPLE_CONCRETE_ROUNDEL = registerBlock("roundel/purple_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> BLUE_CONCRETE_ROUNDEL = registerBlock("roundel/blue_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> BROWN_CONCRETE_ROUNDEL = registerBlock("roundel/brown_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> GREEN_CONCRETE_ROUNDEL = registerBlock("roundel/green_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> RED_CONCRETE_ROUNDEL = registerBlock("roundel/red_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> BLACK_CONCRETE_ROUNDEL = registerBlock("roundel/black_concrete",
            () -> new LightBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    public static final RegistryObject<Block> FOAM_PIPES = registerBlock("foam_pipes",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL)
                    .sound(SoundType.WOOL).strength(1F, 2F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> METAL_GRATE = registerBlock("metal_grate/block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion()
                    .sound(SoundType.LANTERN).strength(1.25F, 4.2F).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> METAL_GRATE_SLAB = registerBlock("metal_grate/slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion()
                    .sound(SoundType.LANTERN).strength(1.25F, 4.2F).requiresCorrectToolForDrops()));
    public static final RegistryObject<StairBlock> METAL_GRATE_STAIRS = registerBlock("metal_grate/stairs",
            () -> new StairBlock(ModBlocks.METAL_GRATE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion()
                    .sound(SoundType.LANTERN).strength(1.25F, 4.2F).requiresCorrectToolForDrops()));


    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
