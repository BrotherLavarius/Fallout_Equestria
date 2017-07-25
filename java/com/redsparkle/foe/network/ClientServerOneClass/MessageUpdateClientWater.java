package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.water.IWaterCapability;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 12/25/2016.
 */
public class MessageUpdateClientWater implements IMessage {
    public Integer water;

    public MessageUpdateClientWater() {
    }

    public MessageUpdateClientWater(IWaterCapability wat) {
        this.water = wat.getWater();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        water = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(water);
    }

    public static class HandlerClient implements IMessageHandler<MessageUpdateClientWater, IMessage> {

        @Override
        public IMessage onMessage(MessageUpdateClientWater message, MessageContext ctx) {
            ClientOnlyProxy.handleWaterMessage(message);
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageUpdateClientWater, IMessage> {

        @Override
        public IMessage onMessage(MessageUpdateClientWater message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleWaterMessage(message, playerMP);
            return null;
        }

    }
}
