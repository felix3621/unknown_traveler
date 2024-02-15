package io.github.felix3621.unknown_traveler;

import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;

import java.util.function.Consumer;

public class CreativeTab {
    public static void registerTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(
                new ResourceLocation(UnknownTraveler.MODID, "tardis"),
                tardis()
        );
    }

    private static Consumer<CreativeModeTab.Builder> tardis() {
        return builder ->
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


                            output.accept(ModItems.EMITTER.get());
                            output.accept(ModItems.ACTIVATOR.get());
                            output.accept(ModItems.HANDLE.get());
                            output.accept(ModItems.END.get());

                            output.accept(ModItems.SCREWDRIVER.get());
                        });
    }
}
