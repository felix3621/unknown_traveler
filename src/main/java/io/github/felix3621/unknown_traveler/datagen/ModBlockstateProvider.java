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
    }
}
