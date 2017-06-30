package com.redsparkle.foe.gui.Inventory_Crafting;

import com.redsparkle.foe.gui.Inventory_Crafting.Slots.*;
import com.redsparkle.foe.utils.UtilPlayerInventoryFilestorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by hoijima on 29.06.17.
 */
public class CONTAINER_AdditionalInventory extends Container {
    private final EntityPlayer thePlayer;
    public IInventory_AdditionalInventory inventory_ai;

    //TODO: FInish this class
    public CONTAINER_AdditionalInventory(EntityPlayer player, InventoryPlayer playerInventory) {
        thePlayer = player;
        inventory_ai = new IInventory_AdditionalInventory(player);
        if (!player.world.isRemote) {
            //TODO: write this method
            // UtilPlayerInventoryFilestorage.putDataIntoInventory(invo, player);
            //      inventory.stackList = UtilPlayerInventoryFilestorage.getPlayerInventory(player).stackList;
        }
        this.addSlotToContainer(new SlotPipBuck(inventory_ai, 140, 27, 4));
        this.addSlotToContainer(new SlotDevice(inventory_ai, 141, 6, 5));
        this.addSlotToContainer(new SlotDevice(inventory_ai, 142, 6, 24));
        this.addSlotToContainer(new SlotDevice(inventory_ai, 143, 6, 43));
        this.addSlotToContainer(new SlotDevice(inventory_ai, 144, 6, 62));
        this.addSlotToContainer(new SlotHarness(inventory_ai, 145, 123, 50));
        this.addSlotToContainer(new SlotGun(inventory_ai, 146, 92, 15));
        this.addSlotToContainer(new SlotGun(inventory_ai, 147, 152, 15));

        this.addSlotToContainer(new SlotAmmo(inventory_ai, 148, 113, 6));
        this.addSlotToContainer(new SlotAmmo(inventory_ai, 149, 132, 6));
        this.addSlotToContainer(new SlotAmmo(inventory_ai, 150, 113, 25));
        this.addSlotToContainer(new SlotAmmo(inventory_ai, 151, 132, 25));

        // Add vanilla PLAYER INVENTORY - just copied/pasted from vanilla classes
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Add ACTION BAR - just copied/pasted from vanilla classes
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

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

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        if (!thePlayer.world.isRemote) {
            UtilPlayerInventoryFilestorage.setPlayerInventory(thePlayer, inventory_ai);
        }
    }
}
