package io.github.felix3621.unknown_traveler.util.capabilities.light;

import io.github.felix3621.unknown_traveler.util.capabilities.Capabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public interface ILightCapability extends INBTSerializable<CompoundTag> {
    void addLightPos(BlockPos pos);
    void setLight(int level);
    int getLight();

    class DimProvider implements ICapabilitySerializable<CompoundTag> {
        private final ILightCapability capability;
        public DimProvider(Level world) {
            this.capability = new LightCapability(world);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == Capabilities.TARDIS_DIM ? (LazyOptional<T>) LazyOptional.of(() -> capability) : LazyOptional.empty();
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
