package io.github.felix3621.unknown_traveler.util.capabilities.light;

import io.github.felix3621.unknown_traveler.block.BlockProperties;
import io.github.felix3621.unknown_traveler.block.custom.LightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class LightCapability implements ILightCapability{
    private final List<BlockPos> lights;
    private int lightlevel;
    private final Level world;

    @Override
    public void addLightPos(BlockPos pos) {
        this.lights.add(pos);

        if (!this.world.isClientSide) {
            BlockState state = this.world.getBlockState(pos);
            if (state.hasProperty(BlockProperties.LIGHT)) {
                this.world.setBlockAndUpdate(pos, state.setValue(BlockProperties.LIGHT, this.lightlevel));
            }
        }
    }

    @Override
    public void setLight(int level) {
        if (level >= 0 && level <= 15) {
            if (this.lightlevel != level) {
                for (BlockPos pos : this.lights) {
                    BlockState state = this.world.getBlockState(pos);
                    if (state.getBlock() instanceof LightBlock) {
                        this.world.setBlockAndUpdate(pos, state.setValue(BlockProperties.LIGHT, level));
                    } else {
                        this.lights.remove(pos);
                    }
                }
                this.lightlevel = level;
            }
        }
    }

    @Override
    public int getLight() {
        return this.lightlevel;
    }

    public LightCapability(Level world) {
        this.lights = new ArrayList<>();
        this.lightlevel = 0;
        this.world = world;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        ListTag lightList = new ListTag();
        for (BlockPos pos : this.lights) {
            lightList.add(LongTag.valueOf(pos.asLong()));
        }
        tag.put("light", lightList);
        tag.putInt("light_level", this.lightlevel);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        ListTag lightList = compoundTag.getList("light", ListTag.TAG_LONG);
        for (Tag pos : lightList) {
            this.lights.add(BlockPos.of(((LongTag) pos).getAsLong()));
        }
        this.lightlevel = compoundTag.getInt("light_level");
    }
}
