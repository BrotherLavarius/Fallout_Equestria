package com.redsparkle.api.Capability.Player.rad;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Created by hoijima on 09.09.16.
 */
public class RadsFactoryStorage implements IStorage<IRadiationCapability> {
    public NBTBase writeNBT(Capability<IRadiationCapability> capability, IRadiationCapability instance, EnumFacing side) {
        return ((RadsFactoryProvider) instance).serializeNBT();
    }

    public void readNBT(Capability<IRadiationCapability> capability, IRadiationCapability instance, EnumFacing side, NBTBase nbt) {
        ((RadsFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
