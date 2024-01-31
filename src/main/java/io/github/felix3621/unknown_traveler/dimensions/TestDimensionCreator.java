package io.github.felix3621.unknown_traveler.dimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.util.Unit;
import net.minecraft.world.level.ChunkPos;

public class TestDimensionCreator {
    public static void prepareTARDISWorld(ChunkProgressListener chunkProgress, ServerLevel level){
        //RegistryAccess registryAccess = server.registryAccess();
        //LOGGER.info("Preparing start region for dimension {}", (Object)serverlevel.dimension().location());
        BlockPos blockpos = level.getSharedSpawnPos();
        chunkProgress.updateSpawnPos(new ChunkPos(blockpos));
        ServerChunkCache serverchunkcache = level.getChunkSource();
        serverchunkcache.getLightEngine().setTaskPerBatch(500);
        //this.nextTickTime = Util.getMillis();
        serverchunkcache.addRegionTicket(TicketType.START, new ChunkPos(blockpos), 11, Unit.INSTANCE);

    }
}
