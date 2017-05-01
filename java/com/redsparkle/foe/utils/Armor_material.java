package com.redsparkle.foe.utils;

import com.redsparkle.foe.main;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by hoijima on 3/21/2017.
 */
public class Armor_material {
    public static ItemArmor.ArmorMaterial X01_ARMOR = EnumHelper.addArmorMaterial("X01_ARMOR", main.MODID + ":x01", 35, new int[]{39, 91, 71, 41}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 35);
    public static ItemArmor.ArmorMaterial T60_ARMOR = EnumHelper.addArmorMaterial("T60_ARMOR", main.MODID + ":t60", 30, new int[]{30, 80, 60, 30}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 30);
    public static ItemArmor.ArmorMaterial T50_ARMOR = EnumHelper.addArmorMaterial("T50_ARMOR", main.MODID + ":t50", 21, new int[]{21, 69, 48, 25}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 21);
    public static ItemArmor.ArmorMaterial T40_ARMOR = EnumHelper.addArmorMaterial("T40_ARMOR", main.MODID + ":t40", 16, new int[]{18, 54, 39, 20}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 16);

}
