package io.github.felix3621.unknown_traveler.block.entity.custom;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.custom.TardisExteriorBlockOpen;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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

public class TardisExteriorBlockEntityOpen extends BlockEntity implements GeoAnimatable {
    private Integer ID = -1;
    private final Animation animation = new Animation();
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt(UnknownTraveler.MODID + ":tardis_id",this.ID);
    }

    @Override
    public void load(CompoundTag pTag) {
        this.ID = pTag.getInt(UnknownTraveler.MODID + ":tardis_id");
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer id) {
        this.ID = id;
    }


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public TardisExteriorBlockEntityOpen(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TARDIS_EXTERIOR_BLOCK_ENTITY_OPEN.get(), pos, state);

        this.animation.registerAnimation("DEPLOY", RawAnimation.begin().thenPlay("tardis.animation.spawn"));
        this.animation.registerAnimation("REMOVE", RawAnimation.begin().thenPlay("tardis.animation.despawn"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller", 0, this::deployAnimController));
    }

    protected <E extends TardisExteriorBlockEntityOpen> PlayState deployAnimController(final AnimationState<E> state) {
        this.animation.TEBEOanimationTick(state);

        if (level.getBlockState(worldPosition).getValue(TardisExteriorBlockOpen.SpawnAnimation)) {
            playSpawnAnimation();
        }

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

    @Override
    public void handleUpdateTag(CompoundTag tag) {}

    @Override
    public CompoundTag getUpdateTag() {
        return new CompoundTag();
    }
}
