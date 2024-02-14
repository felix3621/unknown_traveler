package io.github.felix3621.unknown_traveler.block.entity.client.tardis_exterior.open;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntityOpen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TardisExteriorBlockModelOpen extends GeoModel<TardisExteriorBlockEntityOpen> {
    @Override
    public ResourceLocation getModelResource(TardisExteriorBlockEntityOpen object) {
        return new ResourceLocation(UnknownTraveler.MODID, "geo/tardis_open_exterior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TardisExteriorBlockEntityOpen object) {
        return new ResourceLocation(UnknownTraveler.MODID, "textures/block/tardis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TardisExteriorBlockEntityOpen animatable) {
        return new ResourceLocation(UnknownTraveler.MODID, "animations/tardis.animation.json");
    }
}
