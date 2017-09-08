package com.redsparkle.foe.network;

import com.redsparkle.api.items.helpers.guns.Reload;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/13/2017.
 */
public class MessageGunReload implements IMessage {


    String type;

    public MessageGunReload() {
    }

    public MessageGunReload(String type) {
        this.type = type;
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = ByteBufUtils.readUTF8String(buf);
    }
    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, type);
    }

    public static class HandlerServer implements IMessageHandler<MessageGunReload, IMessage> {
        @Override
        public IMessage onMessage(MessageGunReload message, MessageContext ctx) {

            EntityPlayerMP player = ctx.getServerHandler().player;
            IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    Reload.reload_processor(player,message.type);
                }
            });
            return null;
        }

        }
    }

