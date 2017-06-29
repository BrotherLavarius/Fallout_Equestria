package com.redsparkle.foe.gui.Inventory_Crafting;

import com.lothrazar.powerinventory.Const;
import com.lothrazar.powerinventory.ModInv;
import com.lothrazar.powerinventory.config.ModConfig;
import com.lothrazar.powerinventory.net.PacketSyncExtendedInventory;
import com.lothrazar.powerinventory.proxy.CommonProxy;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.Inventory.MessageSync_Adv_Inventory;
import com.redsparkle.foe.utils.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import java.lang.ref.WeakReference;

/**
 * Created by hoijima on 29.06.17.
 */
public class IInventory_AdditionalInventory implements IInventory {
    public static int INV_SIZE;
    public NonNullList<ItemStack> inventory;
    private final String tagName = "foe_tag";
    private final String tagSlot = "Slot";

    public ItemStack pipbuckStack = ItemStack.EMPTY;

    public ItemStack device_stack_one   = ItemStack.EMPTY;
    public ItemStack device_stack_two   = ItemStack.EMPTY;
    public ItemStack device_stack_tree  = ItemStack.EMPTY;
    public ItemStack device_stack_four  = ItemStack.EMPTY;

    public ItemStack harnes_stack       = ItemStack.EMPTY;

    public ItemStack gun_stack_one      = ItemStack.EMPTY;
    public ItemStack gun_stack_two      = ItemStack.EMPTY;

    public ItemStack ammo_stack_one     = ItemStack.EMPTY;
    public ItemStack ammo_stack_two     = ItemStack.EMPTY;
    public ItemStack ammo_stack_tree    = ItemStack.EMPTY;
    public ItemStack ammo_stack_four    = ItemStack.EMPTY;

    public ItemStack[] stacks = new ItemStack[]{
            device_stack_one,
            device_stack_two,
            device_stack_tree,
            device_stack_four,
            harnes_stack,
            gun_stack_one,
            gun_stack_two,
            ammo_stack_one,
            ammo_stack_two,
            ammo_stack_tree,
            ammo_stack_four
    };

    public WeakReference<EntityPlayer> player;

    public IInventory_AdditionalInventory(EntityPlayer player) {
        // always 2 hotbars. the number of sections depends on config (ignoring
        // locked or not per player)
        INV_SIZE = 12 * Constants.HOTBAR_SIZE + Constants.V_INVO_SIZE;
        //    inventory = new ItemStack[INV_SIZE];
        inventory = NonNullList.withSize(INV_SIZE, ItemStack.EMPTY);
        this.player = new WeakReference<EntityPlayer>(player);
    }
    @Override
    public int getSizeInventory() {
        return INV_SIZE;
    }
    @Override
    public ItemStack getStackInSlot(int slot) {
        for (int i = 0; i <= (Constants.slots.length - 1); i++) {
            if (slot == Constants.slots[i]) {
                return stacks[i];
            }
        }

        if (slot >= inventory.size()) { return ItemStack.EMPTY; }
        return inventory.get(slot);
    }

    public void dropStackInSlot(EntityPlayer p, int slot) {
        ItemStack itemstack = getStackInSlot(slot);
        if (!itemstack.isEmpty()) {
            p.dropItem(itemstack, false);
        }
        syncSlotToClients(slot);
    }
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack;
        if (index < 140 || index > 151)
        for (int i = 0; i <= (Constants.slots.length - 1); i++) {

            if (index == Constants.slots[i]) {
                if (this.stacks[i].getCount() <= count) {
                    itemstack = this.stacks[i];
                    this.stacks[i] = ItemStack.EMPTY;
                    syncSlotToClients(index);
                    return itemstack;
                } else {
                    itemstack = this.stacks[i].splitStack(count);
                    if (this.stacks[i].getCount() == 0) {
                        this.stacks[i] = ItemStack.EMPTY;
                    }
                    syncSlotToClients(index);
                    return itemstack;
                }
            }

        }

        else {
            int indexCopy = index;
            int countCopy = count;
            if (!this.getStackInSlot(indexCopy).isEmpty()) {
                if (this.getStackInSlot(indexCopy).getCount() <= countCopy) {
                    itemstack = this.getStackInSlot(indexCopy);
                    this.setInventorySlotContents(indexCopy, ItemStack.EMPTY);
                    syncSlotToClients(index);
                    return itemstack;
                }
                else {
                    itemstack = this.getStackInSlot(indexCopy).splitStack(countCopy);
                    if (this.getStackInSlot(indexCopy).getCount() == 0) {
                        //            this.inventory[indexCopy] =  ItemStack.EMPTY;
                        this.setInventorySlotContents(indexCopy, ItemStack.EMPTY);
                    }
                    syncSlotToClients(index);
                    return itemstack;
                }
            }
            else {
                return ItemStack.EMPTY;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot >= 140 && slot <=151) {
            for (int i = 0; i <= (Constants.slots.length - 1); i++) {
                if (Constants.slots[i] == slot){
                    stacks[i] = stack;
                }
            }

        }
        else {
            this.inventory.set(slot, stack);
        }
        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        syncSlotToClients(slot);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    @Override
    public void markDirty() {}
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return true;
    }

    public void writeToNBT(NBTTagCompound tags) {
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound tagcompound;
        for (int i = 0; i <= (Constants.slots.length - 1); i++) {
            if (this.stacks[i] == null) {
                this.stacks[i] = ItemStack.EMPTY;
            }
        }
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (!this.getStackInSlot(i).isEmpty()) {
                tagcompound = new NBTTagCompound();
                tagcompound.setInteger(tagSlot, i);
                this.getStackInSlot(i).writeToNBT(tagcompound);
                nbttaglist.appendTag(tagcompound);
            }
        }
        for (int i = 0; i <= (Constants.slots.length - 1); i++) {
            if (!this.stacks[i].isEmpty()) {
                tagcompound = new NBTTagCompound();
                tagcompound.setInteger(tagSlot, Constants.slots[i]);
                this.stacks[i].writeToNBT(tagcompound);
                nbttaglist.appendTag(tagcompound);
            }
        }
        tags.setTag(tagName, nbttaglist);
    }
    public void readFromNBT(NBTTagCompound tagcompound) {
        NBTTagList nbttaglist = tagcompound.getTagList(tagName, net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);
        ItemStack itemstack;
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound tags = nbttaglist.getCompoundTagAt(i);// tagAt
            int b = tags.getInteger(tagSlot);
            itemstack = new ItemStack(tags);// ItemStack.loadItemStackFromNBT(tags);
            if (b >= 0 && b < this.getSizeInventory()) {
                this.setInventorySlotContents(b, itemstack);
            }
            else if (!itemstack.isEmpty()) {
                for (int g = 0; g <= (Constants.slots.length - 1); g++) {
                    if (b == Constants.slots[i]) {
                        stacks[i] = itemstack;
                    }
                }
            }
        }
    }
    @Override
    public boolean hasCustomName() {
        return false;
    }
    @Override
    public void openInventory(EntityPlayer player) {}
    @Override
    public void closeInventory(EntityPlayer player) {}
    @Override
    public int getField(int id) {
        return 0;
    }
    @Override
    public void setField(int id, int value) {}
    @Override
    public int getFieldCount() {
        return 0;
    }
    @Override
    public void clear() {}
    @Override
    public String getName() {
        return null;
    }
    @Override
    public ItemStack removeStackFromSlot(int slot) {
        // was getStackInSlotOnClosing
        ItemStack stack = getStackInSlot(slot);
        if (stack.isEmpty()) {
            setInventorySlotContents(slot, ItemStack.EMPTY);
        }
        return stack;
    }
    @Override
    public ITextComponent getDisplayName() {
        // TODO Auto-generated method stub
        return null;
    }

    public void syncSlotToClients(int slot) {
        try {
            //TODO: make a SyncMessage
            if (main.proxy.getClientWorld() == null) {
                main.simpleNetworkWrapper.sendToAll(new MessageSync_Adv_Inventory(player.get(), slot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        // TODO Auto-generated method stub
        return true;
    }
}
