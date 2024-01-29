package io.github.felix3621.unknown_traveler.block.entity.custom;

import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TardisExteriorBlockEntity extends BlockEntity implements GeoAnimatable {
    protected static final RawAnimation DEPLOY = RawAnimation.begin().thenPlay("tardis.animation.spawn");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public TardisExteriorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TARDIS_EXTERIOR_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        //controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    protected <E extends TardisExteriorBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
        return state.setAndContinue(DEPLOY);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}
