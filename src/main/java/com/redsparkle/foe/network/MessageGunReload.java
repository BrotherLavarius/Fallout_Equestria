package com.redsparkle.foe.network;

import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/13/2017.
 */
public class MessageGunReload implements IMessage {


    public String type;

    public MessageGunReload() {
    }

    public MessageGunReload(String type) {
        this.type = type;

    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, type);

    }

    public static class HandlerServer implements IMessageHandler<MessageGunReload, IMessage> {
        @Override
        public IMessage onMessage(MessageGunReload message, MessageContext ctx) {
            DedicatedServerProxy.MessageGunReload_hadnler(message, ctx);
            return null;
        }

    }
}

