package com.redsparkle.foe.items.guns;

import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.foe.Init.GlobalsGunStats;
import net.minecraft.item.Item;

/**
 * Created by hoijima on 24.06.17.
 */
public class FlareGun extends Item_Firearm {
    public FlareGun(String itemName, GlobalsGunStats params, Item ClipItem, Item AmmoItem) {
        super(itemName, params, ClipItem, AmmoItem);
    }
}