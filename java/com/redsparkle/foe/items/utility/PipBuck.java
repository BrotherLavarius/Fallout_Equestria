package com.redsparkle.foe.items.utility;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.helpers.Item_Instances.Item_pipbuck;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by NENYN on 12/16/2016.
 */
public class PipBuck extends Item_pipbuck {

    public PipBuck() {
        this.setMaxStackSize(1);
        this.setCreativeTab(InitCreativeTabs.Fallout_meds);   // the item will appear on the Miscellaneous tab in creative
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        final int FIRST_HOTBAR_SLOT_NUMBER = 0;
        final int LAST_HOTBAR_SLOT_NUMBER = 8;

        if (itemSlot < FIRST_HOTBAR_SLOT_NUMBER || itemSlot > LAST_HOTBAR_SLOT_NUMBER) return;
        if (worldIn.isRemote) return;
        if (!(entityIn instanceof EntityPlayerMP)) return;

    }

    // adds 'tooltip' text
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        tooltip.add("Before-War tech");
        tooltip.add(" Chiney!And makes beeping sounds some time!");
    }
}
