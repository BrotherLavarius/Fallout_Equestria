package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 19.05.17.
 */
public class MessageOpenGuiClient implements IMessage {


    public Integer
            ID;

    public MessageOpenGuiClient(){}
    public MessageOpenGuiClient(
            Integer id
    ){
     this.ID = id;


    }

    @Override
    public void fromBytes(ByteBuf buf) {
        ID= buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(ID);
    }
    public static class HandlerClient implements IMessageHandler<MessageOpenGuiClient, IMessage> {


        @Override
        public IMessage onMessage(MessageOpenGuiClient message, MessageContext ctx) {
            ClientOnlyProxy.handleOpenGui(message);
            return null;
        }
    }
}
