package com.redsparkle.foe.capa;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by hoijima on 08.09.16.
 */
public interface IRadiationCapability  extends IPlayerData{

    public void setRadiation(int newRadiationLevel);
    public void addRadiation(int addRadiationLevel);
    public int getRadiation();
}
