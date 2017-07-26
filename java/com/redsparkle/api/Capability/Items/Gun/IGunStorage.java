package com.redsparkle.api.Capability.Items.Gun;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
/**
 * Created by hoijima on 25.07.17.
 */
public class IGunStorage implements Capability.IStorage<IGunInterface> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IGunInterface> capability, IGunInterface instance, EnumFacing side) {
        return ((GunFactoryProvider) instance).serializeNBT();
    }
    @Override
    public void readNBT(Capability<IGunInterface> capability, IGunInterface instance, EnumFacing side, NBTBase nbt) {
        ((GunFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
