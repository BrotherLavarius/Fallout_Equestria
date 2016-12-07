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
    public Integer RadData = 0;

    public MessagePlayerProperties(EntityPlayer entityPlayer) {
        this.RadData = entityPlayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).getRadiation();
    }


    /**
     * Called by the network code.
     * Used to write the contents of your message member variables into the ByteBuf, ready for transmission over the network.
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(RadData);
        System.out.println("TargetEffectMessageToClient:toBytes length=" + buf.readableBytes());
    }


    /**
     * Called by the network code once it has received the message bytes over the network.
     * Used to read the ByteBuf contents into your member variables
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {
    try {
        this.RadData = buf.readInt();
        }catch (IndexOutOfBoundsException ioe) {
            System.err.println("Exception while reading TargetEffectMessageToClient: " + ioe);
            return;
        }
    }


}
