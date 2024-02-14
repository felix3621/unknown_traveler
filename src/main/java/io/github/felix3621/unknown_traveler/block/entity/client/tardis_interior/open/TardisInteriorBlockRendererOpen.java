package io.github.felix3621.unknown_traveler.block.entity.client.tardis_interior.open;

import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntityOpen;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisInteriorBlockEntityOpen;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TardisInteriorBlockRendererOpen extends GeoBlockRenderer<TardisInteriorBlockEntityOpen> {
    public TardisInteriorBlockRendererOpen(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new TardisInteriorBlockModelOpen());
    }
}
