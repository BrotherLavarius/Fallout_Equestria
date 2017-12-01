package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 19.05.17.
 */
public class MessageOpenGuiClient implements IMessage {
    public Integer ID;

    public MessageOpenGuiClient() {
    }

    public MessageOpenGuiClient(
            Integer id
    ) {
        this.ID = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        ID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(ID);
    }

    public static class HandlerClient implements IMessageHandler<MessageOpenGuiClient, IMessage> {
        @Override
        public IMessage onMessage(MessageOpenGuiClient message, MessageContext ctx) {
            IThreadListener mainThread = Minecraft.getMinecraft();
            EntityPlayer player = Minecraft.getMinecraft().player;
            ClientOnlyProxy.handleOpenGui(message, player, mainThread);
            return null;
        }
    }
    public static class HandleServer implements IMessageHandler<MessageOpenGuiClient, IMessage> {
        @Override
        public IMessage onMessage(MessageOpenGuiClient message, MessageContext ctx) {
            IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
            EntityPlayer player = ctx.getServerHandler().player;
            DedicatedServerProxy.handleOpenGuiMessage(message, player, mainThread);
            return null;
        }
    }
}
