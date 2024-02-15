package io.github.felix3621.unknown_traveler.event;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import io.github.felix3621.unknown_traveler.block.entity.client.tardis_interior.closed.TardisInteriorBlockRenderer;
import io.github.felix3621.unknown_traveler.block.entity.client.tardis_interior.open.TardisInteriorBlockRendererOpen;
import io.github.felix3621.unknown_traveler.block.entity.client.tardis_exterior.closed.TardisExteriorBlockRenderer;
import io.github.felix3621.unknown_traveler.block.entity.client.tardis_exterior.open.TardisExteriorBlockRendererOpen;
import io.github.felix3621.unknown_traveler.helper.Helper;
import io.github.felix3621.unknown_traveler.item.ModItems;
import io.github.felix3621.unknown_traveler.item.custom.SonicBasePart;
import io.github.felix3621.unknown_traveler.item.custom.SonicScrewdriver;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = UnknownTraveler.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventBus {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.TARDIS_EXTERIOR_BLOCK_ENTITY.get(), TardisExteriorBlockRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TARDIS_EXTERIOR_BLOCK_ENTITY_OPEN.get(), TardisExteriorBlockRendererOpen::new);

        event.registerBlockEntityRenderer(ModBlockEntities.TARDIS_INTERIOR_BLOCK_ENTITY.get(), TardisInteriorBlockRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TARDIS_INTERIOR_BLOCK_ENTITY_OPEN.get(), TardisInteriorBlockRendererOpen::new);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        renderLayers();
        event.enqueueWork(predicates());
    }

    private static Runnable predicates() {
        return () -> {
            ItemProperties.register(ModItems.EMITTER.get(),
                    Helper.createRL("sonic_type"), (stack, level, living, id) -> SonicBasePart.getType(stack));
            ItemProperties.register(ModItems.ACTIVATOR.get(),
                    Helper.createRL("sonic_type"), (stack, level, living, id) -> SonicBasePart.getType(stack));
            ItemProperties.register(ModItems.HANDLE.get(),
                    Helper.createRL("sonic_type"), (stack, level, living, id) -> SonicBasePart.getType(stack));
            ItemProperties.register(ModItems.END.get(),
                    Helper.createRL("sonic_type"), (stack, level, living, id) -> SonicBasePart.getType(stack));

            ItemProperties.register(ModItems.SCREWDRIVER.get(),
                    Helper.createRL("sonic_type"), (stack, level, living, id) -> SonicScrewdriver.getType(stack));
        };
    }

    private static void renderLayers() {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TARDIS_EXTERIOR_BLOCK_OPEN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TARDIS_EXTERIOR_BLOCK.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAGENTA_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_BLUE_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIME_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GRAY_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_GRAY_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CYAN_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BROWN_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_CONCRETE_ROUNDEL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLACK_CONCRETE_ROUNDEL.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.METAL_GRATE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.METAL_GRATE_SLAB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.METAL_GRATE_STAIRS.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HANGING_WIRES.get(), RenderType.cutout());
    }
}
