package com.redsparkle.foe.inventory;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.text.ITextComponent;

/**
 * Created by hoijima on 20.07.17.
 */
public class Adv_inv extends InventoryBasic {
    public Adv_inv(String title, boolean customName, int slotCount) {
        super(title, customName, slotCount);
    }

    public Adv_inv(ITextComponent title, int slotCount) {
        super(title, slotCount);
    }
}
