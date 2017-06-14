package com.redsparkle.foe.capa.water;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;


/**
 * Created by hoijima on 09.09.16.
 */
public class WaterFactoryStorage implements IStorage<IWaterCapability> {

    public NBTBase writeNBT(Capability<IWaterCapability> capability, IWaterCapability instance, EnumFacing side) {
        return ((WaterFactoryProvider) instance).serializeNBT();
    }

    public void readNBT(Capability<IWaterCapability> capability, IWaterCapability instance, EnumFacing side, NBTBase nbt) {
        ((WaterFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
