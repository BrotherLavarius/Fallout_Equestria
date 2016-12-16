package com.redsparkle.foe.capa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 08.09.16.
 */
public interface IRadiationCapability  {


    public void addRadiation(int addRadiationLevel);

    public void removeRadiation(int removeRadiationLevel);

    public int getRadiation();

    public void setRadiation(int newRadiationLevel);

    public NBTTagCompound saveNBTData();

    public void loadNBTData(NBTTagCompound compound);

    public void update(EntityPlayer player, World world, TickEvent.Phase phase);

    public boolean hasChanged();

    public void onSendClientUpdate();

//public IMessage createUpdateMessage();


}
