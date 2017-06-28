package com.redsparkle.foe.gui.Inventory_Crafting;


import com.redsparkle.foe.gui.Inventory_Crafting.Slots.*;
import com.redsparkle.foe.gui.Inventory_Crafting.init.container_init;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by hoijima on 28.06.17.
 */
public class CONTAINER_AdditionalInventory extends container_init {
    //TileEntitySifter tileEntity;

    public CONTAINER_AdditionalInventory(InventoryPlayer inventory, EntityPlayer player) {
        super(13);

        IItemHandler inven = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        addSlotToContainer(new SlotPipBuck(inven, 140, 27, 4));

        addSlotToContainer(new SlotDevice(inven, 141, 6, 5));
        addSlotToContainer(new SlotDevice(inven, 142, 6, 24));
        addSlotToContainer(new SlotDevice(inven, 143, 6, 43));
        addSlotToContainer(new SlotDevice(inven, 144, 6, 62));

        addSlotToContainer(new SlotHarness(inven, 145, 123, 50));
        addSlotToContainer(new SlotGun(inven, 146, 92, 15));
        addSlotToContainer(new SlotGun(inven, 147, 152, 15));

        addSlotToContainer(new SlotAmmo(inven, 148, 113, 6));
        addSlotToContainer(new SlotAmmo(inven, 149, 132, 6));
        addSlotToContainer(new SlotAmmo(inven, 150, 113, 25));
        addSlotToContainer(new SlotAmmo(inven, 151, 132, 25));


        bindPlayerInventory(inventory);
    }
}