package io.github.felix3621.unknown_traveler.block.entity.client.tardis_interior.closed;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisInteriorBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TardisInteriorBlockModel extends GeoModel<TardisInteriorBlockEntity> {
    @Override
    public ResourceLocation getModelResource(TardisInteriorBlockEntity object) {
        return new ResourceLocation(UnknownTraveler.MODID, "geo/tardis_interior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TardisInteriorBlockEntity object) {
        return new ResourceLocation(UnknownTraveler.MODID, "textures/block/tardis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TardisInteriorBlockEntity animatable) {
        return new ResourceLocation(UnknownTraveler.MODID, "animations/tardis.animation.json");
    }
}
