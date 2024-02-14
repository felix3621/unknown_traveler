package io.github.felix3621.unknown_traveler.block.entity.client.tardis_interior.closed;

import io.github.felix3621.unknown_traveler.block.entity.custom.TardisInteriorBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TardisInteriorBlockRenderer extends GeoBlockRenderer<TardisInteriorBlockEntity> {
    public TardisInteriorBlockRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new TardisInteriorBlockModel());
    }
}
