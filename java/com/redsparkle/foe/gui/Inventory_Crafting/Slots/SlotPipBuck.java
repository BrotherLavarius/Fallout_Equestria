package com.redsparkle.foe.gui.Inventory_Crafting.Slots;

import com.redsparkle.foe.items.helpers.Item_Instances.Item_pipbuck;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by hoijima on 28.06.17.
 */
public class SlotPipBuck extends SlotItemHandler {
    public SlotPipBuck(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof Item_pipbuck;
    }

}
