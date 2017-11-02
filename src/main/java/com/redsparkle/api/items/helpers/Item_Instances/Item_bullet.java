package com.redsparkle.api.items.helpers.Item_Instances;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 19.06.17.
 */
public class Item_bullet extends FoeItem {
    public Item_bullet(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("place holder bullet");
    }
}
