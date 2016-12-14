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

    public static final RadsFactoryStorage radsStorageUnit = new RadsFactoryStorage();
    @Override
    public NBTBase writeNBT(Capability<IRadiationCapability> capability, IRadiationCapability instance, EnumFacing side)
    {
        NBTTagCompound compound = new NBTTagCompound();

        compound.setInteger("RadiationLevel", instance.getRadiation());

        return compound;
    }

    @Override
    public void readNBT(Capability<IRadiationCapability> capability, IRadiationCapability instance, EnumFacing side, NBTBase nbt)
    {
        if (!(nbt instanceof NBTTagCompound)) throw new IllegalArgumentException("Thirst must be read from an NBTTagCompound!");

        NBTTagCompound compound = (NBTTagCompound)nbt;

        if (compound.hasKey("RadiationLevel"))
        {
            instance.setRadiation(compound.getInteger("RadiationLevel"));
        }

    }
}
