package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import com.redsparkle.foe.capa.level.ILevelCapability;
import com.redsparkle.foe.capa.skills.ISkillsCapability;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 3/1/2017.
 */
public class MessageUpdateClientServerLevel implements IMessage {
    public Integer
            Level,
            Progress;


    public MessageUpdateClientServerLevel() {
    }

    public MessageUpdateClientServerLevel(ILevelCapability level) {

        this.Level=level.getLevel();
        this.Progress=level.getProgress();



    }

    @Override
    public void fromBytes(ByteBuf buf) {
        Level= buf.readInt();
        Progress= buf.readInt();


    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(Level);
        buf.writeInt(Progress);

    }

    public static class HandlerClient implements IMessageHandler<MessageUpdateClientServerLevel, IMessage> {

        @Override
        public IMessage onMessage(MessageUpdateClientServerLevel message, MessageContext ctx) {
            ClientOnlyProxy.handleLevelMessage(message);
            return null;
        }

    }

    public static class HandlerServer implements IMessageHandler<MessageUpdateClientServerLevel, IMessage> {

        @Override
        public IMessage onMessage(MessageUpdateClientServerLevel message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleLevelMessage(message, playerMP);
            return null;
        }

    }
}
