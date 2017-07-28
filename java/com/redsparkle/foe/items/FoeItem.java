package com.redsparkle.foe.items;

import com.redsparkle.foe.main;
import net.minecraft.item.Item;

/**
 * Created by hoijima on 28.07.17.
 */
public class FoeItem extends Item {
    public FoeItem(final String itemName) {
        setItemName(this, itemName);
    }



    /**
     * Set the registry name of {@code item} to {@code itemName} and the unlocalised name to the full registry name.
     *
     * @param item     The item
     * @param itemName The item's name
     */
    public static void setItemName(final Item item, final String itemName) {
        item.setRegistryName(main.MODID, itemName);
        item.setUnlocalizedName(item.getRegistryName().toString());
    }
}
