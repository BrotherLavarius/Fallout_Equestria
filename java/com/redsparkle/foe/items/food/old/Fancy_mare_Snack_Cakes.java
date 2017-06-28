package com.redsparkle.foe.items.food.old;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.helpers.food.FoodMultipleUse;

/**
 * Created by hoijima on 09.06.17.
 */
public class Fancy_mare_Snack_Cakes extends FoodMultipleUse {

    public int foodLvl = 0;

    public int NUMBER_OF_BOXES = 2;
    public int MaxDamage = 3;
    public int foodToAdd = 4;

    public Fancy_mare_Snack_Cakes() {
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_Food);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);
        this.foodLvl = foodToAdd;

    }
}
