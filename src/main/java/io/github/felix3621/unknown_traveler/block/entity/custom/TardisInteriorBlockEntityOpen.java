package io.github.felix3621.unknown_traveler.block.entity.custom;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.BlockProperties;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import io.github.felix3621.unknown_traveler.helper.Helper;
import io.github.felix3621.unknown_traveler.helper.TardisHelper;
import io.github.felix3621.unknown_traveler.util.savedata.SDControl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class TardisInteriorBlockEntityOpen extends BlockEntity implements GeoAnimatable {
    private final Animation animation = new Animation();
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public TardisInteriorBlockEntityOpen(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TARDIS_INTERIOR_BLOCK_ENTITY_OPEN.get(), pos, state);

        this.animation.registerAnimation("DEPLOY", RawAnimation.begin().thenPlay("tardis.animation.spawn"));
        this.animation.registerAnimation("REMOVE", RawAnimation.begin().thenPlay("tardis.animation.despawn"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller", 0, this::deployAnimController));
    }

    protected <E extends TardisExteriorBlockEntityOpen> PlayState deployAnimController(final AnimationState<E> state) {
        this.animation.TEBEOanimationTick(state);

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

    public static void tick (Level level, BlockPos pos, BlockState state, TardisInteriorBlockEntityOpen entity) {
        if (!level.isClientSide()) {
            List<Entity> collidedEntities = level.getEntitiesOfClass(Entity.class, new AABB(pos));

            Vec3 doorCenter = pos.getCenter().relative(level.getBlockState(pos).getValue(BlockProperties.FACING), 0.5d);
            Vec3 doorAboveCenter = pos.above().getCenter().relative(level.getBlockState(pos).getValue(BlockProperties.FACING), 0.5d);
            Vec3 doorBellowCenter = pos.below().getCenter().relative(level.getBlockState(pos).getValue(BlockProperties.FACING), 0.5d);

            if (!collidedEntities.isEmpty()) {
                String tardisId = TardisHelper.getTardisID(level);
                ResourceKey<Level> tardisExtDim = (ResourceKey<Level>) SDControl.tardis_exterior.get(tardisId).get("dimension");
                BlockPos tardisExtPos = (BlockPos) SDControl.tardis_exterior.get(tardisId).get("pos");

                Direction tardisDirection;

                switch (SDControl.tardis_exterior.get(tardisId).get("facing").toString()) {
                    case "south" -> tardisDirection = Direction.SOUTH;
                    case "east" -> tardisDirection = Direction.EAST;
                    case "west" -> tardisDirection = Direction.WEST;
                    default -> tardisDirection = Direction.NORTH;
                }

                Direction doorDirection = state.getValue(BlockProperties.FACING);

                tardisExtPos = tardisExtPos.relative(tardisDirection, 1);

                final ServerLevel exteriorDim = level.getServer().getLevel(tardisExtDim);
                if (exteriorDim != null) {
                    for (Entity entity1 : collidedEntities) {
                        Vec3 playerCenter = entity1.position();

                        Direction rel = tardisDirection.getClockWise();

                        if (
                                playerCenter.distanceTo(doorCenter) <= 0.6d || playerCenter.distanceTo(doorAboveCenter) <= 0.6d || playerCenter.distanceTo(doorBellowCenter) <= 0.6d ||
                                playerCenter.distanceTo(doorCenter.relative(rel, 0.25d)) <= 0.6d || playerCenter.distanceTo(doorAboveCenter.relative(rel, 0.25d)) <= 0.6d || playerCenter.distanceTo(doorBellowCenter.relative(rel, 0.25d)) <= 0.6d ||
                                playerCenter.distanceTo(doorCenter.relative(rel, -0.25d)) <= 0.6d || playerCenter.distanceTo(doorAboveCenter.relative(rel, -0.25d)) <= 0.6d || playerCenter.distanceTo(doorBellowCenter.relative(rel, -0.25d)) <= 0.6d
                        ) {
                            Set<RelativeMovement> set = EnumSet.noneOf(RelativeMovement.class);

                            set.add(RelativeMovement.X);
                            set.add(RelativeMovement.Y);
                            set.add(RelativeMovement.Z);

                            entity1.teleportTo(
                                    exteriorDim,
                                    tardisExtPos.getCenter().x,
                                    tardisExtPos.getY(),
                                    tardisExtPos.getCenter().z,
                                    set,
                                    entity1.getYRot()+doorDirection.toYRot()+tardisDirection.toYRot(),
                                    entity1.getXRot()
                            );
                        }
                    }
                }
            }
        }
    }
}
