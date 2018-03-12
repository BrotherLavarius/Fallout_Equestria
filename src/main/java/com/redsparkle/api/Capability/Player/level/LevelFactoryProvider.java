package com.redsparkle.api.Capability.Player.level;

import com.google.gson.JsonObject;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.UnifiedMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by hoijima on 12.09.16.
 */
public class LevelFactoryProvider implements ILevelCapability, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(ILevelCapability.class)
    public static Capability<ILevelCapability> LEVEL_CAPABILITY = null;
    private boolean dirty = true;
    private Integer Level;
    private Integer Progress;

    public LevelFactoryProvider() {
    }

    public static ILevelCapability instanceFor(EntityPlayer player) {
        return player.getCapability(LEVEL_CAPABILITY, null);
    }

    @Override
    public void initNewplayer() {
        setLevel(0);
        setProgress(0);
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer newLevelLevel) {
        Level = newLevelLevel;
    }

    public void addLevel(Integer addLevelLevel) {

        Level = (Level + addLevelLevel);
    }

    public void removeLevel(Integer removeLevelLevel) {
        Level = (Level - removeLevelLevel);
    }

    public Integer getProgress() {
        return Progress;
    }

    public void setProgress(Integer newProgressProgress) {
        Progress = newProgressProgress;
    }

    public void addProgress(Integer addProgressProgress) {
        Progress = (Progress + addProgressProgress);
    }

    public void removeProgress(Integer removeProgressProgress) {
        Progress = (Progress - removeProgressProgress);
    }

    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
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
        JsonObject message = new JsonObject();
        JsonObject body = new JsonObject();
        if (!player.getEntityWorld().isRemote) {
            if (dirty)


                message.addProperty("type", "lvl_msg");
            body.addProperty("lvl", this.getLevel());
            body.addProperty("progress", this.getProgress());
            message.add("details", body);
            main.simpleNetworkWrapper.sendTo(new UnifiedMessage(message), (EntityPlayerMP) player);
            //dirty = false;
        }
    }
}