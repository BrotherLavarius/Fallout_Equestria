package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/**
 * Created by NENYN on 1/17/2017.
 *///Spawn entity and shit
public class MessageGunFire implements IMessage {
    public int type = 0;
    public MessageGunFire(){}
    public MessageGunFire(String type){
        if (type == "main_gun_once") {
            this.type = 0;
        }
        if (type == "main_gun_cont") {
            this.type = 1;
        }
        if (type == "saddlebag_LS_once") {
            this.type = 10;
        }
        if (type == "saddlebag_LS_cont") {
            this.type = 11;
        }
        if (type == "saddlebag_RS_once") {
            this.type = 20;
        }
        if (type == "saddlebag_RS_cont") {
            this.type = 21;
        }

    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = buf.readInt();
    }

    public static class HandlerServer implements IMessageHandler<MessageGunFire, IMessage> {
        @Override
        public IMessage onMessage(MessageGunFire message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            DedicatedServerProxy.handleFireMessage(message,player);
            return null;
        }
    }

    public static class HandlerClient implements IMessageHandler<MessageGunFire, IMessage> {
        @Override
        public IMessage onMessage(MessageGunFire message, MessageContext ctx) {
            ClientOnlyProxy.handleFireMessage(message);
            return null;
        }
    }
}
