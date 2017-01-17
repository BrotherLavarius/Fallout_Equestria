package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/15/2017.
 */
public class MessageGunReloadToClient implements IMessage {
    public Integer gun ;
    //public String[] guns = {"TenMM","LazerPistol","DobleBarrelShotgun"};

    MessageGunReloadToClient(){}

    MessageGunReloadToClient(Integer gun){
        this.gun = gun;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        gun = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(gun);
    }
    public static class Handler implements IMessageHandler<MessageGunReloadToClient, IMessage> {

        @Override
        public IMessage onMessage(MessageGunReloadToClient message, MessageContext ctx) {
            ClientOnlyProxy.handleGundMessageReload(message);
            return null;
        }

    }
}
