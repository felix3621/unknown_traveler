package io.github.felix3621.unknown_traveler.datagen;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, UnknownTraveler.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.FOAM_PIPES.get());

        simpleBlock(
                ModBlocks.METAL_GRATE.get(),
                models().cubeAll(
                        "block/metal_grate/block",
                        modLoc("block/metal_grate")
                )
        );

        simpleBlockItem(
                ModBlocks.METAL_GRATE.get(),
                models().withExistingParent(
                        "item/metal_grate/block",
                        modLoc("block/metal_grate/block")
                )
        );

        slabBlock(
                ModBlocks.METAL_GRATE_SLAB.get(),
                models().slab(
                        "block/metal_grate/slab_bottom",
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate")
                ),
                models().slabTop(
                        "block/metal_grate/slab_top",
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate")
                ),
                models().withExistingParent(
                        "block/metal_grate/slab_double",
                        modLoc("block/metal_grate/block")
                )
        );

        simpleBlockItem(
                ModBlocks.METAL_GRATE_SLAB.get(),
                models().withExistingParent(
                        "item/metal_grate/slab",
                        modLoc("block/metal_grate/slab_bottom")
                )
        );

        stairsBlock(
                ModBlocks.METAL_GRATE_STAIRS.get(),
                models().stairs(
                        "block/metal_grate/stairs_straight",
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate")
                ),
                models().stairsInner(
                        "block/metal_grate/stairs_inner",
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate")
                ),
                models().stairsOuter(
                        "block/metal_grate/stairs_outer",
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate"),
                        modLoc("block/metal_grate")
                )
        );

        simpleBlockItem(
                ModBlocks.METAL_GRATE_STAIRS.get(),
                models().withExistingParent(
                        "item/metal_grate/stairs",
                        modLoc("block/metal_grate/stairs_straight")
                )
        );

        simpleBlock(
                ModBlocks.TUNGSTEN.get(),
                models().cubeAll(
                        "block/tungsten/block",
                        modLoc("block/tungsten")
                )
        );

        simpleBlockItem(
                ModBlocks.TUNGSTEN.get(),
                models().withExistingParent(
                        "item/tungsten/block",
                        modLoc("block/tungsten/block")
                )
        );

        slabBlock(
                ModBlocks.TUNGSTEN_SLAB.get(),
                models().slab(
                        "block/tungsten/slab_bottom",
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten")
                ),
                models().slabTop(
                        "block/tungsten/slab_top",
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten")
                ),
                models().withExistingParent(
                        "block/tungsten/slab_double",
                        modLoc("block/tungsten/block")
                )
        );

        simpleBlockItem(
                ModBlocks.TUNGSTEN_SLAB.get(),
                models().withExistingParent(
                        "item/tungsten/slab",
                        modLoc("block/tungsten/slab_bottom")
                )
        );

        stairsBlock(
                ModBlocks.TUNGSTEN_STAIRS.get(),
                models().stairs(
                        "block/tungsten/stairs_straight",
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten")
                ),
                models().stairsInner(
                        "block/tungsten/stairs_inner",
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten")
                ),
                models().stairsOuter(
                        "block/tungsten/stairs_outer",
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten"),
                        modLoc("block/tungsten")
                )
        );

        simpleBlockItem(
                ModBlocks.TUNGSTEN_STAIRS.get(),
                models().withExistingParent(
                        "item/tungsten/stairs",
                        modLoc("block/tungsten/stairs_straight")
                )
        );
    }
}
