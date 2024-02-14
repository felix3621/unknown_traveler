package io.github.felix3621.unknown_traveler.block.entity.client.tardis_exterior.closed;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TardisExteriorBlockModel extends GeoModel<TardisExteriorBlockEntity> {
    @Override
    public ResourceLocation getModelResource(TardisExteriorBlockEntity object) {
        return new ResourceLocation(UnknownTraveler.MODID, "geo/tardis_exterior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TardisExteriorBlockEntity object) {
        return new ResourceLocation(UnknownTraveler.MODID, "textures/block/tardis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TardisExteriorBlockEntity animatable) {
        return new ResourceLocation(UnknownTraveler.MODID, "animations/tardis.animation.json");
    }
}
