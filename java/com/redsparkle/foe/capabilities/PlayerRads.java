package com.redsparkle.foe.capabilities;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by hoijima on 24.08.16.
 */
public class PlayerRads extends PlayerCapabilities {

    public Integer Radiation;

    public void writeCapabilitiesToNBT(NBTTagCompound tagCompound)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setInteger("Radiation", this.Radiation);
        tagCompound.setTag("abilities", nbttagcompound);
    }
    public void readCapabilitiesFromNBT(NBTTagCompound tagCompound)
    {
        if (tagCompound.hasKey("abilities", 10))
        {
            NBTTagCompound nbttagcompound = tagCompound.getCompoundTag("abilities");
            this.Radiation = nbttagcompound.getInteger("Radiation");


            if (Integer.valueOf(nbttagcompound.getInteger("Radiation")) == null)
            {
                Radiation = 0;
            }

        }
    }
}
