package com.redsparkle.foe.items.saddlebags;

import com.redsparkle.api.items.helpers.Item_Instances.FoeItem;
import com.redsparkle.foe.Init.InitCreativeTabs;

/**
 * Created by hoijima on 24.08.17.
 */
public class Saddlebags extends FoeItem {

    public Saddlebags(String itemName) {
        super(itemName);
        this.setMaxStackSize(1);

        this.setCreativeTab(InitCreativeTabs.Fallout_armor);
    }
}
