package com.redsparkle.api.utils;

import com.redsparkle.foe.Init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 19.07.17.
 */
public class ItemCatalog {
    public static Item Request(String name) {
        if (name.equalsIgnoreCase("item.aem")) {
            return ModItems.AEM.getITEM();
        } else if (name.equalsIgnoreCase("item.pipbuck")) {
            return ModItems.PIPBUCK.getITEM();
        } else {
            return Items.AIR;
        }
    }

    public static ItemStack RequestStack(Item item, int amount, int damage) {
        return new ItemStack(item, amount, damage);
    }
}
