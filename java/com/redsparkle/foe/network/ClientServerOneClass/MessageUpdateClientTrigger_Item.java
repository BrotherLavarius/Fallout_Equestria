package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item;
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
public class MessageUpdateClientTrigger_Item implements IMessage {
    public boolean status;

    public MessageUpdateClientTrigger_Item() {
    }

    public MessageUpdateClientTrigger_Item(boolean stats) {
        this.status = stats;
    }

    public MessageUpdateClientTrigger_Item(ITrigger_item stats) {
        this.status = stats.getStatus();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        status = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(status);
    }

    public static class HandlerClient implements IMessageHandler<MessageUpdateClientTrigger_Item, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateClientTrigger_Item message, MessageContext ctx) {
            ClientOnlyProxy.handleTrigger_Item_Message(message);
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageUpdateClientTrigger_Item, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateClientTrigger_Item message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().player;
            DedicatedServerProxy.handleTrigger_Item_Message(message, playerMP);
            return null;
        }
    }
}
