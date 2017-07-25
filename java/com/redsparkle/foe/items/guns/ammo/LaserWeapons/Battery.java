package com.redsparkle.foe.items.guns.ammo.LaserWeapons;

import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class Battery extends Item_AmmoHolder {

    public int MaxDamage = 29;

    public Battery() {
        final int NUMBER_OF_BOXES = 10;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);


    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Ammo For laser weapons");
        tooltip.add("Contains a crystal that works like a capacitor!");
    }

}
