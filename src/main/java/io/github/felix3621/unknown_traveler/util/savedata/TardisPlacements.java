package io.github.felix3621.unknown_traveler.util.savedata;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntity;
import io.github.felix3621.unknown_traveler.block.entity.custom.TardisExteriorBlockEntityOpen;
import io.github.felix3621.unknown_traveler.helper.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = UnknownTraveler.MODID)
public class TardisPlacements {
    public static void open(Integer ID) {
        Map<String, Object> map = SDControl.trans_tardis_data.get(ID.toString());
        if (map == null) map = new HashMap<>();
        map.put("door", true);
        SDControl.trans_tardis_data.put(ID.toString(), map);
        SDControl.store();
    }
    public static void open(String ID) {
        Map<String, Object> map = SDControl.trans_tardis_data.get(ID);
        if (map == null) map = new HashMap<>();
        map.put("door", true);
        SDControl.trans_tardis_data.put(ID, map);
        SDControl.store();
    }
    public static void close(Integer ID) {
        Map<String, Object> map = SDControl.trans_tardis_data.get(ID.toString());
        if (map == null) map = new HashMap<>();
        map.put("door", false);
        SDControl.trans_tardis_data.put(ID.toString(), map);
        SDControl.store();
    }
    public static void close(String ID) {
        Map<String, Object> map = SDControl.trans_tardis_data.get(ID);
        if (map == null) map = new HashMap<>();
        map.put("door", false);
        SDControl.trans_tardis_data.put(ID, map);
        SDControl.store();
    }
    public static void place(ServerLevel serverLevel, BlockPos pos, Direction facing, Integer ID) {
        Map<String, Object> EXT = new HashMap<>();

        EXT.put("pos", pos);
        EXT.put("dimension", serverLevel.getLevel().dimension());
        EXT.put("facing", facing.toString());

        SDControl.tardis_exterior.put(ID.toString(), EXT);
        SDControl.store();
    }

    @SubscribeEvent
    public static void tickPlace(TickEvent.LevelTickEvent Event) {
        if (!(Event.level instanceof ServerLevel serverWorld)) return;
        for (Map.Entry<String, Map<String, Object>> aEXT : SDControl.tardis_exterior.entrySet()) {
            BlockPos pos = (BlockPos) aEXT.getValue().get("pos");

            Direction facing;
            switch ((String) aEXT.getValue().get("facing")) {
                case "north" -> facing = Direction.NORTH;
                case "south" -> facing = Direction.SOUTH;
                case "east" -> facing = Direction.EAST;
                case "west" -> facing = Direction.WEST;
                default -> {
                    facing = Direction.NORTH;
                    return;
                }
            }

            ResourceKey<?> dimension = (ResourceKey<?>) aEXT.getValue().get("dimension");

            Integer id = Helper.ParseIntWithFallback(aEXT.getKey(), -1);


            Boolean door = false;

            if (id != -1) {
                Map<String, Object> transData = SDControl.trans_tardis_data.get(id.toString());
                if (transData != null) {
                    door = (Boolean) transData.get("door");
                }
            }

            if (serverWorld.dimension() == dimension) {


                if (door) {
                    if (serverWorld.getBlockState(pos).getBlock() != ModBlocks.TARDIS_EXTERIOR_BLOCK_OPEN.get())
                        serverWorld.setBlockAndUpdate(pos, ModBlocks.TARDIS_EXTERIOR_BLOCK_OPEN.get().defaultBlockState());
                } else {
                    if (serverWorld.getBlockState(pos).getBlock() != ModBlocks.TARDIS_EXTERIOR_BLOCK.get())
                        serverWorld.setBlockAndUpdate(pos, ModBlocks.TARDIS_EXTERIOR_BLOCK.get().defaultBlockState());
                }

                if (serverWorld.getBlockState(pos).getValue(BlockStateProperties.HORIZONTAL_FACING) != facing)
                    serverWorld.setBlockAndUpdate(pos, serverWorld.getBlockState(pos).setValue(BlockStateProperties.HORIZONTAL_FACING, facing));

                if (serverWorld.getBlockEntity(pos) instanceof TardisExteriorBlockEntity)
                    ((TardisExteriorBlockEntity) serverWorld.getBlockEntity(pos)).setID(id);
                else if (serverWorld.getBlockEntity(pos) instanceof TardisExteriorBlockEntityOpen)
                    ((TardisExteriorBlockEntityOpen) serverWorld.getBlockEntity(pos)).setID(id);
            }
        }
    }
}
