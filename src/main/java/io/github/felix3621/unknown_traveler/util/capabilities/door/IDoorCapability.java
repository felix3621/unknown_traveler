package io.github.felix3621.unknown_traveler.util.capabilities.door;

import io.github.felix3621.unknown_traveler.util.capabilities.Capabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public interface IDoorCapability extends INBTSerializable<CompoundTag> {
    void addDoorPos(BlockPos pos, BlockState state);
    void tick();
    class DimProvider implements ICapabilitySerializable<CompoundTag> {
        private final IDoorCapability capability;
        public DimProvider(Level world) {
            this.capability = new DoorCapability(world);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == Capabilities.TARDIS_DOOR_CAP ? (LazyOptional<T>) LazyOptional.of(() -> capability) : LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            return capability.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            capability.deserializeNBT(nbt);
        }
    }
}
