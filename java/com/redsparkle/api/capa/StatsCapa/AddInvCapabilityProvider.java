package com.redsparkle.api.capa.StatsCapa;

import com.redsparkle.foe.inventory.AddInv_impl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by hoijima on 07.07.17.
 */
public class AddInvCapabilityProvider implements IAddInvCapability, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(IAddInvCapability.class)
    public static Capability<IAddInvCapability> STATS_CAPA = null;
    public AddInv_impl additional_inventory = new AddInv_impl();
    public ItemStack DeviceSlot1,
            DeviceSlot2,
            DeviceSlot3,
            DeviceSlot4,
            PipBuckSlot,
            AmmoSlot1,
            AmmoSlot2,
            AmmoSlot3,
            AmmoSlot4,
            GunSlot1,
            GunSlot2,
            HarnessSlot;

    private boolean dirty = true;

    public AddInvCapabilityProvider() {
        this(
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY
);
    }

    public AddInvCapabilityProvider(
            ItemStack DeviceSlot1,
            ItemStack DeviceSlot2,
            ItemStack DeviceSlot3,
            ItemStack DeviceSlot4,
            ItemStack PipBuckSlot,
            ItemStack AmmoSlot1,
            ItemStack AmmoSlot2,
            ItemStack AmmoSlot3,
            ItemStack AmmoSlot4,
            ItemStack GunSlot1,
            ItemStack GunSlot2,
            ItemStack HarnessSlot
) {

        this.DeviceSlot1 = DeviceSlot1;
        this.DeviceSlot2 = DeviceSlot2;
        this.DeviceSlot3 = DeviceSlot3;
        this.DeviceSlot4 = DeviceSlot4;
        this.PipBuckSlot = PipBuckSlot;
        this.AmmoSlot1 = AmmoSlot1;
        this.AmmoSlot2 = AmmoSlot2;
        this.AmmoSlot3 = AmmoSlot3;
        this.AmmoSlot4 = AmmoSlot4;
        this.GunSlot1 = GunSlot1;
        this.GunSlot2 = GunSlot2;
        this.HarnessSlot = HarnessSlot;

    }


    public static IAddInvCapability instanceFor(EntityPlayer player) {
        return player.getCapability(STATS_CAPA, null);
    }


    @Override
    public AddInv_impl getAdditional_Inventory() {
        return additional_inventory;
    }

    @Override
    public void setAdditional_Inventory(AddInv_impl additional_inventory) {
        this.additional_inventory = additional_inventory;
    }

    @Override
    public ItemStack getDeviceSlot1() {
        return DeviceSlot1;
    }

    @Override
    public void setDeviceSlot1(ItemStack deviceSlot1) {
        this.DeviceSlot1 = deviceSlot1;
    }

    @Override
    public ItemStack getDeviceSlot2() {
        return DeviceSlot2;
    }

    @Override
    public void setDeviceSlot2(ItemStack deviceSlot2) {
        this.DeviceSlot2 = deviceSlot2;
    }

    @Override
    public ItemStack getDeviceSlot3() {
        return DeviceSlot3;
    }

    @Override
    public void setDeviceSlot3(ItemStack deviceSlot3) {
        this.DeviceSlot1 = deviceSlot3;
    }

    @Override
    public ItemStack getDeviceSlot4() {
        return DeviceSlot4;
    }

    @Override
    public void setDeviceSlot4(ItemStack deviceSlot4) {
        this.DeviceSlot4 = deviceSlot4;
    }

    @Override
    public ItemStack getPipBuckSlot() {
        return PipBuckSlot;
    }

    @Override
    public void setPipBuckSlot(ItemStack pipBuckSlot) {
        this.PipBuckSlot = pipBuckSlot;
    }

    @Override
    public ItemStack getAmmoSlot1() {
        return AmmoSlot1;
    }

    @Override
    public void setAmmoSlot1(ItemStack ammoSlot1) {
        this.AmmoSlot1 = ammoSlot1;
    }

    @Override
    public ItemStack getAmmoSlot2() {
        return AmmoSlot2;
    }

    @Override
    public void setAmmoSlot2(ItemStack ammoSlot2) {
        this.AmmoSlot2 = ammoSlot2;
    }

    @Override
    public ItemStack getAmmoSlot3() {
        return AmmoSlot3;
    }

    @Override
    public void setAmmoSlot3(ItemStack ammoSlot3) {
        this.AmmoSlot1 = ammoSlot3;
    }

    @Override
    public ItemStack getAmmoSlot4() {
        return AmmoSlot4;
    }

    @Override
    public void setAmmoSlot4(ItemStack ammoSlot4) {
        this.AmmoSlot4 = ammoSlot4;
    }

    @Override
    public ItemStack getGunSlot1() {
        return GunSlot1;
    }

    @Override
    public void setGunSlot1(ItemStack gunSlot1) {
        this.GunSlot1 = gunSlot1;
    }

    @Override
    public ItemStack getGunSlot2() {
        return GunSlot2;
    }

    @Override
    public void setGunSlot2(ItemStack gunSlot2) {
        this.GunSlot2 = gunSlot2;
    }

    @Override
    public ItemStack getHarnessSlot() {
        return HarnessSlot;
    }

    @Override
    public void setHarnessSlot(ItemStack harnessSlot) {
        this.HarnessSlot = harnessSlot;
    }




    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }


    @Override
    public NBTTagCompound serializeNBT() {
        ItemStack[] stackArray = new ItemStack[]{
                getPipBuckSlot(),
                getDeviceSlot1(),
                getDeviceSlot2(),
                getDeviceSlot3(),
                getDeviceSlot4(),
                getHarnessSlot(),
                getGunSlot1(),
                getGunSlot2(),
                getAmmoSlot1(),
                getAmmoSlot2(),
                getAmmoSlot3(),
                getAmmoSlot4()
        };

        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagCompound tags = new NBTTagCompound();

        for(int i=0;i <12;i++){
            nbt.setTag("Slot_"+i,tags);
            stackArray[i].writeToNBT(tags);
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

        for(int i=0;i <12;i++) {
        ItemStack item = null;
        NBTTagCompound tag =nbt.getCompoundTag("Slot"+i);
            if(i==0){item.deserializeNBT(tag)
            item.de;}
            if(i==1){item.deserializeNBT(tag);}
            if(i==2){item.deserializeNBT(tag);}
            if(i==3){item.deserializeNBT(tag);}
            if(i==4){item.deserializeNBT(tag);}
            if(i==5){item.deserializeNBT(tag);}
            if(i==6){item.deserializeNBT(tag);}
            if(i==7){item.deserializeNBT(tag);}
            if(i==8){item.deserializeNBT(tag);}
            if(i==9){item.deserializeNBT(tag);}
            if(i==10){item.deserializeNBT(tag);}
            if(i==11){item.deserializeNBT(tag);}

        }
    }


    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == STATS_CAPA;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == STATS_CAPA ? (T) this : null;
    }



}
