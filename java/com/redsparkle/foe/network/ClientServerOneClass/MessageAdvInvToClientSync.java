package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.ByteBuffer;

/**
 * Created by hoijima on 14.07.17.
 */
public class MessageAdvInvToClientSync implements IMessage {

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

    public String[] item_id = new String[12];
    public int[] item_count = new int[12];
    public int[] item_damage = new int[12];
    public int index;

    public MessageAdvInvToClientSync(String[] id, int[] count, int[] damage) {
        /**
         * @param id.length
         * @param item_id.length
         * @param count.length
         * @param item_count.length
         * @param damage.length
         * @param item_damage.length
         */
        for (int i = 0; i < 12; i++) {
            int g = id.length;
            this.item_id[i] = id[i];
            this.item_count[i] = count[i];
            this.item_damage[i] = damage[i];
        }
    }
//TODO: we need to send indexing data about leght of thebytebuder

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {

            item_count[i] = buf.readInt();
            item_damage[i] = buf.readInt();
            item_id[i] = buf.toString();
        }
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {
            String itemname = item_id[i];
            byte[] item = itemname.getBytes();
            ByteBuffer item_id_buf = ByteBuffer.wrap(item);
            buf.writeInt(item_count[i]);
            buf.writeInt(item_damage[i]);
            buf.writeBytes(item_id_buf);
        }
    }

    public static class HandlerClient implements IMessageHandler<MessageAdvInvToClientSync, IMessage> {
        @Override
        public IMessage onMessage(MessageAdvInvToClientSync message, MessageContext ctx) {
            ClientOnlyProxy.handleAdvInv(message, ctx);
            return null;
        }

    }

}
