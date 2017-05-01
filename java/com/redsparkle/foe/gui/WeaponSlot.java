package com.redsparkle.foe.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by hoijima on 22.02.17.
 */
public class WeaponSlot extends Slot {
    int x = 1;
    int y = 2;
    int z = 3;

    public WeaponSlot(IInventory p_i1824_1_, int x, int y, int z) {
        super(p_i1824_1_, x, y, z);
    }
}
