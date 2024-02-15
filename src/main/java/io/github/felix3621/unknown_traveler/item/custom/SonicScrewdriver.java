package io.github.felix3621.unknown_traveler.item.custom;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SonicScrewdriver extends Item implements ISonicScrewdriver{
    private static final String sonic_type = UnknownTraveler.MODID+":sonic_type";

    public SonicScrewdriver(Properties properties) {
        super(properties);
    }

    public static void setType(ItemStack screwdriver, SonicPartTypes type) {
        CompoundTag tag = screwdriver.getOrCreateTag();
        tag.putInt(sonic_type, type.getId());
    }

    public static int getType(ItemStack screwdriver) {
        CompoundTag tag = screwdriver.getOrCreateTag();
        if (tag.contains(sonic_type)) {
            return tag.getInt(sonic_type);
        }
        return SonicPartTypes.MK1.getId();
    }
}
