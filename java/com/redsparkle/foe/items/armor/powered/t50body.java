package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.helpers.armor.ItemBody;
import com.redsparkle.foe.utils.Armor_material;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by hoijima on 3/25/2017.
 */
public class t50body extends ItemBody {
    public t50body(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(Armor_material.T50_ARMOR, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }
}
