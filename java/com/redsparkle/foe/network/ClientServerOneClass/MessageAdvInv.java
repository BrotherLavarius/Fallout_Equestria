package com.redsparkle.foe.network.ClientServerOneClass;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/**
 * Created by hoijima on 18.07.17.
 */
public class MessageAdvInv implements IMessage {
    public int type = 0;
    public MessageAdvInv() {
    }
    public MessageAdvInv(String type) {
        if (type == "sync") {
            this.type = 0;
        }
        if (type == "sync_and_gui") {
            this.type = 1;
        }
        if (type == "close") {
            this.type = 2;
        }
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
    }
    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        type = buf.readInt();
    }
    public static class HandlerServer implements IMessageHandler<MessageAdvInv, IMessage> {
        @Override
        public IMessage onMessage(MessageAdvInv message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().player;
            DedicatedServerProxy.handleAdv(message, playerMP);
            return null;
        }
    }
}
