package com.redsparkle.foe.gui.Inventory_Crafting.Slots;

import com.redsparkle.foe.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.foe.items.helpers.Item_Instances.Item_Utility;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


/**
 * Created by hoijima on 28.06.17.
 */
public class SlotDevice extends Slot {


    /** The index of the slot in the inventory. */
    private final int slotIndex;
    /** The inventory we want to extract a slot from. */
    public final IInventory inventory;
    /** the id of the slot(also the index in the inventory arraylist) */
    public int slotNumber;
    /** display position of the inventory slot on the screen x axis */
    public int xPos;
    /** display position of the inventory slot on the screen y axis */
    public int yPos;

    public SlotDevice(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn,index,xPosition,yPosition);
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
        return 1;
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof Item_Utility;
    }
}
