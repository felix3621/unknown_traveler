package io.github.felix3621.unknown_traveler.util.capabilities;

import io.github.felix3621.unknown_traveler.util.capabilities.door.IDoorCapability;
import io.github.felix3621.unknown_traveler.util.capabilities.light.ILightCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class Capabilities {
    public static final Capability<ILightCapability> TARDIS_LIGHT_CAP = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IDoorCapability> TARDIS_DOOR_CAP = CapabilityManager.get(new CapabilityToken<>(){});
}
