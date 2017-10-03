package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 19.05.17.
 */
public class MessageUpdateSLSServerReplyOnDemand implements IMessage {
    public Integer
            Level,
            Progress;

    public MessageUpdateSLSServerReplyOnDemand() {
    }

    public MessageUpdateSLSServerReplyOnDemand(
            Integer level,
            Integer progress
    ) {
        this.Level = level;
        this.Progress = progress;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        Level = buf.readInt();
        Progress = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(Level);
        buf.writeInt(Progress);
    }

    public static class HandlerClient implements IMessageHandler<MessageUpdateSLSServerReplyOnDemand, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateSLSServerReplyOnDemand message, MessageContext ctx) {
            ClientOnlyProxy.handleLevelMessageOnDemand(message);
            return null;
        }
    }
}
