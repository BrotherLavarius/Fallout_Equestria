package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.capa.StatsCapa.AddInvCapabilityProvider;
import com.redsparkle.api.capa.StatsCapa.IAddInvCapability;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 14.07.17.
 */
public class MessageAdvInvToServerSync implements IMessage {



    public String[] item_id = new String[12];
    public int[] item_count = new int[12];
    public int[] item_damage = new int[12];
    public int index;
    public IAddInvCapability iAdvInv;
    public MessageAdvInvToServerSync() {}

    public MessageAdvInvToServerSync(EntityPlayer player) {
        this.iAdvInv = player.getCapability(AddInvCapabilityProvider.STATS_CAPA,null);
        this.item_id = new String[]{
                iAdvInv.getPipBuckSlot().getDisplayName(),
                iAdvInv.getDeviceSlot1().getDisplayName(),
                iAdvInv.getDeviceSlot2().getDisplayName(),
                iAdvInv.getDeviceSlot3().getDisplayName(),
                iAdvInv.getDeviceSlot4().getDisplayName(),
                iAdvInv.getHarnessSlot().getDisplayName(),
                iAdvInv.getGunSlot1().getDisplayName(),
                iAdvInv.getGunSlot2().getDisplayName(),
                iAdvInv.getAmmoSlot1().getDisplayName(),
                iAdvInv.getAmmoSlot2().getDisplayName(),
                iAdvInv.getAmmoSlot3().getDisplayName(),
                iAdvInv.getAmmoSlot4().getDisplayName()
        };
        this.item_count = new int[]{
                iAdvInv.getPipBuckSlot().getCount(),
                iAdvInv.getDeviceSlot1().getCount(),
                iAdvInv.getDeviceSlot2().getCount(),
                iAdvInv.getDeviceSlot3().getCount(),
                iAdvInv.getDeviceSlot4().getCount(),
                iAdvInv.getHarnessSlot().getCount(),
                iAdvInv.getGunSlot1().getCount(),
                iAdvInv.getGunSlot2().getCount(),
                iAdvInv.getAmmoSlot1().getCount(),
                iAdvInv.getAmmoSlot2().getCount(),
                iAdvInv.getAmmoSlot3().getCount(),
                iAdvInv.getAmmoSlot4().getCount()
        };
        this.item_damage = new int[]{
                iAdvInv.getPipBuckSlot().getItemDamage(),
                iAdvInv.getDeviceSlot1().getItemDamage(),
                iAdvInv.getDeviceSlot2().getItemDamage(),
                iAdvInv.getDeviceSlot3().getItemDamage(),
                iAdvInv.getDeviceSlot4().getItemDamage(),
                iAdvInv.getHarnessSlot().getItemDamage(),
                iAdvInv.getGunSlot1().getItemDamage(),
                iAdvInv.getGunSlot2().getItemDamage(),
                iAdvInv.getAmmoSlot1().getItemDamage(),
                iAdvInv.getAmmoSlot2().getItemDamage(),
                iAdvInv.getAmmoSlot3().getItemDamage(),
                iAdvInv.getAmmoSlot4().getItemDamage()
        };

    }

//TODO: we need to send indexing data about leght of thebytebuder


    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {
        String message = new String(item_id[i]+","+item_count[i]+","+item_damage[i]);
        ByteBufUtils.writeUTF8String(buf,message);

        }
    }
    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {

            String recivedmessage = ByteBufUtils.readUTF8String(buf);
            String[] parts = recivedmessage.split(",");
            item_id[i] = parts[0];
            item_count[i] = Integer.parseInt(parts[1]);
            item_damage[i] = Integer.parseInt(parts[2]);

        }
    }


    public static class HandlerServer implements IMessageHandler<MessageAdvInvToServerSync, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInvToServerSync message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;

            DedicatedServerProxy.handleAdvInvFromCLient(message, playerMP);
            return null;
        }
    }
}
