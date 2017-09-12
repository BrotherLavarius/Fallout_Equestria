package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/17/2017.
 *///Spawn entity and shit
public class MessageGunFire implements IMessage {
    public int type = 0;
    public double x=0;
    public double y=0;
    public double z=0;
    public double xHeading=0.0D;
    public double yHeading=0.0D;
    public double zHeading=0.0D;
    public float vel=0.0F;
    public float inac=0.0F;

    public MessageGunFire(){}
    public MessageGunFire(int type,double x,double y,double z,double xHeading,double yHeading,double zHeading ,float vel,float inac) {
        this.x=x;
        this.y=y;
        this.z=z;
        this.xHeading=xHeading;
        this.yHeading=yHeading;
        this.zHeading=zHeading;

        this.vel=vel;
        this.inac=inac;

        this.type = type;

    }


    public MessageGunFire(String type){
        if (type == "gun_main") {
            this.type = 0;
        }

        if (type == "gun_saddlebagLS") {
            this.type = 10;
        }

        if (type == "gun_saddlebagRS") {
            this.type = 20;
        }



    }
    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(type);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);

        buf.writeDouble(xHeading);
        buf.writeDouble(yHeading);
        buf.writeDouble(zHeading);

        buf.writeDouble(vel);
        buf.writeDouble(inac);
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        this.type = buf.readInt();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();

        this.xHeading = buf.readDouble();
        this.yHeading = buf.readDouble();
        this.zHeading = buf.readDouble();

        this.vel = buf.readFloat();
        this.inac = buf.readFloat();

    }

    public static class HandlerServer implements IMessageHandler<MessageGunFire, IMessage> {
        @Override
        public IMessage onMessage(MessageGunFire message, MessageContext ctx) {
            DedicatedServerProxy.MessageGunFire_handler(message,ctx);
            return null;
        }
    }
    public static class HandlerClient implements IMessageHandler<MessageGunFire, IMessage> {
        @Override
        public IMessage onMessage(MessageGunFire message, MessageContext ctx) {
            ClientOnlyProxy.MessageGunFire_hadnler(message,ctx);
            return null;
        }
    }

}
