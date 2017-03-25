package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.armor.powered.init.ItemBody;
import com.redsparkle.foe.items.armor.powered.init.t60Armor_material;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

/**
 * Created by hoijima on 3/25/2017.
 */
public class t60body extends ItemBody {
    public t60body(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(t60Armor_material.T60_ARMOR, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }
}
