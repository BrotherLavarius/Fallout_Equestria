package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.api.items.helpers.armor.ItemBody;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by hoijima on 3/25/2017.
 */
public class t40body extends ItemBody {
    public t40body(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) {
        super(materialIn, renderIndexIn, equipmentSlotIn, name);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }


}
