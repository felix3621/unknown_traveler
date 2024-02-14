package io.github.felix3621.unknown_traveler.block.entity.client.tardis_exterior.open;

import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntityOpen;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TardisExteriorBlockRendererOpen extends GeoBlockRenderer<TardisExteriorBlockEntityOpen> {
    public TardisExteriorBlockRendererOpen(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new TardisExteriorBlockModelOpen());
    }
}
