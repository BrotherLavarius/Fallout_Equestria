package com.redsparkle.foe.items.saddlebags.ammo;

import com.redsparkle.foe.Init.InitCreativeTabs;
import com.redsparkle.foe.items.FoeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 31.08.17.
 */
public class Seven_mm_bulletCase extends FoeItem {
    public int MaxDamage = 1;

    public Seven_mm_bulletCase(String name) {

        super(name);
        final int NUMBER_OF_BOXES = 40;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxStackSize(64);
        this.setMaxDamage(MaxDamage);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Case from a 7mm bullet");
    }
}
