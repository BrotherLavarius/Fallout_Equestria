package com.redsparkle.foe.capa.spechial;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Created by hoijima on 3/1/2017.
 */
public class SpechialFactoryStorage implements IStorage<ISpechialCapability> {

    public NBTBase writeNBT(Capability<ISpechialCapability> capability, ISpechialCapability instance, EnumFacing side) {
        return ((SpechialFactoryProvider) instance).serializeNBT();
    }


    public void readNBT(Capability<ISpechialCapability> capability, ISpechialCapability instance, EnumFacing side, NBTBase nbt) {
        ((SpechialFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
