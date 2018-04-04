package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.api.items.helpers.armor.ItemHelmet;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by hoijima on 3/5/2017.
 */
public class t40head extends ItemHelmet {
    public t40head(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) {
        super(materialIn, renderIndexIn, equipmentSlotIn, name);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }
}
