package com.redsparkle.foe.network;

import com.redsparkle.foe.Init.FOECapabilitiesInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by NENYN on 12/6/2016.
 */
public class MessagePlayerProperties implements IMessage {
    public Integer RadData;
    public MessagePlayerProperties(){}

    public MessagePlayerProperties(EntityPlayer entityPlayer) {
        this.RadData = RadData;
        RadData = entityPlayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).getRadiation();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        RadData = ByteBufUtils.varIntByteCount(RadData);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.varIntByteCount(RadData);
    }
}
