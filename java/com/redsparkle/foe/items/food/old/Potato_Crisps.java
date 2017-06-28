package com.redsparkle.foe.items.food.old;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.helpers.food.FoodMultipleUse;

/**
 * Created by hoijima on 09.06.17.
 */
public class Potato_Crisps extends FoodMultipleUse{

    public int foodLvl = 0;

    public int NUMBER_OF_BOXES = 3;
    public int MaxDamage = 4;
    public int foodToAdd = 1;

    public Potato_Crisps(){
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_Food);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);
        this.foodLvl = foodToAdd;

    }
}
