package io.github.felix3621.unknown_traveler.block.entity;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, UnknownTraveler.MODID);


    public static final RegistryObject<BlockEntityType<TardisExteriorBlockEntity>> TARDIS_EXTERIOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("tardis_exterior_block_entity", () ->
                    BlockEntityType.Builder.of(TardisExteriorBlockEntity::new,
                            ModBlocks.TARDIS_EXTERIOR_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
