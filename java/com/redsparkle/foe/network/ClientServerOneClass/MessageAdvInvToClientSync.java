package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;

/**
 * Created by hoijima on 14.07.17.
 */
public class MessageAdvInvToClientSync implements IMessage {



    public String[] item_id = new String[12];
    public int[] item_count = new int[12];
    public int[] item_damage = new int[12];
    public int index;
    public MessageAdvInvToClientSync() {}

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



    public static class HandlerClient implements IMessageHandler<MessageAdvInvToClientSync, IMessage> {
        @Override
        public IMessage onMessage(MessageAdvInvToClientSync message, MessageContext ctx) {
            ClientOnlyProxy.handleAdvInv(message, ctx);
            return null;
        }

    }

}
