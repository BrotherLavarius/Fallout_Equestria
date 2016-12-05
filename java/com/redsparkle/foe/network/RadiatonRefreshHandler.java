package com.redsparkle.foe.network;

import com.redsparkle.foe.Init.FOECapabilitiesInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 04.12.2016.
 */
public class RadiatonRefreshHandler implements IMessageHandler<RadiationMessage, IMessage> {


    @Override
    public IMessage onMessage(RadiationMessage message, MessageContext ctx) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        int Rads = ctx.getServerHandler().playerEntity.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).getRadiation();
        message.toSend = Rads;
        if (player.hasCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null)){
            player.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).setRadiation(message.toSend);
        }else {
            player.hasCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null);
            player.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).setRadiation(message.toSend) ;
        }
        return null;
    }
}
