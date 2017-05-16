package com.redsparkle.foe.capa.level;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageUpdateClientServerLevel;
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
public class LevelFactoryProvider implements ILevelCapability, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(ILevelCapability.class)
    public static Capability<ILevelCapability> LEVEL_CAPABILITY = null;

    private boolean dirty = true;
    private Integer basicLevel, LevelLevel, prevLevelLevel,Level;
    private Integer basicProgress, LevelProgress, prevLevelProgress,Progress;


    public LevelFactoryProvider() {
        this(0, 0, 0, 0, 0, 0, 0);
    }

    public LevelFactoryProvider(Integer basicLevel, Integer Level, Integer prevLevelLevel,Integer basicProgress,Integer LevelProgress,Integer prevLevelProgress,Integer Progress ){
        this.basicLevel = basicLevel;
        this.Level = Level;
        this.prevLevelLevel = prevLevelLevel;
        this.basicProgress= basicProgress;
        this.LevelProgress= LevelProgress;
        this.prevLevelProgress= prevLevelProgress;
        this.Progress = Progress;

    }

    public static ILevelCapability instanceFor(EntityPlayer player) {
        return player.getCapability(LEVEL_CAPABILITY, null);
    }

    public Integer getLevel() {
        return Level + basicLevel;
    }

    public Integer addLevel(Integer addLevelLevel) {
        return Level = (LevelLevel + addLevelLevel + basicLevel);
    }

    public Integer removeLevel(Integer removeLevelLevel) {
        return Level = (LevelLevel - removeLevelLevel + basicLevel);
    }

    public Integer setLevel(Integer newLevelLevel) {

        return Level = (basicLevel + newLevelLevel);
    }


    public Integer getProgress() {
        return Progress + basicProgress;
    }

    public Integer addProgress(Integer addProgressProgress) {
        return Progress = (Progress + addProgressProgress + basicProgress);
    }

    public Integer removeProgress(Integer removeProgressProgress) {
        return Progress = (Progress - removeProgressProgress + basicProgress);
    }

    public Integer setProgress(Integer newProgressProgress) {

        return Progress = (basicProgress + newProgressProgress);
    }
    
    

    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }

    public boolean hasChanged() {
        return this.prevLevelLevel != this.Level;
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == LEVEL_CAPABILITY;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == LEVEL_CAPABILITY ? (T) this : null;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Level", Level);
        nbt.setInteger("Progress", Progress);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setLevel(nbt.getInteger("Level"));
        setProgress(nbt.getInteger("Progress"));
    }

    public void updateClient(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            if (dirty) main.simpleNetworkWrapper.sendTo(new MessageUpdateClientServerLevel(this), (EntityPlayerMP) player);
            //dirty = false;
        }
    }
}