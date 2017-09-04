package com.redsparkle.foe.network;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/**
 * Created by NENYN on 1/13/2017.
 */
public class MessageGunReload implements IMessage {


    int type;

    public MessageGunReload() {
    }

    public MessageGunReload(int type) {
        this.type = type;
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
    }

    public static class HandlerServer implements IMessageHandler<MessageGunReload, IMessage> {
        @Override
        public IMessage onMessage(MessageGunReload message, MessageContext ctx) {
            final EntityPlayerMP player = ctx.getServerHandler().player;
            DedicatedServerProxy.handleReloadMessage(message, player);
            return null;
        }
    }
}
