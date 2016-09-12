package com.redsparkle.foe.capa;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 12.09.16.
 */
public class RadsFactoryProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {


    @CapabilityInject(IRadiationCapability.class)
    public static Capability<IRadiationCapability> RADIATION = null;


    private IRadiationCapability radiation = null;

    public RadsFactoryProvider(DefaultRadsFactory defaultRadsFactory) {
        radiation = new DefaultRadsFactory();
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return RADIATION != null && capability == RADIATION;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (RADIATION != null && capability == RADIATION)
            return (T) radiation;
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return radiation.saveNBTData();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        radiation.loadNBTData(nbt);

    }
}
