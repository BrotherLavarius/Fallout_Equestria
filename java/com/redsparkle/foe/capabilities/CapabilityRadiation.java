package com.redsparkle.foe.capabilities;

import com.redsparkle.foe.capabilities.interfaces.IRad;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * Created by hoijima desu on 12.08.16 desu.
 */
public class CapabilityRadiation {

    /**
     * Access to the capability. Can be used for making checks.
     */
    @CapabilityInject(IRad.class)
    public static Capability<IRad> CAPABILITY_RADS = null;


    public static class CapabilityRad<T extends IRad> implements IStorage<IRad> {

        @Override
        public NBTBase writeNBT (Capability<IRad> capability, IRad instance, EnumFacing side) {

            return null;
        }

        @Override
        public void readNBT (Capability<IRad> capability, IRad instance, EnumFacing side, NBTBase nbt) {

        }
    }
}
