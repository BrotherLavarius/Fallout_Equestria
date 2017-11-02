package com.redsparkle.foe.items.saddlebags.ammo;

import com.redsparkle.foe.Init.InitCreativeTabs;
import com.redsparkle.api.items.helpers.Item_Instances.FoeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 31.08.17.
 */
public class Seven_mmAmmo extends FoeItem {
    public Seven_mmAmmo(String seven_mmAmmo) {
        super(seven_mmAmmo);
        final int NUMBER_OF_BOXES = 50;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Widely produced before the war");
        tooltip.add("Used by any 7mm rifle");
    }
}
