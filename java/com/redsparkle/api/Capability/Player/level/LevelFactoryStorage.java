package com.redsparkle.api.Capability.Player.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;


/**
 * Created by hoijima on 09.09.16.
 */
public class LevelFactoryStorage implements IStorage<ILevelCapability> {

    public NBTBase writeNBT(Capability<ILevelCapability> capability, ILevelCapability instance, EnumFacing side) {
        return ((LevelFactoryProvider) instance).serializeNBT();
    }

    public void readNBT(Capability<ILevelCapability> capability, ILevelCapability instance, EnumFacing side, NBTBase nbt) {
        ((LevelFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
