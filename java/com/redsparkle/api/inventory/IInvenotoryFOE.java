package com.redsparkle.api.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;


/**
 * Created by hoijima on 07.07.17.
 */
public interface IInvenotoryFOE extends IInventory {

    /**
     * Drop only the Foe items from the player's inventory.
     *
     * @param player
     */
    void dropExtendedItems(EntityPlayer player);

    /**
     * Make the implementing inventory a copy of the specified extended inventory.
     *
     * @param par1InventoryPlayer The inventory to copy
     */
    void copyInventory(IInvenotoryFOE par1InventoryPlayer);
}
