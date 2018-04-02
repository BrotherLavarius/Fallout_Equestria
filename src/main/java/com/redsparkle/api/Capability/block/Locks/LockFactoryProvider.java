package com.redsparkle.api.Capability.block.Locks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by hoijima on 19.12.17.
 */
public class LockFactoryProvider implements LockInterface, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(LockInterface.class)
    public static Capability<LockInterface> LOCK_CAPABILITY = null;
    public int ComplexLvl;
    public boolean LockStatus;

    public LockFactoryProvider() {
        this(1, false);
    }

    public LockFactoryProvider(int ComplexLvl, boolean LockStatus) {
        this.ComplexLvl = ComplexLvl;
        this.LockStatus = LockStatus;
    }

    @Override
    public int getComplex() {
        return ComplexLvl;
    }

    @Override
    public void setComplex(int Complex) {
        this.ComplexLvl = Complex;
    }

    @Override
    public boolean getLockStatus() {
        return LockStatus;
    }

    @Override
    public void setLockStatus(Boolean status) {
        this.LockStatus = status;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == LOCK_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == LOCK_CAPABILITY ? (T) this : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Complexity", ComplexLvl);
        nbt.setBoolean("Locked_Status", LockStatus);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setComplex(nbt.getInteger("Complexity"));
        setLockStatus(nbt.getBoolean("Locked_Status"));
    }


}
