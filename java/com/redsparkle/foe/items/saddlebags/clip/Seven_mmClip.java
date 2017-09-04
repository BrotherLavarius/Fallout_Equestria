package com.redsparkle.foe.items.saddlebags.clip;

import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.guns.ItemClipHelpers;
import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by hoijima on 30.08.17.
 */
public class Seven_mmClip extends Item_AmmoHolder {
    public Seven_mmClip(String fourTenMMClip) {
        super(fourTenMMClip);
        final int NUMBER_OF_BOXES = 1;
        this.clipsize = GlobalsGunStats.SEVEN_MM_RIFLE.getClipsize();
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(clipsize);
        this.clipInfo = "Ammo clip for 14mm pistol";
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer playerIn = (EntityPlayer) entityLiving;
        ItemStack Istack = ItemClipHelpers.SevenMMClipStackHelper(stack, worldIn, playerIn, clipsize);
        return Istack;
    }
}
