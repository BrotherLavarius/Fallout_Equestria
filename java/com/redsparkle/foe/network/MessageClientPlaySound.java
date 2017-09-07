package com.redsparkle.foe.network;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.Init.SoundInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/13/2017.
 */
public class MessageClientPlaySound implements IMessage {


    String type;
    String position;


    public MessageClientPlaySound() {
    }

    public MessageClientPlaySound(String type, String position) {

        this.type = type;
        this.position = position;
    }


    @Override
    public void fromBytes(ByteBuf buf) {

        this.type = ByteBufUtils.readUTF8String(buf);
        this.position = ByteBufUtils.readUTF8String(buf);

    }

    @Override
    public void toBytes(ByteBuf buf) {

        ByteBufUtils.writeUTF8String(buf, type);
        ByteBufUtils.writeUTF8String(buf, position);

    }

    public static class HandlerClient implements IMessageHandler<MessageClientPlaySound, IMessage> {
        @Override
        public IMessage onMessage(MessageClientPlaySound message, MessageContext ctx) {
            IThreadListener mainThread = Minecraft.getMinecraft();
            EntityPlayer player = Minecraft.getMinecraft().player;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    String whatToPlay = message.type;
                    String position = message.position;
                    // Types of things vary from sound_env_rads to gun_tenmm_fire
                    String[] whatToPlayArray = whatToPlay.split("_");
                    String[] positionArray = position.split(",");
                    GlobalsGunStats gunStats = null;

                    if (whatToPlayArray[0].equalsIgnoreCase("gun")) {
                        if (whatToPlayArray[1].equalsIgnoreCase("main")) {
                            gunStats = ((Item_Firearm) player.getHeldItemMainhand().getItem()).params;
                        }
                        if (whatToPlayArray[1].equalsIgnoreCase("saddlebagLS")) {
                            gunStats = ((Item_SaggleBagGun) player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem()).params;
                        }

                        if (whatToPlayArray[1].equalsIgnoreCase("saddlebagRS")) {
                            gunStats = ((Item_SaggleBagGun) player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem()).params;
                        }
                        if (whatToPlayArray[2].equalsIgnoreCase("fire")) {
                            player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                                    SoundInit.lookup.get(gunStats.getGunName()).get(0), SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                        } else if (whatToPlayArray[2].equalsIgnoreCase("dry")) {
                            player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                                    SoundInit.lookup.get(gunStats.getGunName()).get(1), SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                        } else if (whatToPlayArray[2].equalsIgnoreCase("reload")) {
                            player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                                    SoundInit.lookup.get(gunStats.getGunName()).get(2), SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                        } else if (whatToPlayArray[2].equalsIgnoreCase("clipout")) {
                            player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                                    SoundInit.lookup.get(gunStats.getGunName()).get(3), SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                        }


                        if (whatToPlayArray[1].equalsIgnoreCase("clipReload")) {
                            player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                                    SoundInit.clip_load, SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                        }
                    }


                }
            });
            return null;
        }
    }
}

