package com.redsparkle.foe.items.food.old;

import com.redsparkle.api.items.helpers.food.FoodMultipleUse;
import com.redsparkle.foe.Init.InitCreativeTabs;

/**
 * Created by hoijima on 09.06.17.
 */
public class Sugar_Bombs extends FoodMultipleUse {
    public int foodLvl = 0;
    public int NUMBER_OF_BOXES = 2;
    public int MaxDamage = 2;
    public int foodToAdd = 2;

    public Sugar_Bombs(String sugar_bombs) {
        super(sugar_bombs);
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_Food);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);
        this.foodLvl = foodToAdd;
    }
}
