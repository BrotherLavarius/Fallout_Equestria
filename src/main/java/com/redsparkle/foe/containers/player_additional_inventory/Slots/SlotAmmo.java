package com.redsparkle.foe.containers.player_additional_inventory.Slots;

import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 28.06.17.
 */
public class SlotAmmo extends Slot {
    /**
     * The inventory we want to extract a slot from.
     */
    public final IInventory inventory;
    /**
     * The index of the slot in the inventory.
     */
    private final int slotIndex;
    /**
     * the id of the slot(also the index in the inventory arraylist)
     */
    public int slotNumber;
    /**
     * display position of the inventory slot on the screen x axis
     */
    public int xPos;
    /**
     * display position of the inventory slot on the screen y axis
     */
    public int yPos;

    public SlotAmmo(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.inventory = inventoryIn;
        this.slotIndex = index;
        this.xPos = xPosition;
        this.yPos = yPosition;
    }

    @Override
    public int getSlotIndex() {
        return slotIndex;
    }

    @Override
    public int getSlotStackLimit() {
        return 65;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return super.isItemValid(stack) && stack.getItem() instanceof Item_AmmoHolder;
    }
}
