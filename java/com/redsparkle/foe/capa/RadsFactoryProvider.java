package com.redsparkle.foe.capa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 12.09.16.
 */
public class RadsFactoryProvider implements IRadiationCapability {
    private int radiationLevel;
    private int prevRadiationLevel;

    private int radiationTimer;

    public RadsFactoryProvider(){
        this.radiationLevel = 1;
    }

    @Override
    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {
        if (phase == TickEvent.Phase.START)
        {
            if(radiationTimer ++ >= 5000){
                radiationTimer = 0;
                radiationLevel -= 0.01F;
            }
            else radiationTimer ++;
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

    @Override
    public void setRadiation(int newRadiationLevel) {
        this.radiationLevel = newRadiationLevel;
    }

    @Override
    public void addRadiation(int addRadiationLevel) {
        this.radiationLevel = Math.max(this.radiationLevel + addRadiationLevel, 1);
    }

    @Override
    public int getRadiation() {
        return this.radiationLevel;
    }

}
