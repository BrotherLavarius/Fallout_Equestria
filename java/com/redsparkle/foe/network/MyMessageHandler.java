package com.redsparkle.foe.network;

import com.redsparkle.foe.Init.FOECapabilitiesInit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 04.12.2016.
 */
public class MyMessageHandler implements IMessageHandler<MyMessageClass, IMessage> {
// Do note that the default constructor is required, but implicitly defined in this case

    @Override
    public IMessage onMessage(MyMessageClass message, MessageContext ctx) {
        // This is the player the packet was sent to the server from
        EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
        // The value that was sent
        int amount = serverPlayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).getRadiation();
        amount = message.toSend;
        return null;
    }
}
