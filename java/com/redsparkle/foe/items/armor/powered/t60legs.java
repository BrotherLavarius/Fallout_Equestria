package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.utils.Armor_material;
import com.redsparkle.foe.items.armor.powered.init.ItemHelmet;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by hoijima on 3/5/2017.
 */
public class t60legs extends ItemHelmet {
    public t60legs(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(Armor_material.T60_ARMOR, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }

}
