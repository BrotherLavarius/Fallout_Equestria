package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.api.items.helpers.armor.ItemHelmet;
import com.redsparkle.api.utils.Armor_material;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by hoijima on 3/5/2017.
 */
public class t40legs extends ItemHelmet {
    public t40legs(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(Armor_material.T40_ARMOR, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }
}
