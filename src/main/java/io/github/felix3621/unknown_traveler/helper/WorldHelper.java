package io.github.felix3621.unknown_traveler.helper;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class WorldHelper {
    /**
     * Compares the current world's dimension type against a registry key of a json based dimension type
     * @param world
     * @param dimTypeKey
     * @return True if the registry keys match, false if they do not
     */
    public static boolean areDimensionTypesSame(Level world, ResourceKey<DimensionType> dimTypeKey) {

        if(world.registryAccess() == null || world.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE) == null)
            return false;

        DimensionType type = world.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE).get(dimTypeKey);
        if(type != null && type.equals(world.dimensionType()))
            return true;
        return false;
    }
}
