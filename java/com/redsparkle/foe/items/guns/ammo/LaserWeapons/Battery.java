package com.redsparkle.foe.items.guns.ammo.LaserWeapons;
import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class Battery extends Item_AmmoHolder {
    public Battery(String battery) {
        super(battery);
        final int NUMBER_OF_BOXES = 20;
        this.clipsize = GlobalsGunStats.LASER_PISTOL.Clipsize();
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(clipsize);
        this.clipInfo = "Ammo For laser weapons";
    }
}
