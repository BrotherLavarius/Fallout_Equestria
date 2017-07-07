package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.api.items.helpers.armor.ItemHelmet;
import com.redsparkle.api.utils.Armor_material;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by hoijima on 3/5/2017.
 */
public class t60head extends ItemHelmet {
    public t60head(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(Armor_material.T60_ARMOR, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }

}
