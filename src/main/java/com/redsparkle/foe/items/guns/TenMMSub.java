package com.redsparkle.foe.items.guns;

import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.foe.Init.GlobalsGunStats;
import net.minecraft.item.Item;

/**
 * Created by NENYN on 1/5/2017.
 */
public class TenMMSub extends Item_Firearm {

    public TenMMSub(String itemName, GlobalsGunStats params, Item ClipItem, Item AmmoItem) {
        super(itemName, params, ClipItem, AmmoItem);
    }
}
