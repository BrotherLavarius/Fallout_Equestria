package com.redsparkle.foe.capa.skills;

import com.redsparkle.foe.capa.spechial.ISpechialCapability;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 3/1/2017.
 */
public class SkillsFactoryStorage implements IStorage<ISpechialCapability> {

    public NBTBase writeNBT(Capability<ISpechialCapability> capability, ISpechialCapability instance, EnumFacing side) {
        return ((SkillsFactoryProvider) instance).serializeNBT();    }

    public void readNBT(Capability<ISpechialCapability> capability, ISpechialCapability instance, EnumFacing side, NBTBase nbt) {
        ((SkillsFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);

    }
}
