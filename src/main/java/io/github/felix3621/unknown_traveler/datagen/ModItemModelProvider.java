package io.github.felix3621.unknown_traveler.datagen;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, UnknownTraveler.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }
}
