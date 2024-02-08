package io.github.felix3621.unknown_traveler.datagen;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, UnknownTraveler.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(ModBlocks.TARDIS_EXTERIOR_BLOCK.get(), "Tardis Exterior Block");
        this.add(ModBlocks.TARDIS_EXTERIOR_BLOCK_OPEN.get(), "Tardis Exterior Block");

        this.add(ModBlocks.WHITE_CONCRETE_ROUNDEL.get(), "White Concrete Roundel");
        this.add(ModBlocks.ORANGE_CONCRETE_ROUNDEL.get(), "Orange Concrete Roundel");
        this.add(ModBlocks.MAGENTA_CONCRETE_ROUNDEL.get(), "Magenta Concrete Roundel");
        this.add(ModBlocks.LIGHT_BLUE_CONCRETE_ROUNDEL.get(), "Light Blue Concrete Roundel");
        this.add(ModBlocks.YELLOW_CONCRETE_ROUNDEL.get(), "Yellow Concrete Roundel");
        this.add(ModBlocks.LIME_CONCRETE_ROUNDEL.get(), "Lime Concrete Roundel");
        this.add(ModBlocks.PINK_CONCRETE_ROUNDEL.get(), "Pink Concrete Roundel");
        this.add(ModBlocks.GRAY_CONCRETE_ROUNDEL.get(), "Gray Concrete Roundel");
        this.add(ModBlocks.LIGHT_GRAY_CONCRETE_ROUNDEL.get(), "Light Gray Concrete Roundel");
        this.add(ModBlocks.CYAN_CONCRETE_ROUNDEL.get(), "Cyan Concrete Roundel");
        this.add(ModBlocks.PURPLE_CONCRETE_ROUNDEL.get(), "Purple Concrete Roundel");
        this.add(ModBlocks.BLUE_CONCRETE_ROUNDEL.get(), "Blue Concrete Roundel");
        this.add(ModBlocks.BROWN_CONCRETE_ROUNDEL.get(), "Brown Concrete Roundel");
        this.add(ModBlocks.GREEN_CONCRETE_ROUNDEL.get(), "Green Concrete Roundel");
        this.add(ModBlocks.RED_CONCRETE_ROUNDEL.get(), "Red Concrete Roundel");
        this.add(ModBlocks.BLACK_CONCRETE_ROUNDEL.get(), "Black Concrete Roundel");

        this.add(ModBlocks.FOAM_PIPES.get(), "Foam Pipes");

        this.add(ModBlocks.METAL_GRATE.get(), "Metal Grate");
        this.add(ModBlocks.METAL_GRATE_SLAB.get(), "Metal Grate Slab");
        this.add(ModBlocks.METAL_GRATE_STAIRS.get(), "Metal Grate Stairs");

        this.add(ModBlocks.TUNGSTEN.get(), "Tungsten");
        this.add(ModBlocks.TUNGSTEN_SLAB.get(), "Tungsten Block");
        this.add(ModBlocks.TUNGSTEN_STAIRS.get(), "Tungsten Stairs");

        this.add(ModBlocks.TUNGSTEN_PLATE.get(), "Tungsten Plate");
        this.add(ModBlocks.TUNGSTEN_PLATE_SLAB.get(), "Tungsten Plate Slab");
        this.add(ModBlocks.TUNGSTEN_PLATE_STAIRS.get(), "Tungsten Plate Stairs");

        this.add(ModBlocks.TUNGSTEN_PIPES.get(), "Tungsten Pipes");
        this.add(ModBlocks.TUNGSTEN_PIPES_SLAB.get(), "Tungsten Pipes Slab");
        this.add(ModBlocks.TUNGSTEN_PIPES_STAIRS.get(), "Tungsten Pipes Stairs");

        this.add(ModBlocks.BLINKING_LIGHTS.get(), "Blinking Lights");

        this.add(ModBlocks.HANGING_WIRES.get(), "Hanging Wires");


        this.add("item_group.unknown_traveler.tardis", "Tardis");

        this.add("command.unknown_traveler.light.check", "The light level in this tardis is %s");
        this.add("command.unknown_traveler.light.set", "The light level in this tardis is now set to %s");

        this.add("command.unknown_traveler.error.capability", "An error with the capability occurred");
        this.add("command.unknown_traveler.error.tardis_dim", "You need to be in a tardis to execute this command");
    }
}
