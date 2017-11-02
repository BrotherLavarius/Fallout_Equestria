package com.redsparkle.foe.items.guns.ammo.TenMM;

import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;

/**
 * Created by NENYN on 1/2/2017.
 */
public class TenMMClip extends Item_AmmoHolder {
    public TenMMClip(String itemName, int clipsize, String clipInfo) {
        super(itemName, clipsize, clipInfo);
    }
//    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
//        EntityPlayer playerIn = (EntityPlayer) entityLiving;
//        ItemStack Istack = ItemClipHelpers.TenMMClipStackHelper(stack, worldIn, playerIn, clipsize);
//        return Istack;
//    }
}
