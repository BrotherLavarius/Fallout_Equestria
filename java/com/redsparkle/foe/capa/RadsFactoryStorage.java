package com.redsparkle.foe.capa;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;


/**
 * Created by hoijima on 09.09.16.
 */
public class RadsFactoryStorage implements IStorage<IRadiationCapability> {

    public static final RadsFactoryStorage radsStorage = new RadsFactoryStorage();

    @Override
    public NBTBase writeNBT(Capability<IRadiationCapability> capability, IRadiationCapability instance, EnumFacing side) {
        NBTTagCompound nbtData = new NBTTagCompound();
        return instance.getRadiation().writeToNBT(nbtData);
    }

    @Override
    public void readNBT(Capability<IRadiationCapability> capability, IRadiationCapability instance, EnumFacing side, NBTBase nbt) {

        NBTTagCompound properties = (NBTTagCompound) nbt;
        instance.getRadiation().readFromNBT(properties);

    }
}
