package com.redsparkle.api.Capability.Player.saddlegun_shooting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by hoijima on 30.08.17.
 */
public class ITrigger_item_Provider implements ITrigger_item, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(ITrigger_item.class)
    public static Capability<ITrigger_item> TRIGGER_ITEM = null;


    boolean status;

    public ITrigger_item_Provider() {
        this(false);
    }

    public ITrigger_item_Provider(boolean status_of_item) {
        this.status = status_of_item;

    }

    public static ITrigger_item instanceFor(EntityPlayer player) {
        return player.getCapability(TRIGGER_ITEM, null);
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == TRIGGER_ITEM;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == TRIGGER_ITEM ? (T) this : null;
    }

    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("status", status);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setStatus(nbt.getBoolean("status"));
    }
}
