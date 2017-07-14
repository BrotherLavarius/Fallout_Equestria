package com.redsparkle.foe.containers;

import com.redsparkle.api.capa.StatsCapa.AddInvCapabilityProvider;
import com.redsparkle.api.capa.StatsCapa.IAddInvCapability;
import com.redsparkle.foe.containers.Slots.*;
import com.redsparkle.foe.inventory.AddInv_impl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 29.06.17.
 */
public class CONTAINER_AdditionalInventory extends Container {

    private final int numRows;
    public AddInv_impl additional_inventory;
    public InventoryPlayer inventoryPlayer;
    public IAddInvCapability stats;

    //TODO: FInish this class
    public CONTAINER_AdditionalInventory(EntityPlayer thePlayer) {
        inventoryPlayer = thePlayer.inventory;
        additional_inventory = new AddInv_impl();
        stats = thePlayer.getCapability(AddInvCapabilityProvider.STATS_CAPA, null);
        additional_inventory.openInventory(thePlayer);
        numRows = inventoryPlayer.getSizeInventory() / 9;

        int i = (numRows - 4) * 18;

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j1 + l * 9 + 9, 8 + j1 * 18, 84 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i1, 8 + i1 * 18, 142 + i));
        }



        /*
         * SLOTS:
         *
         * Additional inventory 0-11 ........ 0  - 8
         * Player Inventory     9-35 .. 9  - 35
         * Player Hotbar        0-8 ... 36 - 44
         */

            this.addSlotToContainer(new SlotPipBuck(additional_inventory,0, 27, 4));
            this.addSlotToContainer(new SlotDevice(additional_inventory, 1, 6, 5));
            this.addSlotToContainer(new SlotDevice(additional_inventory, 2, 6, 24));
            this.addSlotToContainer(new SlotDevice(additional_inventory, 3, 6, 43));
            this.addSlotToContainer(new SlotDevice(additional_inventory, 4, 6, 62));
            this.addSlotToContainer(new SlotHarness(additional_inventory, 5, 123, 50));
            this.addSlotToContainer(new SlotGun(additional_inventory, 6, 152, 15));
            this.addSlotToContainer(new SlotGun(additional_inventory, 7, 103, 15));
            this.addSlotToContainer(new SlotAmmo(additional_inventory, 8, 113, 6));
            this.addSlotToContainer(new SlotAmmo(additional_inventory, 9, 132, 6));
            this.addSlotToContainer(new SlotAmmo(additional_inventory, 10, 113, 25));
            this.addSlotToContainer(new SlotAmmo(additional_inventory, 11, 132, 25));

    }

    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1) {
        ItemStack var2 = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(par1);

        if (slot != null && slot.getHasStack()) {
            final ItemStack stack = slot.getStack();
            var2 = stack.copy();

            if (par1 >= 36) {
                if (!this.mergeItemStack(stack, 0, 36, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                boolean flag = false;
                for (int j = 0; j < 13; j++) {
                    if ((this.inventorySlots.get(j)).isItemValid(stack)) {
                        if (!this.mergeOneItem(stack, j, j + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                        flag = true;
                        break;
                    }
                }


                if (!flag) {
                    if (par1 < 27) {
                        if (!this.mergeItemStack(stack, 27, 36, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.mergeItemStack(stack, 0, 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == var2.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(par1EntityPlayer, stack);
        }

        return var2;
    }


    protected boolean mergeOneItem(ItemStack par1ItemStack, int par2, int par3, boolean par4) {
        boolean flag1 = false;
        if (par1ItemStack.getCount() > 0) {
            Slot slot;
            ItemStack slotStack;

            for (int k = par2; k < par3; k++) {
                slot = this.inventorySlots.get(k);
                slotStack = slot.getStack();

                if (slotStack.isEmpty()) {
                    ItemStack stackOneItem = par1ItemStack.copy();
                    stackOneItem.setCount(1);
                    par1ItemStack.shrink(1);
                    slot.putStack(stackOneItem);
                    slot.onSlotChanged();
                    flag1 = true;
                    break;
                }
            }
        }

        return flag1;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        additional_inventory.closeInventory(playerIn);
    }

}
