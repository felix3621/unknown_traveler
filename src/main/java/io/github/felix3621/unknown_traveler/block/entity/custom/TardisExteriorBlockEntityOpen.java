package io.github.felix3621.unknown_traveler.block.entity.custom;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.BlockProperties;
import io.github.felix3621.unknown_traveler.block.custom.TardisExteriorBlock;
import io.github.felix3621.unknown_traveler.block.custom.TardisExteriorBlockOpen;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
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

public class TardisExteriorBlockEntityOpen extends BlockEntity implements GeoAnimatable {
    private static final float tardis_ball_spawn_x = TardisExteriorBlock.tardis_ball_door_position.getX() + 0.5F;
    private static final float tardis_ball_spawn_y = TardisExteriorBlock.tardis_ball_door_position.getY() - 1F;
    private static final float tardis_ball_spawn_z = TardisExteriorBlock.tardis_ball_door_position.getZ() + 0.5F;
    private static final float tardis_ball_spawn_yaw = TardisExteriorBlock.tardis_ball_door_direction.toYRot();

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

    public static void tick (Level level, BlockPos pos, BlockState state, TardisExteriorBlockEntityOpen entity) {
        if (!level.isClientSide()) {
            List<Entity> collidedEntities = new ArrayList<>();
            switch (level.getBlockState(pos).getValue(BlockProperties.FACING)) {
                case NORTH -> collidedEntities = level.getEntitiesOfClass(Entity.class, new AABB(pos.north()));
                case SOUTH -> collidedEntities = level.getEntitiesOfClass(Entity.class, new AABB(pos.south()));
                case EAST -> collidedEntities = level.getEntitiesOfClass(Entity.class, new AABB(pos.east()));
                case WEST -> collidedEntities = level.getEntitiesOfClass(Entity.class, new AABB(pos.west()));
            }

            Vec3 tardisCenter = pos.getCenter();
            Vec3 tardisAboveCenter = pos.above().getCenter();
            Vec3 tardisBelowCenter = pos.below().getCenter();

            Direction tardisDirection = state.getValue(BlockProperties.FACING);

            if (!collidedEntities.isEmpty()) {
                final ServerLevel interior = level.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(UnknownTraveler.MODID, "tardis_"+entity.ID.toString())));
                if (interior != null) {
                    for (Entity entity1 : collidedEntities) {
                        Vec3 playerCenter = entity1.position();

                        if (playerCenter.distanceTo(tardisCenter) <= 1d || playerCenter.distanceTo(tardisAboveCenter) <= 1d || playerCenter.distanceTo(tardisBelowCenter) <= 1d) {
                            Set<RelativeMovement> set = EnumSet.noneOf(RelativeMovement.class);

                            set.add(RelativeMovement.X);
                            set.add(RelativeMovement.Y);
                            set.add(RelativeMovement.Z);

                            entity1.teleportTo(
                                    interior,
                                    tardis_ball_spawn_x,
                                    tardis_ball_spawn_y,
                                    tardis_ball_spawn_z,
                                    set,
                                    entity1.getYRot()+tardisDirection.toYRot()+tardis_ball_spawn_yaw,
                                    entity1.getXRot()
                            );
                        }
                    }
                }
            }
        }
    }
}
