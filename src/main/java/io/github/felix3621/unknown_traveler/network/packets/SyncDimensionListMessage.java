package io.github.felix3621.unknown_traveler.network.packets;

import io.github.felix3621.unknown_traveler.network.ClientPacketHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncDimensionListMessage {
    public ResourceKey<Level> level;
    public boolean add = true;

    public SyncDimensionListMessage(ResourceKey<Level> level, boolean add) {
        this.level = level;
        this.add = add;
    }

    public static void encode(SyncDimensionListMessage mes, FriendlyByteBuf buf){
        buf.writeResourceKey(mes.level);
        buf.writeBoolean(mes.add);
    }

    public static SyncDimensionListMessage decode(FriendlyByteBuf buf){
        return new SyncDimensionListMessage(buf.readResourceKey(Registries.DIMENSION), buf.readBoolean());
    }

    public static void handle(SyncDimensionListMessage mes, Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> ClientPacketHandler.handleDimSyncPacket(mes));
        context.get().setPacketHandled(true);
    }
}
