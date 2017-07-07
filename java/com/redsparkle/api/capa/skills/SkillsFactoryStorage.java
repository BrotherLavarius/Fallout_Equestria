package com.redsparkle.api.capa.skills;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Created by hoijima on 3/1/2017.
 */
public class SkillsFactoryStorage implements IStorage<ISkillsCapability> {

    public NBTBase writeNBT(Capability<ISkillsCapability> capability, ISkillsCapability instance, EnumFacing side) {
        return ((SkillsFactoryProvider) instance).serializeNBT();
    }

    public void readNBT(Capability<ISkillsCapability> capability, ISkillsCapability instance, EnumFacing side, NBTBase nbt) {
        ((SkillsFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);

    }
}
