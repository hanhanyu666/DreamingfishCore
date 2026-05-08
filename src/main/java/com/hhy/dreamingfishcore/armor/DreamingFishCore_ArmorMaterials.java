package com.hhy.dreamingfishcore.armor;

import com.hhy.dreamingfishcore.DreamingFishCore;
import com.hhy.dreamingfishcore.item.DreamingFishCore_Items;
import java.util.EnumMap;
import java.util.List;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public final class DreamingFishCore_ArmorMaterials {
    public static final ArmorMaterial SUPPORTER = new ArmorMaterial(
            defense(4, 7, 5, 4),
            25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(DreamingFishCore_Items.SUPPORTER_HAT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(DreamingFishCore.MODID, "supporter"))),
            1.0F,
            0.0F
    );

    private DreamingFishCore_ArmorMaterials() {}

    private static EnumMap<ArmorItem.Type, Integer> defense(int boots, int leggings, int chestplate, int helmet) {
        EnumMap<ArmorItem.Type, Integer> values = new EnumMap<>(ArmorItem.Type.class);
        values.put(ArmorItem.Type.BOOTS, boots);
        values.put(ArmorItem.Type.LEGGINGS, leggings);
        values.put(ArmorItem.Type.CHESTPLATE, chestplate);
        values.put(ArmorItem.Type.HELMET, helmet);
        return values;
    }
}
