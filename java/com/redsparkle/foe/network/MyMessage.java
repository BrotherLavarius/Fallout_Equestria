package com.redsparkle.foe.network;

import com.redsparkle.foe.Init.FOECapabilitiesInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 04.12.2016.
 */
public class MyMessage implements IMessage {

        private String text;

        public MyMessage() { }

        public MyMessage(String text) {
            this.text = text;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
        }

        @Override
        public void toBytes(ByteBuf buf) {
            ByteBufUtils.writeUTF8String(buf, text);
        }

        public static class MyMessageHandler implements IMessageHandler<MyMessage, IMessage> {

            // or in 1.8:
            @Override
            public IMessage onMessage(MyMessage message, MessageContext ctx) {
                IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj; // or Minecraft.getMinecraft() on the client
                mainThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));
                    }
                });
                return null; // no response in this case
            }
        }
}
