package com.redsparkle.foe.gui.Inventory_Crafting;

import com.redsparkle.foe.gui.Inventory_Crafting.Slots.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 29.06.17.
 */
public class CONTAINER_AdditionalInventory extends Container {
    private final IInventory lowerChestInventory;
    private final int numRows;


    public IInventory_AdditionalInventory AdditionalInventory;
    //TODO: FInish this class
    public CONTAINER_AdditionalInventory(IInventory playerInventory, EntityPlayer player) {
        this.lowerChestInventory = playerInventory;
        //this.AdditionalInventory = new IInventory_AdditionalInventory(player);
        this.numRows = playerInventory.getSizeInventory() / 9;
        playerInventory.openInventory(player);


        this.addSlotToContainer(new SlotPipBuck(lowerChestInventory, 140, 27, 4));
        this.addSlotToContainer(new SlotDevice(lowerChestInventory, 141, 6, 5));
        this.addSlotToContainer(new SlotDevice(lowerChestInventory, 142, 6, 24));
        this.addSlotToContainer(new SlotDevice(lowerChestInventory, 143, 6, 43));
        this.addSlotToContainer(new SlotDevice(lowerChestInventory, 144, 6, 62));
        this.addSlotToContainer(new SlotHarness(lowerChestInventory, 145, 123, 50));
        this.addSlotToContainer(new SlotGun(lowerChestInventory, 146, 92, 15));
        this.addSlotToContainer(new SlotGun(lowerChestInventory, 147, 152, 15));

        this.addSlotToContainer(new SlotAmmo(lowerChestInventory, 148, 113, 6));
        this.addSlotToContainer(new SlotAmmo(lowerChestInventory, 149, 132, 6));
        this.addSlotToContainer(new SlotAmmo(lowerChestInventory, 150, 113, 25));
        this.addSlotToContainer(new SlotAmmo(lowerChestInventory, 151, 132, 25));

        // Add vanilla PLAYER INVENTORY - just copied/pasted from vanilla classes
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(lowerChestInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Add ACTION BAR - just copied/pasted from vanilla classes
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(lowerChestInventory, i, 8 + i * 18, 142));
        }

    }

    /**
     * Determines whether supplied player can use this container
     *
     * @param player
     */
    public boolean canInteractWith(EntityPlayer player) {
        return this.lowerChestInventory.isUsableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int SlotIndex) {
        ItemStack stack = ItemStack.EMPTY;
        Slot intVar1 = this.inventorySlots.get(SlotIndex);
        if (intVar1 != null && intVar1.getHasStack()) {
            ItemStack newStack = intVar1.getStack();
            stack = newStack.copy();

            {//managment for chest
                if (SlotIndex < this.numRows * 9) {
                    if (!this.mergeItemStack(newStack, this.numRows * 9, this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.mergeItemStack(newStack, 0, this.numRows * 9, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (SlotIndex >= 140 && SlotIndex <= 151) {
                if (!this.mergeItemStack(newStack, 140, 151, true)) {
                    return ItemStack.EMPTY;
                }
            }


            if (newStack.isEmpty()) {
                intVar1.putStack(ItemStack.EMPTY);
            } else {
                intVar1.onSlotChanged();
            }
        }

        return stack;
    }

    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        this.lowerChestInventory.closeInventory(player);
    }

    public IInventory getLowerChestInventory() {
        return this.lowerChestInventory;
    }
}
