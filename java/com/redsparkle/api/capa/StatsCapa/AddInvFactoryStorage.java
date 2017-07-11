package com.redsparkle.api.capa.StatsCapa;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;


/**
 * Created by hoijima on 09.09.16.
 */
public class AddInvFactoryStorage implements IStorage<IAddInvCapability> {

    public NBTBase writeNBT(Capability<IAddInvCapability> capability, IAddInvCapability instance, EnumFacing side) {
        return ((AddInvCapabilityProvider) instance).serializeNBT();
    }

    public void readNBT(Capability<IAddInvCapability> capability, IAddInvCapability instance, EnumFacing side, NBTBase nbt) {
        ((AddInvCapabilityProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
