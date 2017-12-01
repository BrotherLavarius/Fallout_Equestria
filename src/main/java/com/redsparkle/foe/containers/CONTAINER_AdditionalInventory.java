package com.redsparkle.foe.containers;

import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.foe.containers.player_additional_inventory.Adv_inv;
import com.redsparkle.foe.containers.player_additional_inventory.Slots.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

/**
 * Created by hoijima on 29.06.17.
 */
public class CONTAINER_AdditionalInventory extends Container {
    private final static int HOTBAR_SLOT_COUNT = 9;
    private final static int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final static int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final static int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final static int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private final static int VANILLA_FIRST_SLOT_INDEX = 0;
    private final static int ADV_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private final static int ADV_INVENTORY_SLOT_COUNT = 11;
    private final int numRows;
    public InventoryBasic additional_inventory;
    public InventoryPlayer inventoryPlayer;
    public IAdvInventory adv_inv;
    public EntityPlayer player;

    //TODO: FInish this class
    public CONTAINER_AdditionalInventory(EntityPlayer player) {
        this.adv_inv = player.getCapability(IAdvProvider.Adv_Inv, null);
        this.inventoryPlayer = player.inventory;
        this.player = player;
        this.additional_inventory = new Adv_inv("FOE additional inventory", false, 12);
        additional_inventory.openInventory(player);
        numRows = inventoryPlayer.getSizeInventory() / 9;
        this.addSlotToContainer(new SlotPipBuck(additional_inventory, 0, 27, 4));
        this.addSlotToContainer(new SlotDevice(additional_inventory, 1, 6, 5));
        this.addSlotToContainer(new SlotDevice(additional_inventory, 2, 6, 24));
        this.addSlotToContainer(new SlotDevice(additional_inventory, 3, 6, 43));
        this.addSlotToContainer(new SlotDevice(additional_inventory, 4, 6, 62));
        this.addSlotToContainer(new SlotHarness(additional_inventory, 5, 123, 50));
        this.addSlotToContainer(new SlotGun(additional_inventory, 6, 92, 15));
        this.addSlotToContainer(new SlotGun(additional_inventory, 7, 152, 15));
        this.addSlotToContainer(new SlotAmmo(additional_inventory, 8, 113, 6));
        this.addSlotToContainer(new SlotAmmo(additional_inventory, 9, 132, 6));
        this.addSlotToContainer(new SlotAmmo(additional_inventory, 10, 113, 25));
        this.addSlotToContainer(new SlotAmmo(additional_inventory, 11, 132, 25));
        int i = (numRows - 4) * 18;
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j1 + l * 9 + 9, 8 + j1 * 18, 84 + l * 18 + i));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i1, 8 + i1 * 18, 142 + i));
        }
        /*
         * SLOTS:
         *
         * Additional inventory 0-11 ........ 0  - 8
         * Player Inventory     9-35 .. 9  - 35
         * Player Hotbar        0-8 ... 36 - 44
         */
        for (int g = 0; g < 12; g++) {
            this.additional_inventory.setInventorySlotContents(g, adv_inv.getStackInSlot(g));
        }

    }

    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
        Slot sourceSlot = inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();
        // Check if the slot clicked is one of the vanilla container slots
        if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX && sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!mergeItemStack(sourceStack, ADV_INVENTORY_FIRST_SLOT_INDEX, ADV_INVENTORY_FIRST_SLOT_INDEX + ADV_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (sourceSlotIndex >= ADV_INVENTORY_FIRST_SLOT_INDEX && sourceSlotIndex < ADV_INVENTORY_FIRST_SLOT_INDEX + ADV_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;   // EMPTY_ITEM
            }
        } else {
            System.err.print("Invalid slotIndex:" + sourceSlotIndex);
            return ItemStack.EMPTY;   // EMPTY_ITEM
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {  // getStackSize
            sourceSlot.putStack(ItemStack.EMPTY);  // EMPTY_ITEM
        } else {
            sourceSlot.onSlotChanged();
        }
        sourceSlot.onTake(player, sourceStack);  //onPickupFromSlot()
        return copyOfSourceStack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        for (int g = 0; g < 12; g++) {
            adv_inv.insertItem(g, additional_inventory.getStackInSlot(g), false);
            if (adv_inv.getStackInSlot(g) == ItemStack.EMPTY && additional_inventory.getStackInSlot(g) == ItemStack.EMPTY) {
            }
            if (adv_inv.getStackInSlot(g) != ItemStack.EMPTY && additional_inventory.getStackInSlot(g) == ItemStack.EMPTY) {
                adv_inv.extractItem(g, adv_inv.getStackInSlot(g).getCount(), false);
            }
            if (adv_inv.getStackInSlot(g) != ItemStack.EMPTY && additional_inventory.getStackInSlot(g) != ItemStack.EMPTY) {
                adv_inv.extractItem(g, adv_inv.getStackInSlot(g).getCount(), false);
                adv_inv.insertItem(g, additional_inventory.getStackInSlot(g), false);
            }
        }
        this.additional_inventory.closeInventory(playerIn);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges()
    {
        for (int i = 0; i < additional_inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = additional_inventory.getStackInSlot(i);
            ItemStack itemstack1 = additional_inventory.getStackInSlot(i);

            if (!ItemStack.areItemStacksEqual(itemstack1, itemstack))
            {
                boolean clientStackChanged = !ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack1, itemstack);
                itemstack1 = itemstack.isEmpty() ? ItemStack.EMPTY : itemstack.copy();
                additional_inventory.setInventorySlotContents(i, itemstack1);

                if (clientStackChanged)
                    for (int j = 0; j < this.listeners.size(); ++j)
                    {
                        this.listeners.get(j).sendSlotContents(this, i, itemstack1);
                    }


                for (int g = 0; g < ((WorldServer) this.player.world).getEntityTracker().getTrackingPlayers(this.player).size(); g++) {
                    ((WorldServer) this.player.world).getEntityTracker().getTrackingPlayers(this.player).iterator().next().getCapability(IAdvProvider.Adv_Inv, null);

                }
//                ((WorldServer) this.player.world).getEntityTracker().getTrackingPlayers((Entity) this.player).size();


            }
        }
    }

}
