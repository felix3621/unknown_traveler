package io.github.felix3621.unknown_traveler.util.capabilities;

import io.github.felix3621.unknown_traveler.util.capabilities.light.ILightCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class Capabilities {
    public static final Capability<ILightCapability> TARDIS_DIM = CapabilityManager.get(new CapabilityToken<>(){});
}
