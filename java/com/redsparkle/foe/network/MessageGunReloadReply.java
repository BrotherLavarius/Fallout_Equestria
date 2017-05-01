package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/15/2017.
 */
public class MessageGunReloadReply implements IMessage {
    public int soundname = 0;
    //public String[] guns = {"TenMM","LaserPistol","DobleBarrelShotgun"};

    public MessageGunReloadReply() {
    }

    public MessageGunReloadReply(Integer soundname) {
        this.soundname = soundname;
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(soundname);
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        soundname = buf.readInt();
    }


    public static class Handler implements IMessageHandler<MessageGunReloadReply, IMessage> {

        @Override
        public IMessage onMessage(MessageGunReloadReply message, MessageContext ctx) {
            ClientOnlyProxy.handleGundMessageReload(message);
            return null;
        }

    }
}
