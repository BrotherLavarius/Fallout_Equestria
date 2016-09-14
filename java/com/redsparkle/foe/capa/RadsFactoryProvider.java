package com.redsparkle.foe.capa;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.redsparkle.foe.FOECapabilitiesInit.RADIATION_CAPABILITY;

/**
 * Created by hoijima on 12.09.16.
 */
public class RadsFactoryProvider implements IRadiationCapability,ICapabilityProvider {
    private int radiationLevel;
    private int prevRadiationLevel;
    private Entity te;

    private int radiationTimer;

    public RadsFactoryProvider(){
        this.radiationLevel = 1;
    }

    public RadsFactoryProvider(Entity entity) {
        this.te = te;
    }



    @Override
    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {
        if (phase == TickEvent.Phase.START)
        {
            if(radiationTimer ++ >= 5000){
                radiationTimer = 0;
                radiationLevel -= 1;
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
    public void addRadiation(int addRadiationLevel) {
        this.radiationLevel = Math.max(this.radiationLevel + addRadiationLevel, 1);
    }

    @Override
    public int getRadiation() {
        return this.radiationLevel;
    }

    @Override
    public void setRadiation(int newRadiationLevel) {
        this.radiationLevel = newRadiationLevel;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return RADIATION_CAPABILITY != null && capability == RADIATION_CAPABILITY;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (RADIATION_CAPABILITY != null && capability == RADIATION_CAPABILITY) return RADIATION_CAPABILITY.cast(this);
        return null;
    }

}
