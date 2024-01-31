package io.github.felix3621.unknown_traveler.helper;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.dimensions.TardisDimensions;
import io.github.felix3621.unknown_traveler.util.savedata.SDControl;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.Map;

public class TardisHelper {
    public static ServerLevel getTardisDim(MinecraftServer server, String name) {

        name = "tardis_"+name;

        ResourceKey<Level> worldKey = ResourceKey.create(Registries.DIMENSION, Helper.createRL(name));

        Map<ResourceKey<Level>, ServerLevel> map = server.forgeGetWorldMap();

        // if the world already exists, return it
        if (map.containsKey(worldKey)) {
            return map.get(worldKey);
        } else {
            ServerLevel TardisWorld = TardisDimensions.createAndRegisterDynamicWorldAndDimension(server, map, worldKey);

            ResourceLocation loc = new ResourceLocation(UnknownTraveler.MODID, name);
            if (!SDControl.tardis_dimensions.contains(worldKey)) {
                SDControl.tardis_dimensions.add(worldKey);
                SDControl.store();
            }

            return TardisWorld;
        }
    }
}
