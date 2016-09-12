package com.redsparkle.foe.capa;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by hoijima on 09.09.16.
 */
public class DefaultRadsFactory implements IRadiationCapability {

    private PlayerRadiationPreset radiation = new PlayerRadiationPreset();

    @Override
    public int getRadiation() {
        return 0;
    }

    @Override
    public int setRadiation() {
        return 0;
    }

    @Override
    public int removeRadiation() {
        return 0;
    }

    @Override
    public int degradationRadiation() {
        return 0;
    }

    @Override
    public NBTTagCompound saveNBTData() {

        return (NBTTagCompound) RadsFactoryStorage.radsStorage.writeNBT(RadsFactoryProvider.RADIATION, this, null);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        RadsFactoryStorage.radsStorage.readNBT(RadsFactoryProvider.RADIATION, this, null, compound);
    }

}
