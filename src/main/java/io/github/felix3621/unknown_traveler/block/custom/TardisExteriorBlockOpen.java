package io.github.felix3621.unknown_traveler.block.custom;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.block.entity.ModBlockEntities;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntityOpen;
import io.github.felix3621.unknown_traveler.helper.TardisHelper;
import io.github.felix3621.unknown_traveler.util.savedata.TardisPlacements;
import io.github.felix3621.unknown_traveler.util.savedata.TardisID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import static io.github.felix3621.unknown_traveler.block.BlockProperties.FACING;

public class TardisExteriorBlockOpen extends BaseEntityBlock {
    public static final BooleanProperty SpawnAnimation = BooleanProperty.create("spawn_animation");
    public TardisExteriorBlockOpen(Properties properties) {
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

                Integer ID = ((TardisExteriorBlockEntityOpen) level.getBlockEntity(pos)).getID();
                if (ID == -1) {
                    ID = TardisID.getID();
                    ((TardisExteriorBlockEntityOpen) level.getBlockEntity(pos)).setID(ID);
                    create = true;
                }
                ServerLevel dim = TardisHelper.getTardisDim(level.getServer(), ID.toString());
                if (create) {
                    TardisPlacements.place(level.getServer().getLevel(level.dimension()), pos, DIRECTION, ID);

                    StructureTemplate structure = level.getServer().getStructureManager().getOrCreate(new ResourceLocation(UnknownTraveler.MODID, "tardis/ball"));
                    structure.placeInWorld(dim, new BlockPos(-15, -15, -15), new BlockPos(-15, -15, -15), new StructurePlaceSettings().setIgnoreEntities(false), level.random, 3);
                }

                level.setBlockAndUpdate(pos, ModBlocks.TARDIS_EXTERIOR_BLOCK.get().defaultBlockState());
                TardisPlacements.close(ID);
            });
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.TARDIS_EXTERIOR_BLOCK_ENTITY_OPEN.get().create(pPos, pState);
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

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState pos, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.TARDIS_EXTERIOR_BLOCK_ENTITY_OPEN.get(), TardisExteriorBlockEntityOpen::tick);
    }
}
