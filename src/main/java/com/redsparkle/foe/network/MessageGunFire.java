package com.redsparkle.foe.network;

import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.items.helpers.guns.GunFire;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by NENYN on 1/17/2017.
 *///Spawn entity and shit
public class MessageGunFire implements IMessage {
    public int type = 0;
    public MessageGunFire(){}
    public MessageGunFire(String type){
        if (type == "gun_main") {
            this.type = 0;
        }

        if (type == "gun_saddlebagLS") {
            this.type = 10;
        }

        if (type == "gun_saddlebagRS") {
            this.type = 20;
        }



    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = buf.readInt();
    }

    public static class HandlerServer implements IMessageHandler<MessageGunFire, IMessage> {
        @Override
        public IMessage onMessage(MessageGunFire message, MessageContext ctx) {
            System.out.println("Recived Fire bullet request");
            DedicatedServerProxy.MessageGunFire_handler(message,ctx);
            return null;
        }
    }
    public static class HandlerClient implements IMessageHandler<MessageGunFire, IMessage> {
        @Override
        public IMessage onMessage(MessageGunFire message, MessageContext ctx) {
            System.out.println("Recived Render bullet request");
            ClientOnlyProxy.MessageGunFire_hadnler(message,ctx);
            return null;
        }
    }

}
