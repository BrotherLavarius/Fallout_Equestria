package com.redsparkle.foe.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/17/2017.
 *///Spawn entity and shit
public class MessageFireToServer implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
    public static class Handler implements IMessageHandler<MessageFireToServer, IMessage> {

        @Override
        public IMessage onMessage(MessageFireToServer message, MessageContext ctx) {
            return null;
        }
    }
}
