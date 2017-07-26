package com.redsparkle.foe.items.guns.ammo.TenMM;

import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.api.items.helpers.guns.ItemClipHelpers;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by NENYN on 1/2/2017.
 */
public class TenMMClip extends Item_AmmoHolder {

    public TenMMClip() {
        final int NUMBER_OF_BOXES = 1;
        this.clipsize = GlobalsGunStats.TEN_MM.Clipsize();
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(clipsize);
        this.clipInfo = "Ammo clip for 10mm pistol";


    }


    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer playerIn = (EntityPlayer) entityLiving;
        ItemStack Istack = ItemClipHelpers.TenMMClipStackHelper(stack, worldIn, playerIn, clipsize);
        return Istack;

    }

}
