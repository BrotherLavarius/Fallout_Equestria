package com.redsparkle.foe.capa;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by hoijima on 12.09.16.
 */
public class RadsFactoryProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {
    @CapabilityInject(IRadiationCapability.class)

    public static Capability<IRadiationCapability> RADIATION_CAPABILITY = null;
    private IRadiationCapability radiationI = null;

    public RadsFactoryProvider() {
        radiationI = new RadsDefaultImpl();
    }

    public RadsFactoryProvider(IRadiationCapability radiationI) {
        this.radiationI = radiationI;
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return RADIATION_CAPABILITY != null && capability == RADIATION_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        if (RADIATION_CAPABILITY != null && capability == RADIATION_CAPABILITY)
            return (T) radiationI;
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return radiationI.saveNBTData();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        radiationI.loadNBTData(nbt);
    }
}