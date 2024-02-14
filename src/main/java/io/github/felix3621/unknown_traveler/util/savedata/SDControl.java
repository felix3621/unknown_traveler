package io.github.felix3621.unknown_traveler.util.savedata;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = UnknownTraveler.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SDControl {
    public static int tardis_id = 0;
    public static Map<String, Map<String, Object>> tardis_exterior = new HashMap<>();
    public static List<ResourceKey<Level>> tardis_dimensions = new ArrayList<>();
    public static Map<String, Map<String, Object>> trans_tardis_data = new HashMap<>();

    public static void reset() {
        tardis_id = 0;
        tardis_exterior = new HashMap<>();
        tardis_dimensions = new ArrayList<>();
        trans_tardis_data = new HashMap<>();
    }

    public static class data extends SavedData {
        public static data load(CompoundTag nbt) {
            reset();
            data loop = new data();

            // ==Data loading: start==
            // remember: variables may not be set in nbt, so add a fallback value

            tardis_id = nbt.contains("nextTardisId") ? nbt.getInt("nextTardisId") : 0;

            CompoundTag nbt_compound_tardis_exterior = nbt.contains("tardisExteriors") ? nbt.getCompound("tardisExteriors") : new CompoundTag();
            for (String key : nbt_compound_tardis_exterior.getAllKeys()) {
                Map<String, Object> map = new HashMap<>();
                CompoundTag nbt_tardis_exterior = nbt_compound_tardis_exterior.getCompound(key);

                ListTag nbt_pos = nbt_tardis_exterior.getList("pos", ListTag.TAG_INT);
                int pos_x = nbt_pos.getInt(0);
                int pos_y = nbt_pos.getInt(1);
                int pos_z = nbt_pos.getInt(2);
                map.put("pos",new BlockPos(pos_x, pos_y, pos_z));

                map.put("facing", nbt_tardis_exterior.getString("facing"));

                map.put("dimension", ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbt_tardis_exterior.getString("dimension"))));

                tardis_exterior.put(key, map);
            }

            ListTag nbt_dim = nbt.contains("tardisDimensions") ? nbt.getList("tardisDimensions", ListTag.TAG_STRING) : new ListTag();
            for (int i = 0; i < nbt_dim.size(); i++) {
                tardis_dimensions.add(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbt_dim.getString(i))));
            }

            CompoundTag nbt_compound_trans_tardis_data = nbt.contains("transTardisData") ? nbt.getCompound("transTardisData") : new CompoundTag();
            for (String key : nbt_compound_trans_tardis_data.getAllKeys()) {
                Map<String, Object> map = new HashMap<>();
                CompoundTag nbt_trans_tardis_data = nbt_compound_trans_tardis_data.getCompound(key);

                map.put("door", (nbt_trans_tardis_data.getByte("door") != (byte)0));

                trans_tardis_data.put(key, map);
            }

            // ==Data loading: end==
            return loop;
        }

        @Override
        public CompoundTag save(CompoundTag nbt) {
            // ==Data saving: start ==

            nbt.put("nextTardisId", IntTag.valueOf(tardis_id));

            CompoundTag nbt_compound_tardis_exetrior = new CompoundTag();
            for (Map.Entry<String, Map<String, Object>> entry_tardis_exterior : tardis_exterior.entrySet()) {
                CompoundTag nbt_tardis_exterior = new CompoundTag();

                ListTag pos = new ListTag();
                BlockPos block = ((BlockPos) entry_tardis_exterior.getValue().get("pos"));
                pos.add(IntTag.valueOf(block.getX()));
                pos.add(IntTag.valueOf(block.getY()));
                pos.add(IntTag.valueOf(block.getZ()));
                nbt_tardis_exterior.put("pos",pos);

                nbt_tardis_exterior.put("dimension", StringTag.valueOf(((ResourceKey<?>) entry_tardis_exterior.getValue().get("dimension")).location().toString()));

                nbt_tardis_exterior.put("facing", StringTag.valueOf(((String) entry_tardis_exterior.getValue().get("facing"))));

                nbt_compound_tardis_exetrior.put(entry_tardis_exterior.getKey(), nbt_tardis_exterior);
            }
            nbt.put("tardisExteriors", nbt_compound_tardis_exetrior);

            ListTag nbt_dim = new ListTag();
            for (ResourceKey<Level> dim : tardis_dimensions) nbt_dim.add(StringTag.valueOf(dim.location().toString()));
            nbt.put("tardisDimensions", nbt_dim);

            CompoundTag nbt_compound_trans_tardis_data = new CompoundTag();
            for (Map.Entry<String, Map<String, Object>> entry_trans_tardis_data : trans_tardis_data.entrySet()) {
                CompoundTag nbt_trans_tardis_data = new CompoundTag();

                nbt_trans_tardis_data.put("door", ByteTag.valueOf((Boolean) entry_trans_tardis_data.getValue().get("door") ? (byte)1 : (byte)0));

                nbt_compound_trans_tardis_data.put(entry_trans_tardis_data.getKey(), nbt_trans_tardis_data);
            }
            nbt.put("transTardisData", nbt_compound_trans_tardis_data);

            // ==Data saving: end ==
            return nbt;
        }
    }

    public static void store() {
        Event.DATA.setDirty();
    }

    //save and load from/to file
    @Mod.EventBusSubscriber(modid = UnknownTraveler.MODID)
    static class Event {
        static data DATA;

        @SubscribeEvent
        static void save(LevelEvent.Save event) {
            if (!(event.getLevel() instanceof ServerLevel serverWorld)) return;
            if (serverWorld.dimension() == Level.OVERWORLD)
                serverWorld.getDataStorage().set(UnknownTraveler.MODID, DATA);
        }

        @SubscribeEvent
        static void load(LevelEvent.Load event) {
            if (!(event.getLevel() instanceof ServerLevel serverWorld)) return;
            if (serverWorld.dimension() == Level.OVERWORLD)
                DATA = serverWorld.getDataStorage().computeIfAbsent(data::load, data::new, UnknownTraveler.MODID);
        }

        @SubscribeEvent
        static void unload(LevelEvent.Unload event) {
            if (!(event.getLevel() instanceof ServerLevel serverWorld)) return;
            if (serverWorld.dimension() == Level.OVERWORLD)
                reset();
        }
    }
}
