package com.redsparkle.foe.gui.Inventory_Crafting;


import com.redsparkle.foe.gui.Inventory_Crafting.Slots.*;
import com.redsparkle.foe.gui.Inventory_Crafting.init.Inventory_init;
import com.redsparkle.foe.gui.Inventory_Crafting.init.container_init;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by hoijima on 28.06.17.
 */
public class CONTAINER_AdditionalInventory extends Container {
    /**
     * Avoid magic numbers! This will greatly reduce the chance of you making errors in 'transferStackInSlot' method
     */
    private static final int ARMOR_START = Inventory_init.INV_SIZE, ARMOR_END = ARMOR_START + 3,
            INV_START = ARMOR_END + 1, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
            HOTBAR_END = HOTBAR_START + 8;

    public CONTAINER_AdditionalInventory(EntityPlayer player, InventoryPlayer inventoryPlayer, Inventory_init inventory_init) {
        int i;

        // Add CUSTOM slots - we'll just add two for now, both of the same type.
        // Make a new Slot class for each different item type you want to add

        this.addSlotToContainer(new SlotPipBuck(inventory_init, 0, 27, 4));
        this.addSlotToContainer(new SlotDevice(inventory_init, 1, 6, 5));
        this.addSlotToContainer(new SlotDevice(inventory_init, 2, 6, 24));
        this.addSlotToContainer(new SlotDevice(inventory_init, 3, 6, 43));
        this.addSlotToContainer(new SlotDevice(inventory_init, 4, 6, 62));
        this.addSlotToContainer(new SlotHarness(inventory_init, 5, 123, 50));
        this.addSlotToContainer(new SlotGun(inventory_init, 6, 92, 15));
        this.addSlotToContainer(new SlotGun(inventory_init, 7, 152, 15));

        this.addSlotToContainer(new SlotAmmo(inventory_init, 8, 113, 6));
        this.addSlotToContainer(new SlotAmmo(inventory_init, 9, 132, 6));
        this.addSlotToContainer(new SlotAmmo(inventory_init, 10, 113, 25));
        this.addSlotToContainer(new SlotAmmo(inventory_init, 11, 132, 25));


        // Add ARMOR slots; note you need to make a public version of SlotArmor
        // just copy and paste the vanilla code into a new class and change what you need


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

    /**
     * Determines whether supplied player can use this container
     *
     * @param playerIn
     */
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return false;
    }
}