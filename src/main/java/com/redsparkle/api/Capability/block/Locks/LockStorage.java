package com.redsparkle.api.Capability.block.Locks;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 19.12.17.
 */
public class LockStorage implements Capability.IStorage<LockInterface> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<LockInterface> capability, LockInterface instance, EnumFacing side) {
        return ((LockFactoryProvider) instance).serializeNBT();
    }

    @Override
    public void readNBT(Capability<LockInterface> capability, LockInterface instance, EnumFacing side, NBTBase nbt) {
        ((LockFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);

    }
}
