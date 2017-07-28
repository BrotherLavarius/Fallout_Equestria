package com.redsparkle.foe.items.guns.ammo.FlareShell;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.FoeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class FlareShell extends FoeItem {
    public int MaxDamage = 1;
    public FlareShell(String flareShell) {
        super(flareShell);
        final int NUMBER_OF_BOXES = 40;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);
    }
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Ammo For Flare Gun");
        tooltip.add("Contains a SHINY EXPOSIIIIION!");
    }
}
