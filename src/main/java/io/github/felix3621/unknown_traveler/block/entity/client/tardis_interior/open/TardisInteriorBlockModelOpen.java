package io.github.felix3621.unknown_traveler.block.entity.client.tardis_interior.open;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisInteriorBlockEntityOpen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TardisInteriorBlockModelOpen extends GeoModel<TardisInteriorBlockEntityOpen> {
    @Override
    public ResourceLocation getModelResource(TardisInteriorBlockEntityOpen object) {
        return new ResourceLocation(UnknownTraveler.MODID, "geo/tardis_open_interior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TardisInteriorBlockEntityOpen object) {
        return new ResourceLocation(UnknownTraveler.MODID, "textures/block/tardis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TardisInteriorBlockEntityOpen animatable) {
        return new ResourceLocation(UnknownTraveler.MODID, "animations/tardis.animation.json");
    }
}
