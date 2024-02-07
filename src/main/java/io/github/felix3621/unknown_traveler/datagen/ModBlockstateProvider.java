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
    }
}
