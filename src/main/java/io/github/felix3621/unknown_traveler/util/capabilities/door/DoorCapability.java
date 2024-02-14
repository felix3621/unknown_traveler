package io.github.felix3621.unknown_traveler.util.capabilities.door;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.BlockProperties;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.helper.TardisHelper;
import io.github.felix3621.unknown_traveler.util.savedata.SDControl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class DoorCapability implements IDoorCapability {
    private final List<Door> doors;
    private final Level world;

    @Override
    public void addDoorPos(BlockPos pos, BlockState state) {
        this.doors.add(new Door(pos, state));
    }

    public DoorCapability(Level world) {
        this.doors = new ArrayList<>();
        this.world = world;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        ListTag doorList = new ListTag();
        for (Door door : this.doors) {
            CompoundTag element = new CompoundTag();
            element.putLong("pos", door.pos().asLong());

            switch (door.state().getValue(BlockProperties.FACING)) {
                case NORTH -> element.putString("direction", "north");
                case SOUTH -> element.putString("direction", "south");
                case EAST -> element.putString("direction", "east");
                case WEST -> element.putString("direction", "west");
            }

            doorList.add(element);
        }
        tag.put("doors", doorList);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        ListTag doorList = compoundTag.getList("doors", ListTag.TAG_COMPOUND);
        for (Tag doorElem : doorList) {
            BlockPos pos = BlockPos.of(((CompoundTag) doorElem).getLong("pos"));

            Direction doorDir = null;

            switch (((CompoundTag) doorElem).getString("direction")) {
                case "south" -> doorDir = Direction.SOUTH;
                case "east" -> doorDir = Direction.EAST;
                case "west" -> doorDir = Direction.WEST;
                default -> doorDir = Direction.NORTH;
            }

            BlockState state = ModBlocks.TARDIS_INTERIOR_BLOCK.get().defaultBlockState().setValue(BlockProperties.FACING, doorDir);

            this.doors.add(new Door(pos, state));
        }
    }

    @Override
    public void tick() {
        for (Door door : this.doors) {
            boolean doorState = (boolean) SDControl.trans_tardis_data.get(TardisHelper.getTardisID(this.world)).get("door");
            if (doorState) {
                if (this.world.getBlockState(door.pos()).getBlock() != ModBlocks.TARDIS_INTERIOR_BLOCK_OPEN.get()) {
                    this.world.setBlockAndUpdate(door.pos(), ModBlocks.TARDIS_INTERIOR_BLOCK_OPEN.get().defaultBlockState());
                }
            } else {
                if (this.world.getBlockState(door.pos()).getBlock() != ModBlocks.TARDIS_INTERIOR_BLOCK.get()) {
                    this.world.setBlockAndUpdate(door.pos(), ModBlocks.TARDIS_INTERIOR_BLOCK.get().defaultBlockState());
                }
            }

            Block block = this.world.getBlockState(door.pos()).getBlock();
            if (block != ModBlocks.TARDIS_INTERIOR_BLOCK.get() && block != ModBlocks.TARDIS_INTERIOR_BLOCK_OPEN.get()) return;

            if (this.world.getBlockState(door.pos()).getValue(BlockProperties.FACING) != door.state().getValue(BlockProperties.FACING)) {
                this.world.setBlockAndUpdate(door.pos(), this.world.getBlockState(door.pos()).setValue(BlockProperties.FACING, door.state().getValue(BlockProperties.FACING)));
            }
        }
    }
}
