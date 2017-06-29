package com.redsparkle.foe.gui.Inventory_Crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by hoijima on 29.06.17.
 */
public class CONTAINER_AdditionalInventory extends Container {
    private final EntityPlayer thePlayer;
    public IInventory_AdditionalInventory inventory_ai;

    //TODO: FInish this class
    public CONTAINER_AdditionalInventory(EntityPlayer player, InventoryPlayer inventory) {
        thePlayer = player;
    }

    /**
     * Determines whether supplied player can use this container
     *
     * @param playerIn
     */
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
