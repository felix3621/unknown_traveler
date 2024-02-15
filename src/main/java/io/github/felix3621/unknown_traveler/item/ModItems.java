package io.github.felix3621.unknown_traveler.item;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.item.custom.ISonicPart;
import io.github.felix3621.unknown_traveler.item.custom.SonicBasePart;
import io.github.felix3621.unknown_traveler.item.custom.SonicScrewdriver;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, UnknownTraveler.MODID);


    public static final RegistryObject<Item> EMITTER = ITEMS.register("sonic_parts/emitter",
            () -> new SonicBasePart(ISonicPart.SonicPart.EMITTER));
    public static final RegistryObject<Item> ACTIVATOR = ITEMS.register("sonic_parts/activator",
            () -> new SonicBasePart(ISonicPart.SonicPart.ACTIVATOR));
    public static final RegistryObject<Item> HANDLE = ITEMS.register("sonic_parts/handle",
            () -> new SonicBasePart(ISonicPart.SonicPart.HANDLE));
    public static final RegistryObject<Item> END = ITEMS.register("sonic_parts/end",
            () -> new SonicBasePart(ISonicPart.SonicPart.END));

    public static final RegistryObject<Item> SCREWDRIVER = ITEMS.register("sonic_parts/screwdriver",
            () -> new SonicScrewdriver(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
