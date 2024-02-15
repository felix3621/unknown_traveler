package io.github.felix3621.unknown_traveler.item.custom;

import io.github.felix3621.unknown_traveler.UnknownTraveler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SonicBasePart extends Item implements ISonicPart {
    private static final String sonic_type = UnknownTraveler.MODID+":sonic_type";
    private final SonicPart sonicPart;

    public SonicBasePart(SonicPart part) {
        this(part, new Properties().stacksTo(1));
    }
    public SonicBasePart(SonicPart part, Properties properties) {
        super(properties);
        this.sonicPart = part;
    }

    public static void setType(ItemStack sonic, SonicComponentTypes type) {
        CompoundTag tag = sonic.getOrCreateTag();
        tag.putInt(sonic_type, type.getId());
    }

    public static int getType(ItemStack sonic) {
        CompoundTag tag = sonic.getOrCreateTag();
        if (tag.contains(sonic_type)) {
            return tag.getInt(sonic_type);
        }
        return SonicComponentTypes.MK1.getId();
    }

    @Override
    public SonicPart getSonicPart() {
        return sonicPart;
    }
}
