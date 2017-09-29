package com.redsparkle.foe.items;

import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class FoeScrap extends FoeItem {
    public int MaxDamage = 1;

    public FoeScrap(String name) {
        super(name);
        final int NUMBER_OF_BOXES = 64;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_scrap);   // the item will appear on the Miscellaneous tab in creative
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("This is use full...for some thing i guess?");
    }
}
