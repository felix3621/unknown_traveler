package io.github.felix3621.unknown_traveler.block.custom;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntity;
import io.github.felix3621.unknown_traveler.helper.TardisHelper;
import io.github.felix3621.unknown_traveler.util.capabilities.Capabilities;
import io.github.felix3621.unknown_traveler.util.capabilities.door.IDoorCapability;
import io.github.felix3621.unknown_traveler.util.capabilities.light.ILightCapability;
import io.github.felix3621.unknown_traveler.util.savedata.TardisPlacements;
import io.github.felix3621.unknown_traveler.util.savedata.TardisID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import static io.github.felix3621.unknown_traveler.block.BlockProperties.FACING;

public class TardisExteriorBlock extends BaseEntityBlock {
    private static final BlockPos tardis_ball_offset = new BlockPos(-14, -12, -14);
    public static final BlockPos tardis_ball_door_position = new BlockPos(0, 1, 13);
    public static final Direction tardis_ball_door_direction = Direction.SOUTH;

    public static final BooleanProperty SpawnAnimation = BooleanProperty.create("spawn_animation");
    public TardisExteriorBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any().setValue(SpawnAnimation, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(hand != player.getUsedItemHand())
            return InteractionResult.PASS;

        if(!level.isClientSide){
            level.getServer().execute(() -> {
                Direction DIRECTION = state.getValue(FACING);
                boolean create = false;
                Integer ID = ((TardisExteriorBlockEntity) level.getBlockEntity(pos)).getID();
                if (ID == -1) {
                    ID = TardisID.getID();
                    ((TardisExteriorBlockEntity) level.getBlockEntity(pos)).setID(ID);
                    create = true;
                }
                ServerLevel dim = TardisHelper.getTardisDim(level.getServer(), ID.toString());
                if (create) {
                    TardisPlacements.place(level.getServer().getLevel(level.dimension()), pos, DIRECTION, ID);

                    StructureTemplate structure = level.getServer().getStructureManager().getOrCreate(new ResourceLocation(UnknownTraveler.MODID, "tardis/ball"));
                    structure.placeInWorld(dim, tardis_ball_offset, tardis_ball_offset, new StructurePlaceSettings().setIgnoreEntities(false), level.random, 3);

                    BlockState state1 = ModBlocks.TARDIS_INTERIOR_BLOCK_OPEN.get().defaultBlockState().setValue(FACING, tardis_ball_door_direction);

                    dim.setBlockAndUpdate(tardis_ball_door_position, state1);
                    dim.getServer().tell(new TickTask(1, () -> {
                        IDoorCapability cap = dim.getCapability(Capabilities.TARDIS_DOOR_CAP).orElse(null);
                        if (cap != null) {
                            cap.addDoorPos(tardis_ball_door_position, state1);
                        }
                    }));
                }

                level.setBlockAndUpdate(pos, ModBlocks.TARDIS_EXTERIOR_BLOCK_OPEN.get().defaultBlockState());
                TardisPlacements.open(ID);
            });
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.TARDIS_EXTERIOR_BLOCK_ENTITY.get().create(pPos, pState);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(SpawnAnimation);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
