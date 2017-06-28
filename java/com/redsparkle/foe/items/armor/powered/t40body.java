package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.helpers.armor.ItemBody;
import com.redsparkle.foe.utils.Armor_material;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by hoijima on 3/25/2017.
 */
public class t40body extends ItemBody {
    public t40body(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(Armor_material.T40_ARMOR, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }
}
