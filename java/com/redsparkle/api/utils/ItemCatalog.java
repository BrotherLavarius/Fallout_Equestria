package com.redsparkle.api.utils;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 19.07.17.
 */
public class ItemCatalog {

    public static Item Request(String name){
            if(name.equalsIgnoreCase("item.aem")){
                return GlobalItemArray_For_init.AllInit[2];
            }else if(name.equalsIgnoreCase("item.pipbuck")){
                return GlobalItemArray_For_init.AllInit[0];
            }else{return Items.AIR;}
    }
    public static ItemStack RequestStack(Item item,int amount,int damage){
        return new ItemStack(item,amount,damage);

    }
}
