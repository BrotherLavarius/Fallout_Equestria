package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.capa.rad.IRadiationCapability;
import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 12/25/2016.
 */
public class MessageUpdateClientRads implements IMessage {
    public Integer radiation;

    public MessageUpdateClientRads() {
    }

    public MessageUpdateClientRads(IRadiationCapability rad) {
        this.radiation = rad.getRadiation();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        radiation = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(radiation);
    }

    public static class Handler implements IMessageHandler<MessageUpdateClientRads, IMessage> {

        @Override
        public IMessage onMessage(MessageUpdateClientRads message, MessageContext ctx) {
            ClientOnlyProxy.handleRadMessage(message);
            return null;
        }
    }
}
