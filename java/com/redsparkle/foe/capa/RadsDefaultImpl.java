package com.redsparkle.foe.capa;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 14.09.16.
 */
public class RadsDefaultImpl implements IRadiationCapability {

    private int basicRads = 0;
    private int radiationLevel;
    private int prevRadiationLevel;
    private int radiationTimer;

    @Override
    public void addRadiation(int addRadiationLevel) {
        this.radiationLevel = Math.max(this.radiationLevel + addRadiationLevel + basicRads, 1);
    }

    @Override
    public int getRadiation() {
        return this.radiationLevel + basicRads;
    }

    @Override
    public void setRadiation(int newRadiationLevel) {
        this.radiationLevel = basicRads + newRadiationLevel;
    }

    @Override
    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {
        if (phase == TickEvent.Phase.START) {
            if (radiationTimer++ >= 5000) {
                radiationTimer = 0;
                radiationLevel -= 1;
            } else radiationTimer++;
        }
    }

    @Override
    public boolean hasChanged() {
        return this.prevRadiationLevel != this.radiationLevel;
    }


    @Override
    public void onSendClientUpdate() {
        this.prevRadiationLevel = this.radiationLevel;
    }

    //#############################################################
    @Override
    public NBTTagCompound saveNBTData() {
        return (NBTTagCompound) RadsFactoryStorage.radsStorageUnit.writeNBT(RadsFactoryProvider.RADIATION_CAPABILITY, this, null);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        RadsFactoryStorage.radsStorageUnit.readNBT(RadsFactoryProvider.RADIATION_CAPABILITY, this, null, compound);

    }

}
