package com.redsparkle.api.Capability.Items.Ammo;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 24.07.17.
 */
public class IAmmoStorage implements Capability.IStorage<IAmmoInterface> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IAmmoInterface> capability, IAmmoInterface instance, EnumFacing side) {
        return ((AmmoFactoryProvider) instance).serializeNBT();
    }

    @Override
    public void readNBT(Capability<IAmmoInterface> capability, IAmmoInterface instance, EnumFacing side, NBTBase nbt) {
        ((AmmoFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
