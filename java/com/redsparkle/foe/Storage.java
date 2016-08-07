package com.redsparkle.foe;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Created by hoijima desu on 07.08.16 desu.
 */
public class Storage implements IStorage<IRadiation> {

    @Override
    public NBTBase writeNBT(Capability<IRadiation> capability, IRadiation instance, EnumFacing side) {
        // return an NBT tag
        return new NBTTagLong(instance.rads());
    }

    @Override
    public void readNBT(Capability<IRadiation> capability, IRadiation instance, EnumFacing side, NBTBase nbt) {
        // load from the NBT tag
        instance.rads();
    }
}
