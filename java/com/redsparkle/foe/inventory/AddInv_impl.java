package com.redsparkle.foe.inventory;

import com.redsparkle.api.capa.StatsCapa.AddInvCapabilityProvider;
import com.redsparkle.api.capa.StatsCapa.IAddInvCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

/**
 * Created by hoijima on 11.07.17.
 */
public class AddInv_impl implements IInventory {

    public int INV_SIZE = 11;
    public ItemStack[] inventory = new ItemStack[INV_SIZE];
    public String customName;
    public IAddInvCapability stats;
    @Override
    public int getSizeInventory() {
        return INV_SIZE;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory())
        {return ItemStack.EMPTY;}
        return this.inventory[index];
    }


    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).getCount() <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, ItemStack.EMPTY);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).getCount() <= 0) {
                    this.setInventorySlotContents(index, ItemStack.EMPTY);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (stack == null) {
            stack = ItemStack.EMPTY;
        }

        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (stack != null && stack.getCount() == 0)
            stack = ItemStack.EMPTY;

        inventory[index] = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {
        this.stats = player.getCapability(AddInvCapabilityProvider.STATS_CAPA, null);

        for (int g = 0; g <= this.getSizeInventory(); g++) {
            this.setInventorySlotContents(g, ItemStack.EMPTY);
        }
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
                this.setInventorySlotContents(i, item);
            }//TODO: this returns null for some fucking reason

        }


    }


    @Override
    public void closeInventory(EntityPlayer player) {
        for (int i = 0; i < 12; i++) {
            if (i == 0) {
                stats.setPipBuckSlot(this.getStackInSlot(i));
            }
            if (i == 1) {
                stats.setDeviceSlot1(this.getStackInSlot(i));
            }
            if (i == 2) {
                stats.setDeviceSlot2(this.getStackInSlot(i));
            }
            if (i == 3) {
                stats.setDeviceSlot3(this.getStackInSlot(i));
            }
            if (i == 4) {
                stats.setDeviceSlot4(this.getStackInSlot(i));
            }
            if (i == 5) {
                stats.setHarnessSlot(this.getStackInSlot(i));
            }
            if (i == 6) {
                stats.setGunSlot1(this.getStackInSlot(i));
            }
            if (i == 7) {
                stats.setGunSlot2(this.getStackInSlot(i));
            }
            if (i == 8) {
                stats.setAmmoSlot1(this.getStackInSlot(i));
            }
            if (i == 9) {
                stats.setAmmoSlot2(this.getStackInSlot(i));
            }
            if (i == 10) {
                stats.setAmmoSlot2(this.getStackInSlot(i));
            }
            if (i == 11) {
                stats.setAmmoSlot3(this.getStackInSlot(i));
            }
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.getSizeInventory(); i++)
            this.setInventorySlotContents(i, ItemStack.EMPTY);
    }

    @Override
    public String getName() {
        return this.customName;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }
}
