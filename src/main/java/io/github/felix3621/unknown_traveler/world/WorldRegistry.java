package io.github.felix3621.unknown_traveler.world;

import com.mojang.serialization.Codec;
import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.helper.Helper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WorldRegistry {
    public static final DeferredRegister<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister.create(Registries.CHUNK_GENERATOR, UnknownTraveler.MODID);
    public static final RegistryObject<Codec<? extends ChunkGenerator>> TARDIS_CHUNK_GENERATOR = CHUNK_GENERATORS.register("tardis", () -> TardisChunkGenerator.CODEC);
    public static final ResourceKey<Biome> TARDIS_BIOME = ResourceKey.create(Registries.BIOME, Helper.createRL("tardis"));
}
