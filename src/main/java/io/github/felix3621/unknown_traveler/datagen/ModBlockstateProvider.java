package io.github.felix3621.unknown_traveler.datagen;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, UnknownTraveler.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.FOAM_PIPES.get());
        simpleBlockItem(
                ModBlocks.FOAM_PIPES.get(),
                models().getExistingFile(
                        modLoc("block/foam_pipes")
                )
        );

        generateBasicSet(
                ModBlocks.METAL_GRATE.get(),
                ModBlocks.METAL_GRATE_SLAB.get(),
                ModBlocks.METAL_GRATE_STAIRS.get(),
                "metal_grate",
                modLoc("block/metal_grate")
        );
        generateBasicSet(
                ModBlocks.TUNGSTEN.get(),
                ModBlocks.TUNGSTEN_SLAB.get(),
                ModBlocks.TUNGSTEN_STAIRS.get(),
                "tungsten",
                modLoc("block/tungsten")
        );
        generateBasicSet(
                ModBlocks.TUNGSTEN_PLATE.get(),
                ModBlocks.TUNGSTEN_PLATE_SLAB.get(),
                ModBlocks.TUNGSTEN_PLATE_STAIRS.get(),
                "tungsten_plate",
                modLoc("block/tungsten_plate")
        );
        generateBasicSet(
                ModBlocks.TUNGSTEN_PIPES.get(),
                ModBlocks.TUNGSTEN_PIPES_SLAB.get(),
                ModBlocks.TUNGSTEN_PIPES_STAIRS.get(),
                "tungsten_pipes",
                modLoc("block/tungsten_pipes"),
                modLoc("block/tungsten")
        );

        simpleBlock(ModBlocks.BLINKING_LIGHTS.get());
        simpleBlockItem(
                ModBlocks.BLINKING_LIGHTS.get(),
                models().getExistingFile(
                        modLoc("block/blinking_lights")
                )
        );

        simpleBlock(
                ModBlocks.HANGING_WIRES.get(),
                models().cross(
                        "block/hanging_wires",
                        modLoc("block/hanging_wires")
                ));
        simpleBlockItem(
                ModBlocks.HANGING_WIRES.get(),
                models().getExistingFile(
                        modLoc("block/hanging_wires")
                )
        );
    }

    private void generateBasicBlock(Block block, String position, ResourceLocation texture) {
        simpleBlock(block,
                models().cubeAll(
                        "block/"+position+"/block",
                        texture
                )
        );
        simpleBlockItem(block,
                models().withExistingParent(
                        "item/"+position+"/block",
                        modLoc("block/"+position+"/block")
                )
        );
    }
    private void generateBasicBlock(Block block, String position, ResourceLocation side, ResourceLocation topbottom) {
        simpleBlock(
                block,
                models().cubeBottomTop(
                        "block/"+position+"/block",
                        side,
                        topbottom,
                        topbottom
                )
        );
        simpleBlockItem(
                block,
                models().withExistingParent(
                        "item/"+position+"/block",
                        modLoc("block/"+position+"/block")
                )
        );
    }

    private void generateBasicStairs(StairBlock stairBlock, String position, ResourceLocation texture) {
        stairsBlock(
                stairBlock,
                models().stairs(
                        "block/"+position+"/stairs_straight",
                        texture,
                        texture,
                        texture
                ),
                models().stairsInner(
                        "block/"+position+"/stairs_inner",
                        texture,
                        texture,
                        texture
                ),
                models().stairsOuter(
                        "block/"+position+"/stairs_outer",
                        texture,
                        texture,
                        texture
                )
        );
        simpleBlockItem(
                stairBlock,
                models().withExistingParent(
                        "item/"+position+"/stairs",
                        modLoc("block/"+position+"/stairs_straight")
                )
        );
    }
    private void generateBasicStairs(StairBlock stairBlock, String position, ResourceLocation side, ResourceLocation topbottom) {
        stairsBlock(
                stairBlock,
                models().stairs(
                        "block/"+position+"/stairs_straight",
                        side,
                        topbottom,
                        topbottom
                ),
                models().stairsInner(
                        "block/"+position+"/stairs_inner",
                        side,
                        topbottom,
                        topbottom
                ),
                models().stairsOuter(
                        "block/"+position+"/stairs_outer",
                        side,
                        topbottom,
                        topbottom
                )
        );
        simpleBlockItem(
                stairBlock,
                models().withExistingParent(
                        "item/"+position+"/stairs",
                        modLoc("block/"+position+"/stairs_straight")
                )
        );
    }

    private void generateBasicSlab(SlabBlock slabBlock, String position, ResourceLocation texture) {
        slabBlock(slabBlock,
                models().slab(
                        "block/"+position+"/slab_bottom",
                        texture,
                        texture,
                        texture
                ),
                models().slabTop(
                        "block/"+position+"/slab_top",
                        texture,
                        texture,
                        texture
                ),
                models().withExistingParent(
                        "block/"+position+"/slab_double",
                        modLoc("block/"+position+"/block")
                )
        );
        simpleBlockItem(
                slabBlock,
                models().withExistingParent(
                        "item/"+position+"/slab",
                        modLoc("block/"+position+"/slab_bottom")
                )
        );
    }
    private void generateBasicSlab(SlabBlock slabBlock, String position, ResourceLocation side, ResourceLocation topbottom) {
        slabBlock(
                slabBlock,
                models().slab(
                        "block/"+position+"/slab_bottom",
                        side,
                        topbottom,
                        topbottom
                ),
                models().slabTop(
                        "block/"+position+"/slab_top",
                        side,
                        topbottom,
                        topbottom
                ),
                models().withExistingParent(
                        "block/"+position+"/slab_double",
                        modLoc("block/"+position+"/block")
                )
        );
        simpleBlockItem(
                slabBlock,
                models().withExistingParent(
                        "item/tungsten_pipes/slab",
                        modLoc("block/tungsten_pipes/slab_bottom")
                )
        );
    }

    private void generateBasicSet(Block block, SlabBlock slabBlock, StairBlock stairBlock, String position, ResourceLocation texture) {
        generateBasicBlock(block, position, texture);
        generateBasicSlab(slabBlock, position, texture);
        generateBasicStairs(stairBlock, position, texture);
    }
    private void generateBasicSet(Block block, SlabBlock slabBlock, StairBlock stairBlock, String position, ResourceLocation side, ResourceLocation topbottom) {
        generateBasicBlock(block, position, side, topbottom);
        generateBasicSlab(slabBlock, position, side, topbottom);
        generateBasicStairs(stairBlock, position, side, topbottom);
    }
}
