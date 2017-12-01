package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 12/25/2016.
 */
public class MessageUpdateClientTrigger_Item implements IMessage {
    public boolean status, interaction_mode;

    public MessageUpdateClientTrigger_Item() {
    }

    public MessageUpdateClientTrigger_Item(boolean stats, boolean interaction_mode) {
        this.status = stats;
        this.interaction_mode = interaction_mode;
    }

    public MessageUpdateClientTrigger_Item(ITrigger_item stats) {
        this.status = stats.getStatus();
        this.interaction_mode = stats.getInteraction();
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        this.status = buf.readBoolean();
        this.interaction_mode = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(status);
        buf.writeBoolean(interaction_mode);
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
            IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
            EntityPlayerMP playerMP = ctx.getServerHandler().player;
            mainThread.addScheduledTask(() -> {
                DedicatedServerProxy.handleTrigger_Item_Message(message, playerMP);
            });
            return null;
        }
    }
}
