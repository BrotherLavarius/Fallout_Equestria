package com.redsparkle.foe.items.guns.inits;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by NENYN on 1/21/2017.
 */
public class ItemFirearm extends Item {

    public Item ammoItem;
    public int damage;
    public int clipRounds;

    public ItemFirearm() {
        this.clipRounds = 32;
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
    }
}
