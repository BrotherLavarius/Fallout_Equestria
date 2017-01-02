package com.redsparkle.foe.items.guns.Items;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;

import net.minecraft.item.*;

import javax.annotation.Nullable;

/**
 * Created by NENYN on 1/2/2017.
 */
public class TenMMPistol extends Item {
    public TenMMPistol()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(10);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
    }


}
