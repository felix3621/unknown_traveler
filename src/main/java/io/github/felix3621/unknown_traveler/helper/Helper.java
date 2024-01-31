package io.github.felix3621.unknown_traveler.helper;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import net.minecraft.resources.ResourceLocation;

public class Helper {
    public static final ResourceLocation createRL(String name){
        return new ResourceLocation(UnknownTraveler.MODID, name);
    }

    public static int ParseIntWithFallback(String str, int fallback) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
