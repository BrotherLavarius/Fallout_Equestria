package com.redsparkle.api.items.helpers.Item_Instances;

import com.redsparkle.foe.items.FoeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by NENYN on 12/16/2016.
 */
public abstract class Item_pipbuck extends FoeItem {
    public Item_pipbuck(final String name) {
        super(name);
    }

    // adds 'tooltip' text
    @SideOnly(Side.CLIENT)
    public abstract void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced);
}
