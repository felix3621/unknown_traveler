package io.github.felix3621.unknown_traveler.network;

import io.github.felix3621.unknown_traveler.helper.Helper;
import io.github.felix3621.unknown_traveler.network.packets.SyncDimensionListMessage;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Network {
    public static final String NET_VERSION = "1.0";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(Helper.createRL("main"), () -> NET_VERSION, NET_VERSION::equals, NET_VERSION::equals);

    public static int ID = 0;

    public static void registerPackets(){
        INSTANCE.registerMessage(id(), SyncDimensionListMessage.class, SyncDimensionListMessage::encode, SyncDimensionListMessage::decode, SyncDimensionListMessage::handle);
    }

    public static void sendPacketToAll(Object message){
        Network.INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    public static int id(){
        return ++ID;
    }
}
