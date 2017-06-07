package com.redsparkle.foe.gui.Inventory_Crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by hoijima on 07.06.17.
 */
public class Saddlebags extends Container {
    public InventoryPlayer inventoryPlayer;
    //public InventoryExtended extendedInventory;

    public Saddlebags(EntityPlayer player) {


    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return false;
    }
}
