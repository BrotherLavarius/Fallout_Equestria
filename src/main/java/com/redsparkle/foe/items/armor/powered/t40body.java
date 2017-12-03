package com.redsparkle.foe.items.armor.powered;

import com.redsparkle.api.items.helpers.armor.ItemBody;
import com.redsparkle.api.utils.Armor_material;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

/**
 * Created by hoijima on 3/25/2017.
 */
public class t40body extends ItemBody {
    public t40body(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(Armor_material.T40_ARMOR, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {
    }
}
