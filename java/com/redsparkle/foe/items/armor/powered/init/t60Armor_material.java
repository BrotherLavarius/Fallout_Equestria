package com.redsparkle.foe.items.armor.powered.init;

import com.redsparkle.foe.main;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by hoijima on 3/21/2017.
 */
public class t60Armor_material {
    public static ItemArmor.ArmorMaterial T60_ARMOR = EnumHelper.addArmorMaterial("T60_ARMOR", main.MODID + ":t60head",90, new int[] {3, 8, 6, 3},1, SoundEvents.ITEM_ARMOR_EQUIP_IRON,90);
}
