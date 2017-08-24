package com.redsparkle.foe.items.saddlebags;

import com.redsparkle.api.items.helpers.armor.ItemBody;
import com.redsparkle.api.utils.Armor_material;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by hoijima on 24.08.17.
 */
public class Harness extends ItemBody {
    public Harness(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(Armor_material.T40_ARMOR, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }
}
