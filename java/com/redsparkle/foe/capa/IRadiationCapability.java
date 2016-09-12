package com.redsparkle.foe.capa;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by hoijima on 08.09.16.
 */
public interface IRadiationCapability {

    public int getRadiation();

    public int setRadiation();

    public int removeRadiation();

    public int degradationRadiation();

    public NBTTagCompound saveNBTData();

    public void loadNBTData(NBTTagCompound compound);
}
