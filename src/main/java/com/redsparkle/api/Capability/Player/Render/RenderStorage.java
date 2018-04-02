package com.redsparkle.api.Capability.Player.Render;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class RenderStorage implements Capability.IStorage<IRenders> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IRenders> capability, IRenders instance, EnumFacing side) {
        return ((RenderProvider) instance).serializeNBT();

    }

    @Override
    public void readNBT(Capability<IRenders> capability, IRenders instance, EnumFacing side, NBTBase nbt) {
        ((RenderProvider) instance).deserializeNBT((NBTTagCompound) nbt);

    }
}
