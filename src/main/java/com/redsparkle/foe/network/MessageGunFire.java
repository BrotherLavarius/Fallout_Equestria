package com.redsparkle.foe.network;

import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.items.helpers.guns.GunFire;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
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
            EntityPlayerMP player = ctx.getServerHandler().player;
            IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {



                        if (message.type == 0) {
                            if(!player.isCreative() && player.getHeldItemMainhand().getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0){
                                player.getHeldItemMainhand().getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                                player.getHeldItemMainhand().setItemDamage(player.getHeldItemMainhand().getItemDamage() + 1);
                                GunFire.GunFire(player.world, player, message.type);
                            }
                            else if(player.isCreative()){
                                GunFire.GunFire(player.world, player, message.type);
                            }

                        }

                        if (message.type >= 10 & message.type <= 29) {


                            if (message.type == 10) {
                                if (!player.isCreative() &&player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0) {
                                    player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                                    player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).setItemDamage(player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItemDamage() + 1);
                                    GunFire.GunFire(player.world, player, message.type);
                                }
                                else if(player.isCreative()){
                                    GunFire.GunFire(player.world, player, message.type);
                                }
                            }

                            if (message.type == 20) {
                                if (!player.isCreative() &&player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0) {
                                    player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                                    player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).setItemDamage(player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItemDamage() +1);
                                    GunFire.GunFire(player.world, player, message.type);
                                }
                                else if(player.isCreative()){
                                    GunFire.GunFire(player.world, player, message.type);
                                }
                            }
                        }


                }
            });
            return null;
        }
    }
    public static class HandlerClient implements IMessageHandler<MessageGunFire, IMessage> {
        @Override
        public IMessage onMessage(MessageGunFire message, MessageContext ctx) {
            IThreadListener mainThread = Minecraft.getMinecraft();
            EntityPlayer player = Minecraft.getMinecraft().player;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                        GunFire.GunFire(player.world, player, message.type);


                }
            });
            return null;
        }
    }

}
