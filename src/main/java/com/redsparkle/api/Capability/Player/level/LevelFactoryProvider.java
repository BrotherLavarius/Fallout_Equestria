package com.redsparkle.api.Capability.Player.level;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerLevel;
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
    public Integer addLevel(Integer addLevelLevel) {
        return Level = (Level + addLevelLevel);
    }
    public Integer removeLevel(Integer removeLevelLevel) {
        return Level = (Level - removeLevelLevel);
    }
    public Integer setLevel(Integer newLevelLevel) {
        return Level = newLevelLevel;
    }
    public Integer getProgress() {
        return Progress;
    }
    public Integer addProgress(Integer addProgressProgress) {
        return Progress = (Progress + addProgressProgress);
    }
    public Integer removeProgress(Integer removeProgressProgress) {
        return Progress = (Progress - removeProgressProgress);
    }
    public Integer setProgress(Integer newProgressProgress) {
        return Progress = newProgressProgress;
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
        if (!player.getEntityWorld().isRemote) {
            if (dirty)
                main.simpleNetworkWrapper.sendTo(new MessageUpdateClientServerLevel(this), (EntityPlayerMP) player);
            //dirty = false;
        }
    }
}