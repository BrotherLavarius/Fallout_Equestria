package com.redsparkle.foe.network;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/**
 * Created by hoijima on 19.05.17.
 */
public class MessageUpdateSLSClientOnDemand implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {
    }
    @Override
    public void toBytes(ByteBuf buf) {
    }
    public static class serverSideHandler implements IMessageHandler<MessageUpdateSLSClientOnDemand, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateSLSClientOnDemand message, MessageContext ctx) {
            final EntityPlayerMP player = ctx.getServerHandler().player;
            DedicatedServerProxy.handleSLSOnDemand(player);
            return null;
        }
    }
}
