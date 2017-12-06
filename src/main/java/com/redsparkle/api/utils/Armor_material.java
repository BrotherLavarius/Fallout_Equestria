package com.redsparkle.api.utils;

import com.redsparkle.foe.main;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by hoijima on 3/21/2017.
 */
public class Armor_material {
    public static ItemArmor.ArmorMaterial X01_ARMOR = EnumHelper.addArmorMaterial("X01_ARMOR", main.MODID + ":x01", 65, new int[]{65, 95, 85, 40}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 66);
    public static ItemArmor.ArmorMaterial T60_ARMOR = EnumHelper.addArmorMaterial("T60_ARMOR", main.MODID + ":t60", 80, new int[]{45, 58, 51, 45}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 50);
    public static ItemArmor.ArmorMaterial T50_ARMOR = EnumHelper.addArmorMaterial("T50_ARMOR", main.MODID + ":t50", 45, new int[]{35, 65, 45, 25}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 56);
    public static ItemArmor.ArmorMaterial T40_ARMOR = EnumHelper.addArmorMaterial("T40_ARMOR", main.MODID + ":t40", 35, new int[]{25, 45, 39, 20}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 25);
}
