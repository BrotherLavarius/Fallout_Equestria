package com.redsparkle.foe.items.guns.inits;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.inits.bulletFiredGuns.EntityBullet;
import net.minecraft.item.Item;

/**
 * Created by NENYN on 1/21/2017.
 */
public class ItemFirearm extends Item {

    public Item ammoItem;
    public int damage;
    public int clipRounds;
    public Class<? extends EntityBullet> bulletClass;

    public ItemFirearm()
    {
        this.clipRounds = 32;
        this.bulletClass = EntityBullet.class;
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
    }
}
