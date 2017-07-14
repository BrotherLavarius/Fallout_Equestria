package com.redsparkle.foe.containers;

import com.redsparkle.api.capa.StatsCapa.AddInvCapabilityProvider;
import com.redsparkle.api.capa.StatsCapa.IAddInvCapability;
import com.redsparkle.foe.containers.Slots.*;
import com.redsparkle.foe.inventory.AddInv_impl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 29.06.17.
 */
public class CONTAINER_AdditionalInventory extends Container {

    private final int numRows;
    public AddInv_impl additional_inventory;
    public InventoryPlayer inventoryPlayer;
    private IAddInvCapability stats;

    //TODO: FInish this class
    public CONTAINER_AdditionalInventory(EntityPlayer thePlayer) {
        inventoryPlayer = thePlayer.inventory;
        additional_inventory = new AddInv_impl();
        stats = thePlayer.getCapability(AddInvCapabilityProvider.STATS_CAPA, null);
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
         * Additional inventory 0-12 ........ 0  - 8
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

        onContainerOpen();
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

        for (int i = 0; i < 12; i++) {
            if (i == 0) {
                stats.setPipBuckSlot(additional_inventory.getStackInSlot(i));
            }
            if (i == 1) {
                stats.setDeviceSlot1(additional_inventory.getStackInSlot(i));
            }
            if (i == 2) {
                stats.setDeviceSlot2(additional_inventory.getStackInSlot(i));
            }
            if (i == 3) {
                stats.setDeviceSlot3(additional_inventory.getStackInSlot(i));
            }
            if (i == 4) {
                stats.setDeviceSlot4(additional_inventory.getStackInSlot(i));
            }
            if (i == 5) {
                stats.setHarnessSlot(additional_inventory.getStackInSlot(i));
            }
            if (i == 6) {
                stats.setGunSlot1(additional_inventory.getStackInSlot(i));
            }
            if (i == 7) {
                stats.setGunSlot2(additional_inventory.getStackInSlot(i));
            }
            if (i == 8) {
                stats.setAmmoSlot1(additional_inventory.getStackInSlot(i));
            }
            if (i == 9) {
                stats.setAmmoSlot2(additional_inventory.getStackInSlot(i));
            }
            if (i == 10) {
                stats.setAmmoSlot2(additional_inventory.getStackInSlot(i));
            }
            if (i == 11) {
                stats.setAmmoSlot3(additional_inventory.getStackInSlot(i));
            }
        }
    }

    public void onContainerOpen() {
        try {
            ItemStack[] slots = new ItemStack[]{
                    stats.getPipBuckSlot(),
                    stats.getDeviceSlot1(),
                    stats.getDeviceSlot2(),
                    stats.getDeviceSlot3(),
                    stats.getDeviceSlot4(),
                    stats.getHarnessSlot(),
                    stats.getGunSlot1(),
                    stats.getGunSlot2(),
                    stats.getAmmoSlot1(),
                    stats.getAmmoSlot2(),
                    stats.getAmmoSlot3(),
                    stats.getAmmoSlot4()
            };
            for (int i = 0; i < 12; i++) {
                i = i;
                ItemStack item = slots[i];
                if (item == null) {
                    System.out.println("SLOT: " + i + " got null");
                    item = ItemStack.EMPTY;
                }//TODO: this returns null for some fucking reason
                additional_inventory.setInventorySlotContents(i, item);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
