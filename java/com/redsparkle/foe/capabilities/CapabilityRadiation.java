package com.redsparkle.foe.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Created by hoijima desu on 12.08.16 desu.
 */
public class CapabilityRadiation {

    @CapabilityInject(IRadiation.class)
    public static final Capability<IRadiation> RADS = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IRadiation.class, new CapabilityRadiation.Storage(), CapabilityRadiation.Default.class);
    }

    public interface IRadiation {
        int getRadiation();

        int getMaxRadiation();

        void setRadiation(int Radiation);

        void setMaxRadiation(int max);

        void remRadiation(int amount);

        void addRadiation(int amount);
    }

    public static class Storage implements Capability.IStorage<IRadiation> {

        @Override
        public NBTBase writeNBT(Capability<IRadiation> capability, IRadiation instance, EnumFacing side) {
            NBTTagCompound data = new NBTTagCompound();
            data.setInteger("Radiation", instance.getRadiation());
            data.setInteger("maxRadiation", instance.getMaxRadiation());
            return data;
        }

        @Override
        public void readNBT(Capability<IRadiation> capability, IRadiation instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound data = (NBTTagCompound) nbt;
            instance.setRadiation(data.getInteger("Radiation"));
            instance.setMaxRadiation(data.getInteger("maxRadiation"));
        }
    }


    public static class Default implements IRadiation {
        private int Radiation, maxRadiation;

        @Override
        public int getRadiation() {
            return this.Radiation;
        }

        @Override
        public int getMaxRadiation() {
            return this.maxRadiation;
        }

        @Override
        public void setRadiation(int Radiation) {
            this.Radiation = Radiation;
        }

        @Override
        public void setMaxRadiation(int max) {
            this.maxRadiation = max;
        }

        @Override
        public void remRadiation(int amount) {
            if (this.Radiation - amount >= 0)
                this.Radiation -= amount;
            else
                this.Radiation = 0;
        }

        @Override
        public void addRadiation(int amount) {
            if (this.Radiation + amount <= this.maxRadiation)
                this.Radiation += amount;
            else
                this.Radiation = this.maxRadiation;
        }
    }
}
