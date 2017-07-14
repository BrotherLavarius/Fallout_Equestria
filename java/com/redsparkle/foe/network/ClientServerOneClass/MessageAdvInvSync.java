package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 14.07.17.
 */
public class MessageAdvInvSync implements IMessage {

    public MessageAdvInvSync() {
    }
    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class HandlerServer implements IMessageHandler<MessageAdvInvSync, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInvSync message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;

            DedicatedServerProxy.handleAdvInv(message, playerMP);
            return null;
        }

    }
}
