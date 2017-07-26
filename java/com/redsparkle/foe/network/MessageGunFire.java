package com.redsparkle.foe.network;
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
    public int type;
    public MessageGunFire(){}
    public MessageGunFire(String type){
        if(type == "firearm"){this.type =0;}
        if(type == "shotgun"){this.type =1;}
        if(type == "laser"){this.type =2;}
        if(type == "flame"){this.type =3;}
        if(type == "flare"){this.type =4;}
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
    }
    public static class Handler implements IMessageHandler<MessageGunFire, IMessage> {
        @Override
        public IMessage onMessage(MessageGunFire message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleFireMessage(message,player);
            return null;
        }
    }
}
