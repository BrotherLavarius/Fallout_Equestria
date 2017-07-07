package com.redsparkle.api.capa.water;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientWater;
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
public class WaterFactoryProvider implements IWaterCapability, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(IWaterCapability.class)
    public static Capability<IWaterCapability> WATER_CAPABILITY = null;

    private boolean dirty = true;
    private Integer basicWater, waterLevel, prevWaterLevel, waterTimer, FortificationValue, maxWaterLevel;


    public WaterFactoryProvider() {
        this(0, 0, 0, 0, 0, 100);
    }

    public WaterFactoryProvider(Integer basicWater, Integer waterLevel, Integer prevWaterLevel, Integer waterTimer, Integer FortificationValue, Integer maxWaterLevel) {
        this.basicWater = basicWater;
        this.waterLevel = waterLevel;
        this.prevWaterLevel = prevWaterLevel;
        this.waterTimer = waterTimer;
        this.FortificationValue = FortificationValue;
        this.maxWaterLevel = maxWaterLevel;

    }

    public static IWaterCapability instanceFor(EntityPlayer player) {
        return player.getCapability(WATER_CAPABILITY, null);
    }

    public Integer getWater() {
        return waterLevel + basicWater;
    }


    public Integer addWater(Integer addWaterLevel) {
        if ((waterLevel + addWaterLevel) > 100) {
            return 100;
        } else {
            return waterLevel = (waterLevel + addWaterLevel + basicWater);
        }
    }

    public Integer removeWater(Integer removeWaterLevel) {
        return waterLevel = (waterLevel - removeWaterLevel + basicWater);
    }

    @Override
    public Integer maxWater(Integer maxWaterLevel) {
        return maxWaterLevel;
    }


    public Integer setWater(Integer newWaterLevel) {

        return waterLevel = (basicWater + newWaterLevel);
    }

    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {
        if (phase == TickEvent.Phase.START) {
            waterTimer = 0;
            if (waterTimer++ >= 500000) {
                waterLevel -= 1;
            } else waterTimer++;
        }
    }

    public void timedRemoveWater(EntityPlayer player, TickEvent.Phase phase, Integer startCycle, Integer Cycles, Integer FortificationValue) {

        if (phase == TickEvent.Phase.START) {
            for (Integer cycle = startCycle; cycle < Cycles; ++cycle) {
                waterLevel -= FortificationValue;
            }
        }
    }

    @Override
    public void timedRemoveWater(Integer startCycle, Integer Cycles, Integer FortificationValue) {
        for (int i = startCycle; i < Cycles; i++) {
            waterLevel = waterLevel - FortificationValue;
        }
    }

    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }

    public boolean hasChanged() {
        return this.prevWaterLevel != this.waterLevel;
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == WATER_CAPABILITY;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == WATER_CAPABILITY ? (T) this : null;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("water", waterLevel);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setWater(nbt.getInteger("water"));
    }

    public void updateClient(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            if (dirty) main.simpleNetworkWrapper.sendTo(new MessageUpdateClientWater(this), (EntityPlayerMP) player);
            //dirty = false;
        }
    }
}