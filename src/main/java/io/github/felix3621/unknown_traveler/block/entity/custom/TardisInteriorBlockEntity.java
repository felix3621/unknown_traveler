package io.github.felix3621.unknown_traveler.block.entity.custom;

import io.github.felix3621.unknown_traveler.block.custom.TardisExteriorBlock;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

public class TardisInteriorBlockEntity extends BlockEntity implements GeoAnimatable {
    private final Animation animation = new Animation();
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public TardisInteriorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TARDIS_INTERIOR_BLOCK_ENTITY.get(), pos, state);

        this.animation.registerAnimation("DEPLOY", RawAnimation.begin().thenPlay("tardis.animation.spawn"));
        this.animation.registerAnimation("REMOVE", RawAnimation.begin().thenPlay("tardis.animation.despawn"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller", 0, this::deployAnimController));
    }

    protected <E extends TardisExteriorBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
        this.animation.TEBEanimationTick(state);

        return PlayState.CONTINUE;
    }

    public void playRemoveAnimation() {
        this.animation.play("REMOVE");
    }
    public void playSpawnAnimation() {
        this.animation.play("DEPLOY");
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object o) {
        return RenderUtils.getCurrentTick();
    }
}
