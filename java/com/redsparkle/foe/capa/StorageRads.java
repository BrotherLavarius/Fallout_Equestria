package com.redsparkle.foe.capa;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by hoijima on 09.09.16.
 */
public class StorageRads implements Capability.IStorage<IRadiationCapability> {
    @Override
    public NBTBase writeNBT(Capability<IRadiationCapability> capability, IRadiationCapability instance, EnumFacing side) {
        return null;
    }

    @Override
    public void readNBT(Capability<IRadiationCapability> capability, IRadiationCapability instance, EnumFacing side, NBTBase nbt) {

    }
}
