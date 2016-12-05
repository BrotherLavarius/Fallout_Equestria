package com.redsparkle.foe.network;

import com.redsparkle.foe.Init.FOECapabilitiesInit;
import com.redsparkle.foe.capa.IRadiationCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 12/6/2016.
 */
public class MessagePlayerPropertiesHandler implements IMessageHandler<MessagePlayerProperties, IMessage> {
    @Override
    public IMessage onMessage(final MessagePlayerProperties message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            @Override
            public void run() {
                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                if (player.hasCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null)) //This is true
                {
                    IRadiationCapability cap = player.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null);

                    cap.setRadiation(message.RadData);
                }
            }
        });
        return null;
    }
}
