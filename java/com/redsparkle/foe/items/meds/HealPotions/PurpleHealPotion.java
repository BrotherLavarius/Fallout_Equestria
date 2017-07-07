package com.redsparkle.foe.items.meds.HealPotions;

import com.redsparkle.api.items.helpers.meds.meds_Potion;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;

/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class PurpleHealPotion extends meds_Potion {
    public int NUMBER_OF_BOXES = 10 ;
    public int MaxDamage =15;
    public float HealMoment =10;
    public String potionId = "regeneration";
    public int durationIn = 30;
    public int amplifierIn = 0;


    public PurpleHealPotion() {

        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_meds);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);
        this.potion=potionId;
        this.duration=durationIn;
        this.amplifier=amplifierIn;
        this.HealMoment=HealMomentln;
    }
}
