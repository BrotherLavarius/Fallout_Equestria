package com.redsparkle.foe.capa.rad;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageUpdateClientRads;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 12.09.16.
 */
public class RadsFactoryProvider implements IRadiationCapability, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(IRadiationCapability.class)
    public static Capability<IRadiationCapability> RADIATION_CAPABILITY = null;

    private boolean dirty = true;
    private Integer basicRads, radiationLevel, prevRadiationLevel, radiationTimer, FortificationValue;


    public RadsFactoryProvider() {
        this(0, 0, 0, 0, 0);
    }

    public RadsFactoryProvider(Integer basicRads, Integer radiationLevel, Integer prevRadiationLevel, Integer radiationTimer, Integer FortificationValue) {
        this.basicRads = basicRads;
        this.radiationLevel = radiationLevel;
        this.prevRadiationLevel = prevRadiationLevel;
        this.radiationTimer = radiationTimer;
        this.FortificationValue = FortificationValue;

    }

    public static IRadiationCapability instanceFor(EntityPlayer player) {
        return player.getCapability(RADIATION_CAPABILITY, null);
    }

    public Integer getRadiation()
    {
        return radiationLevel + basicRads;
    }

    public Integer addRadiation(Integer addRadiationLevel) {
        return radiationLevel = (radiationLevel + addRadiationLevel + basicRads);
    }

    public Integer removeRadiation(Integer removeRadiationLevel) {
        return radiationLevel = (radiationLevel - removeRadiationLevel + basicRads);
    }

    public Integer setRadiation(Integer newRadiationLevel) {

        return radiationLevel = (basicRads + newRadiationLevel);
    }

    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {
        if (phase == TickEvent.Phase.START) {
            radiationTimer = 0;
            if (radiationTimer++ >= 5000) {
                radiationLevel -= 1;
            } else radiationTimer++;
        }
    }

    public void timedRemoveRad(EntityPlayer player, TickEvent.Phase phase, Integer startCycle, Integer Cycles, Integer FortificationValue) {

        if (phase == TickEvent.Phase.START) {
            for (Integer cycle = startCycle; cycle < Cycles; ++cycle) {
                radiationLevel -= FortificationValue;
            }
        }
    }

    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }

    public boolean hasChanged() {
        return this.prevRadiationLevel != this.radiationLevel;
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == RADIATION_CAPABILITY;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == RADIATION_CAPABILITY ? (T) this : null;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("rads", radiationLevel);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setRadiation(nbt.getInteger("rads"));
    }

    public void updateClient(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            if (dirty) main.simpleNetworkWrapper.sendTo(new MessageUpdateClientRads(this), (EntityPlayerMP) player);
            //dirty = false;
        }
    }
}