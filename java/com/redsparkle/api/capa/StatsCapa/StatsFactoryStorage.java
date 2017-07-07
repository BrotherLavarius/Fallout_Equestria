package com.redsparkle.api.capa.StatsCapa;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;


/**
 * Created by hoijima on 09.09.16.
 */
public class StatsFactoryStorage implements IStorage<IStatsCapability> {

    public NBTBase writeNBT(Capability<IStatsCapability> capability, IStatsCapability instance, EnumFacing side) {
        return ((StatsCapabilityProvider) instance).serializeNBT();
    }

    public void readNBT(Capability<IStatsCapability> capability, IStatsCapability instance, EnumFacing side, NBTBase nbt) {
        ((StatsCapabilityProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
