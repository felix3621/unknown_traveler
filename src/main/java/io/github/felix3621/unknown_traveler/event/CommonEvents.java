package io.github.felix3621.unknown_traveler.event;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.command.TravelerCommands;
import io.github.felix3621.unknown_traveler.dimensions.TardisDimensions;
import io.github.felix3621.unknown_traveler.helper.WorldHelper;
import io.github.felix3621.unknown_traveler.util.capabilities.Capabilities;
import io.github.felix3621.unknown_traveler.util.capabilities.door.IDoorCapability;
import io.github.felix3621.unknown_traveler.util.capabilities.light.ILightCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnknownTraveler.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        TravelerCommands.register(event.getDispatcher());
    }
    public static final ResourceLocation LIGHT_CAP = new ResourceLocation(UnknownTraveler.MODID, "light");
    public static final ResourceLocation DOOR_CAP = new ResourceLocation(UnknownTraveler.MODID, "door");

    @SubscribeEvent
    public static void attachWorldCaps(AttachCapabilitiesEvent<Level> event) {
        if (WorldHelper.areDimensionTypesSame(event.getObject(), TardisDimensions.DimensionTypes.TARDIS_TYPE)) {
            event.addCapability(LIGHT_CAP, new ILightCapability.DimProvider(event.getObject()));
            event.addCapability(DOOR_CAP, new IDoorCapability.DimProvider(event.getObject()));
        }
    }

    @SubscribeEvent
    public static void capabilityTick(TickEvent.LevelTickEvent Event) {
        if (!(Event.level instanceof ServerLevel serverWorld)) return;

        IDoorCapability cap = serverWorld.getCapability(Capabilities.TARDIS_DOOR_CAP).orElse(null);
        if (cap != null) {
            cap.tick();
        }
    }
}
