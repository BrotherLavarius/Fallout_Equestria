package com.redsparkle.foe.gui.Inventory_Crafting;

import com.redsparkle.foe.items.saddlebags_init.Init_harness;
import com.redsparkle.foe.items.saddlebags_init.Item_SaddleBagAmmo;
import com.redsparkle.foe.items.saddlebags_init.Item_SaggleBagGun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

/**
 * Created by hoijima on 07.06.17.
 */
public class Saddlebags_Inv implements IInventory {

    /** The name your custom inventory will display in the GUI, possibly just "Inventory" */
    private final String name = "SaddleBags";

    /** The key used to store and retrieve the inventory from NBT */
    private final String tagName = "SaddleBags";

    /** Define the inventory size here for easy reference */
    // This is also the place to define which slot is which if you have different types,
    // for example SLOT_SHIELD = 0, SLOT_AMULET = 1;
    public static final int INV_SIZE = 9;
    private ItemStack[] inventory = new ItemStack[INV_SIZE];

    public Saddlebags_Inv()
    {
        // don't need anything here!
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the stack in the given slot.
     *
     * @param index
     */
    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory[index];
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     *
     * @param slot
     * @param amount
     */
    @Override
    public ItemStack decrStackSize(int slot, int amount){
    ItemStack stack = getStackInSlot(slot);
        if (stack != null)
        {
            if (stack.getCount() > amount)
            {
                stack = stack.splitStack(amount);
                this.onInventoryChanged();
            }
            else
            {
                setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    /**
     * Removes a stack from the given slot and returns it.
     *
     * @param index
     */
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     * @param slot
     * @param itemstack
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        this.inventory[slot] = itemstack;

        if (itemstack != null && itemstack.getCount() > this.getInventoryStackLimit())
        {
            itemstack.setCount(this.getInventoryStackLimit());
        }

        this.onInventoryChanged();
    }


    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    @Override
    public int getInventoryStackLimit() {
        return 30;
    }

    /**
     * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
     * hasn't changed and skip it.
     */
    @Override
    public void markDirty() {

    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     *
     * @param player
     */
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     *
     * @param index
     * @param stack
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(getStackInSlot(index).getItem() instanceof Init_harness || getStackInSlot(index).getItem() instanceof Item_SaddleBagAmmo ||getStackInSlot(index).getItem() instanceof Item_SaggleBagGun){
            return true;
        }else {return false;}
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

    }

    /**
     * Get the name of this object. For players this returns their username
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns true if this thing is named
     */
    @Override
    public boolean hasCustomName() {
        return false;
    }

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    public void onInventoryChanged()
    {
        for (int i = 0; i < getSizeInventory(); ++i)
        {
            if (getStackInSlot(i) != null && getStackInSlot(i).getCount() == 0) {
                inventory[i] = null;
            }
        }
    }
}
