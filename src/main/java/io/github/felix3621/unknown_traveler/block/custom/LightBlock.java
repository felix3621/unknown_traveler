package io.github.felix3621.unknown_traveler.block.custom;

import io.github.felix3621.unknown_traveler.block.BlockProperties;
import io.github.felix3621.unknown_traveler.util.capabilities.Capabilities;
import io.github.felix3621.unknown_traveler.util.capabilities.light.ILightCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.server.TickTask;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class LightBlock extends Block {
    public LightBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockProperties.LIGHT, 0));
    }

    @Override
    public void onPlace(BlockState p_60566_, Level pLevel, BlockPos pPos, BlockState p_60569_, boolean p_60570_) {
        if (!pLevel.isClientSide) {
            pLevel.getServer().tell(new TickTask(1, () -> {
                ILightCapability cap = pLevel.getCapability(Capabilities.TARDIS_LIGHT_CAP).orElse(null);
                if (cap != null) {
                    cap.addLightPos(pPos);
                }
            }));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BlockProperties.LIGHT);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(BlockProperties.LIGHT);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState p_60457_) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(BlockProperties.LIGHT);
    }
}
