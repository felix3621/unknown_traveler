package io.github.felix3621.unknown_traveler.dimensions;

import io.github.felix3621.unknown_traveler.helper.Helper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;

public class DimensionTypes {

    public static ResourceKey<DimensionType> TARDIS_TYPE;


    public static void register(){
        TARDIS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Helper.createRL("tardis"));
    }
}
