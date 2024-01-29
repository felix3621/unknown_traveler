package io.github.felix3621.unknown_traveler.block.entity.client;

import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TardisExteriorBlockRenderer extends GeoBlockRenderer<TardisExteriorBlockEntity> {
    public TardisExteriorBlockRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new TardisExteriorBlockModel());
    }
}
