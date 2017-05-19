package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/17/2017.
 */

//TODO: finish this class
public class MessageFireToClientServer implements IMessage {
    public Integer GunID;

    public MessageFireToClientServer(){

    }

    public MessageFireToClientServer(Integer gunid){
        this.GunID = gunid;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        GunID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(GunID);
    }

    public class HandlerClient implements IMessageHandler<MessageFireToClientServer, IMessage> {
        @Override
        public IMessage onMessage(MessageFireToClientServer message, MessageContext ctx) {
            ClientOnlyProxy.handleFireMessage(message);
            return null;
        }
    }

    public class HandlerServer implements IMessageHandler<MessageFireToClientServer, IMessage> {
        @Override
        public IMessage onMessage(MessageFireToClientServer message, MessageContext ctx) {
            DedicatedServerProxy.handleFireMessage(message);
            return null;
        }
    }
}
