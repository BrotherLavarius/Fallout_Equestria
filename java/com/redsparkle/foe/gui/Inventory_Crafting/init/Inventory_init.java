package com.redsparkle.foe.gui.Inventory_Crafting.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

/**
 * Created by hoijima on 28.06.17.
 */
public class Inventory_init implements IInventory {
    /**
     * Define the inventory size here for easy reference
     */
    // This is also the place to define which slot is which if you have different types,
    // for example SLOT_SHIELD = 0, SLOT_AMULET = 1;
    public static final int INV_SIZE = 13;
    /**
     * The name your custom inventory will display in the GUI, possibly just "Inventory"
     */
    private final String name = "Custom Inventory";
    /**
     * The key used to store and retrieve the inventory from NBT
     */
    private final String tagName = "CustomInvTag";
    /**
     * Inventory's size must be same as number of slots you add to the Container class
     */
    private ItemStack[] inventory = new ItemStack[INV_SIZE];

    public Inventory_init() {
        // don't need anything here!
    }


    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize > amount) {
                stack = stack.splitStack(amount);
                this.onInventoryChanged();
            } else {
                setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        return null;
    }


    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        this.inventory[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }


    /**
     * Our custom slots are similar to armor - only one item per slot
     */
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {

    }


    /**
     * This method doesn't seem to do what it claims to do, as
     * items can still be left-clicked and placed in the inventory
     * even when this returns false
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        // If you have different kinds of slots, then check them here:
        // if (slot == SLOT_SHIELD && itemstack.getItem() instanceof ItemShield) return true;

        // For now, only ItemUseMana items can be stored in these slots
        return itemstack.getItem() instanceof ItemUseMana;
    }

    @Override
    public int getField(int i) {
        return 0;
    }

    @Override
    public void setField(int i, int i1) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }


    @Override
    public String getName() {
        return null;
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
}
