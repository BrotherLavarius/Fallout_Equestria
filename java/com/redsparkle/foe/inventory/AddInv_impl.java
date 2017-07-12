package com.redsparkle.foe.inventory;

import com.redsparkle.api.capa.StatsCapa.AddInvCapabilityProvider;
import com.redsparkle.api.capa.StatsCapa.IAddInvCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by hoijima on 11.07.17.
 */
public class AddInv_impl implements IInventory {


    public static final int INV_SIZE = 12;
    private String customName = "container.Additional_Inventory";
    private IAddInvCapability stats;
    private AddInv_impl addInv_impl;
    private ItemStack[] stacks = new ItemStack[INV_SIZE];
    @Override
    public int getSizeInventory() {
        return 12;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory())
            return null;
        return this.stacks[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).getCount() <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).getCount() <= 0) {
                    this.setInventorySlotContents(index, null);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        this.stacks[index] = ItemStack.EMPTY;
        return stacks[index];

    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (stack != null && stack.getCount() == 0)
            stack = null;

        this.stacks[index] = stack;

        this.markDirty();
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
        return true;
    }


    // The next two methods openInventory and closeInventory are not needed in most cases so we leave them empty.
    @Override
    public void openInventory(EntityPlayer player) {
        try {
            this.stats = player.getCapability(AddInvCapabilityProvider.STATS_CAPA, null);
            this.addInv_impl = stats.getAdditional_Inventory();

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
            for (int i = 0; i < slots.length; i++) {
                this.addInv_impl.setInventorySlotContents(i, slots[i]);
            }
            addInv_impl.openInventory(player);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        this.stats = player.getCapability(AddInvCapabilityProvider.STATS_CAPA,null);
        this.addInv_impl = stats.getAdditional_Inventory();

        for(int i=0;i < 12;i++){
            if(i==0){stats.setPipBuckSlot(addInv_impl.getStackInSlot(i));}
            if(i==1){stats.setDeviceSlot1(addInv_impl.getStackInSlot(i));}
            if(i==2){stats.setDeviceSlot2(addInv_impl.getStackInSlot(i));}
            if(i==3){stats.setDeviceSlot3(addInv_impl.getStackInSlot(i));}
            if(i==4){stats.setDeviceSlot4(addInv_impl.getStackInSlot(i));}
            if(i==5){stats.setHarnessSlot(addInv_impl.getStackInSlot(i));}
            if(i==6){stats.setGunSlot1(addInv_impl.getStackInSlot(i));}
            if(i==7){stats.setGunSlot2(addInv_impl.getStackInSlot(i));}
            if(i==8){stats.setAmmoSlot1(addInv_impl.getStackInSlot(i));}
            if(i==9){stats.setAmmoSlot2(addInv_impl.getStackInSlot(i));}
            if(i==10){stats.setAmmoSlot2(addInv_impl.getStackInSlot(i));}
            if(i==11){stats.setAmmoSlot3(addInv_impl.getStackInSlot(i));}
        }
        addInv_impl.closeInventory(player);
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
            this.setInventorySlotContents(i, null);
    }


    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.some_thing_gone_wrong";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.equals("");
    }

    @Override
    public ITextComponent getDisplayName() {
        if (!this.hasCustomName()){
            return new TextComponentString(this.getName());
        }
        return new TextComponentString("something_gone_wrong");
    }
}
