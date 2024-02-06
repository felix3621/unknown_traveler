package io.github.felix3621.unknown_traveler.dimensions;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Lifecycle;
import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.network.Network;
import io.github.felix3621.unknown_traveler.network.packets.SyncDimensionListMessage;
import io.github.felix3621.unknown_traveler.world.TardisChunkGenerator;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.LevelEvent;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

public class TardisDimensions {
    public static class DimensionTypes {
        public static final ResourceKey<DimensionType> TARDIS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, UnknownTraveler.TARDIS_LOC);
    }
    public static ServerLevel createAndRegisterDynamicWorldAndDimension(MinecraftServer server, Map<ResourceKey<Level>, ServerLevel> map, ResourceKey<Level> worldKey) {
        ServerLevel newWorld = createAndRegisterWorldAndDimension(server, map, worldKey, TardisDimensions::createTARDISStem);
        Network.sendPacketToAll(new SyncDimensionListMessage(worldKey, true));
        return newWorld;
    }
    private static ServerLevel createAndRegisterWorldAndDimension(MinecraftServer server, Map<ResourceKey<Level>, ServerLevel> map, ResourceKey<Level> worldKey, BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> dimensionFactory) {
        ServerLevel overworld = server.getLevel(Level.OVERWORLD);
        ResourceKey<LevelStem> dimensionKey = ResourceKey.create(Registries.LEVEL_STEM, worldKey.location());
        LevelStem dimension = dimensionFactory.apply(server, dimensionKey);

        ChunkProgressListener chunkListener = server.progressListenerFactory.create(11);
        Executor executor = server.executor;
        LevelStorageSource.LevelStorageAccess levelSave = server.storageSource;

        WorldData serverConfig = server.getWorldData();
        // this next line registers the Dimension
        MappedRegistry<LevelStem> dimensionRegistry = (MappedRegistry<LevelStem>) server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
        dimensionRegistry.unfreeze();
        dimensionRegistry.register(dimensionKey, dimension, Lifecycle.experimental()); //set to stable to reduce issues that come with the experimental marker

        DerivedLevelData derivedWorldInfo = new DerivedLevelData(serverConfig, serverConfig.overworldData());
        // now we have everything we need to create the world instance
        ServerLevel newWorld = new ServerLevel(
                server,
                executor,
                levelSave,
                derivedWorldInfo,
                worldKey,
                dimension,
                chunkListener,
                serverConfig.isDebugWorld(), // boolean: is-debug-world
                BiomeManager.obfuscateSeed(serverConfig.worldGenOptions().seed()),
                ImmutableList.of(), // "special spawn list"
                // phantoms, raiders, travelling traders, cats are overworld special spawns
                // the dimension loader is hardcoded to initialize preexisting non-overworld worlds with no special spawn lists
                // so this can probably be left empty for best results and spawns should be handled via other means
                false); // "tick time", true for overworld, always false for everything else

        // add world border listener
        overworld.getWorldBorder().addListener(new BorderChangeListener.DelegateBorderChangeListener(newWorld.getWorldBorder()));

        // register world
        map.put(worldKey, newWorld);

        // update forge's world cache (very important, if we don't do this then the new world won't tick!)
        server.markWorldsDirty();

        // fire world load event
        MinecraftForge.EVENT_BUS.post(new LevelEvent.Load(newWorld)); // event isn't cancellable
        System.out.println("Registered new dimension of: " + dimensionKey.location().toString());

        TestDimensionCreator.prepareTARDISWorld(chunkListener, newWorld);

        return newWorld;
    }

    public static LevelStem createTARDISStem(MinecraftServer server, ResourceKey<LevelStem> stem){
        RegistryAccess registries = server.registryAccess();

        LevelStem steam = new LevelStem(registries.registryOrThrow(Registries.DIMENSION_TYPE).getHolderOrThrow(DimensionTypes.TARDIS_TYPE), new TardisChunkGenerator(registries.registryOrThrow(Registries.BIOME).asLookup()));

        return steam;

    }
}
