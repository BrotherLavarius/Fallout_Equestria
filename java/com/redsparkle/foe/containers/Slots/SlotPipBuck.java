package com.redsparkle.foe.containers.Slots;

import com.redsparkle.api.items.helpers.Item_Instances.Item_pipbuck;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 28.06.17.
 */
public class SlotPipBuck extends Slot {

    public SlotPipBuck(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);

    }

    @Override
    public int getSlotStackLimit() {
        return 64;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return stack.getItem() instanceof Item_pipbuck;
    }

}
