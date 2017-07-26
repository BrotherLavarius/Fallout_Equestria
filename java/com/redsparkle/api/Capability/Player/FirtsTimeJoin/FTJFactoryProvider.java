package com.redsparkle.api.Capability.Player.FirtsTimeJoin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by hoijima on 12.09.16.
 */
public class FTJFactoryProvider implements IFTJCapability, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(IFTJCapability.class)
    public static Capability<IFTJCapability> FTJ_CAPABILITY = null;

    private boolean FTJ = true;


    public FTJFactoryProvider() {
        this(true);
    }

    public FTJFactoryProvider(boolean FTJ) {
        this.FTJ = FTJ;

    }

    public static IFTJCapability instanceFor(EntityPlayer player) {
        return player.getCapability(FTJ_CAPABILITY, null);
    }

    public Boolean setFTJ(boolean FTJVal) {

        return FTJ = FTJVal;
    }

    public Boolean getFTJ() {
        return FTJ;
    }


    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == FTJ_CAPABILITY;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == FTJ_CAPABILITY ? (T) this : null;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("FTJ", FTJ);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setFTJ(nbt.getBoolean("FTJ"));
    }

}