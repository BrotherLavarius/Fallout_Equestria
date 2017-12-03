package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/13/2017.
 */
public class MessageClientPlaySound implements IMessage {


    public String type;
    public String position;


    public MessageClientPlaySound() {
    }

    public MessageClientPlaySound(String type, String position) {

        this.type = type;
        this.position = position;
    }


    @Override
    public void fromBytes(ByteBuf buf) {

        this.type = ByteBufUtils.readUTF8String(buf);
        this.position = ByteBufUtils.readUTF8String(buf);

    }

    @Override
    public void toBytes(ByteBuf buf) {

        ByteBufUtils.writeUTF8String(buf, type);
        ByteBufUtils.writeUTF8String(buf, position);

    }

    public static class HandlerClient implements IMessageHandler<MessageClientPlaySound, IMessage> {
        @Override
        public IMessage onMessage(MessageClientPlaySound message, MessageContext ctx) {
            ClientOnlyProxy.MessageClientPlaySound_handler(message, ctx);
            return null;
        }
    }
}

