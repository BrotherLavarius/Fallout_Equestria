package com.redsparkle.foe.gui.Inventory_Crafting.init;

/*
IMPORTANT: The following tutorial was created for Minecraft 1.6.4 - notes on updating to newer versions can be found at the bottom of the page, but you should follow the main tutorial first.

Have you ever wished you had a few extra inventory slots, say for a shield or amulet? You've come to the right place.

In this tutorial I will show you how to add custom slots to the player's inventory and effectively override the default
inventory screen.

A word of warning: there are lots of different aspects involved in this process, so if you've just started modding, I
HIGHLY suggest you come back later after becoming comfortable with ALL of the things listed as prerequisites. You will
thank yourself for it.

Prerequisites:

1 - You should already have your Main Mod, CommonProxy and ClientProxy set up. If you don't know about that, check

out TechGuy543's excellent tutorial: http://www.minecraftforum.net/topic/960286-techguys-modding-tutorials/

Alternatively, check the beginning of my "InventoryItemTutorial" for a basic setup.

2 - Know how to use IExtendedEntityProperties; best if you already have one set up. If not, see my tutorial:

http://www.minecraftforum.net/topic/1952901-eventhandler-and-iextendedentityproperties/#entry24051513

3 - Know how to use KeyBindings; best if you already have one set up:

http://www.minecraftforum.net/topic/1798625-162sobiohazardouss-forge-keybinding-tutorial/

4 - Know how to use Packets; best if you've already set up a PacketHandler:

http://www.minecraftforge.net/wiki/Packet_Handling

5 - Familiarity with IInventory and Containers is helpful, but not essential.

As always, I try to provide as much information as I can so that if you haven't met all the prerequisites you should still be
able to follow along; however, if you are having trouble getting it to work, please make sure you understand and have
followed the tutorials above before asking for help.

NOTE: Throughout this tutorial, I am using ItemUseMana as the custom Item - this is an Item I made in my
IExtendedEntityProperties tutorial, so if you want to follow along exactly, go grab the code for it; otherwise, just
substitute your custom Item(s) for it wherever it appears.

Let's get started.
*/

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Step 1: Create a custom IInventory class
 */
/*
This is the class that will handle all the custom ItemStacks saved in your custom slots. If you've ever worked with
IInventory before, you probably won't see anything new here. It's a very basic setup.
*/
public class InventoryCustomPlayer implements IInventory {
    /**
     * Define the inventory size here for easy reference
     */
    // This is also the place to define which slot is which if you have different types,
    // for example SLOT_SHIELD = 0, SLOT_AMULET = 1;
    public static final int INV_SIZE = 2;
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

    public InventoryCustomPlayer() {
        // don't need anything here!
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
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
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return stack;
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

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagList items = new NBTTagList();

        for (int i = 0; i < getSizeInventory(); ++i) {
            if (getStackInSlot(i) != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(item);
                items.appendTag(item);
            }
        }

        // We're storing our items in a custom tag list using our 'tagName' from above
        // to prevent potential conflicts
        compound.setTag(tagName, items);
    }

    public void readFromNBT(NBTTagCompound compound) {
        NBTTagList items = compound.getTagList(tagName);

        for (int i = 0; i < items.tagCount(); ++i) {
            NBTTagCompound item = (NBTTagCompound) items.tagAt(i);
            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot < getSizeInventory()) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }
}

/**
 * Step 2.1: Create a Container class for your custom IInventory
 */
/*
Again, if you've ever worked with Containers before, this will be very familiar. All we're doing here is adding our
custom slots to the container along with all the slots you'd see in the normal player inventory screen.

One thing to note is you will need to create a custom Slot class for each type of Item you want to add, so if you are adding
a Shield and an Amulet slot, you'll want SlotShield and SlotAmulet classes to match.

Another thing worthy of note is you need to create a public version of the SlotArmor class in order add armor slots, as the
vanilla class is not public by default. To avoid editing base classes, I simply copied the code into a new class stored in
the same package as my other inventory classes.
*/
public class ContainerCustomPlayer extends Container {
    /**
     * Avoid magic numbers! This will greatly reduce the chance of you making errors in 'transferStackInSlot' method
     */
    private static final int ARMOR_START = InventoryCustomPlayer.INV_SIZE, ARMOR_END = ARMOR_START + 3,
            INV_START = ARMOR_END + 1, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
            HOTBAR_END = HOTBAR_START + 8;

    public ContainerCustomPlayer(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryCustomPlayer inventoryCustom) {
        int i;

        // Add CUSTOM slots - we'll just add two for now, both of the same type.
        // Make a new Slot class for each different item type you want to add
        this.addSlotToContainer(new SlotCustom(inventoryCustom, 0, 80, 8));
        this.addSlotToContainer(new SlotCustom(inventoryCustom, 1, 80, 26));

        // Add ARMOR slots; note you need to make a public version of SlotArmor
        // just copy and paste the vanilla code into a new class and change what you need
        for (i = 0; i < 4; ++i) {
            this.addSlotToContainer(new SlotArmor(player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18,

                    i));
        }

        // Add vanilla PLAYER INVENTORY - just copied/pasted from vanilla classes
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Add ACTION BAR - just copied/pasted from vanilla classes
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

}

