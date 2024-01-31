package io.github.felix3621.unknown_traveler.network;

import io.github.felix3621.unknown_traveler.network.packets.SyncDimensionListMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Set;

public class ClientPacketHandler {
    public static void handleDimSyncPacket(SyncDimensionListMessage mes){

        if(Minecraft.getInstance().player == null || Minecraft.getInstance().player.connection.levels() == null)
            return;

        Set<ResourceKey<Level>> levels = Minecraft.getInstance().player.connection.levels();
        //If this player knows about this dimension
        if(levels.contains(mes.level)){
            //If remove
            if(!mes.add){
                levels.remove(mes.level);
            }
        }
        //If player does not know about this dim and we're trying to add it
        else if(mes.add){
            levels.add(mes.level);
        }
    }
}
