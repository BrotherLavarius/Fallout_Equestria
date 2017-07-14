package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.ByteBuffer;

/**
 * Created by hoijima on 14.07.17.
 */
public class MessageAdvInvSync implements IMessage {

//
//    public String PipBuckSlot_id,
//            DeviceSlot1_id,
//            DeviceSlot2_id,
//            DeviceSlot3_id,
//            DeviceSlot4_id,
//            HarnessSlot_id,
//            GunSlot1_id,
//            GunSlot2_id,
//            AmmoSlot1_id,
//            AmmoSlot2_id,
//            AmmoSlot3_id,
//            AmmoSlot4_id;
//
//    public int PipBuckSlot_intCount,
//            DeviceSlot1_intCount,
//            DeviceSlot2_intCount,
//            DeviceSlot3_intCount,
//            DeviceSlot4_intCount,
//            HarnessSlot_intCount,
//            GunSlot1_intCount,
//            GunSlot2_intCount,
//            AmmoSlot1_intCount,
//            AmmoSlot2_intCount,
//            AmmoSlot3_intCount,
//            AmmoSlot4_intCount;
//
//    public int PipBuckSlot_intDamage,
//            DeviceSlot1_intDamage,
//            DeviceSlot2_intDamage,
//            DeviceSlot3_intDamage,
//            DeviceSlot4_intDamage,
//            HarnessSlot_intDamage,
//            GunSlot1_intDamage,
//            GunSlot2_intDamage,
//            AmmoSlot1_intDamage,
//            AmmoSlot2_intDamage,
//            AmmoSlot3_intDamage,
//            AmmoSlot4_intDamage;

    public String[] item_id = new String[11];
    public int[] item_count = new int[11];
    public int[] item_damage= new int[11];

    public MessageAdvInvSync(String[] id,int[] count,int[] damage){

        for(int i = 0;i < 12;i++){
            item_id[i] = id[i];
            item_count[i]= count[i];
            item_damage[i]= damage[i];
        }
    }
    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        for(int i = 0;i < 12;i++){


            item_id[i] = buf.toString();
            item_count[i]= buf.readInt();
            item_damage[i]= buf.readInt();
        }
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf) {
        for(int i = 0;i < 12;i++){

            ByteBuffer item_id_buf = ByteBuffer.wrap(item_id[i].getBytes());
            buf.writeBytes(item_id_buf);
            buf.writeInt(item_count[i]);
            buf.writeInt(item_damage[i]);
        }
    }
    public static class HandlerClient implements IMessageHandler<MessageAdvInvSync, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInvSync message, MessageContext ctx) {
            ClientOnlyProxy.handleAdvInv(message,ctx);
            return null;
        }

    }
    public static class HandlerServer implements IMessageHandler<MessageAdvInvSync, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInvSync message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleAdvInv(playerMP);
            return null;
        }

    }
}
