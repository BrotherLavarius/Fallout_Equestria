package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.utils.InventoryManager;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;

/**
 * Created by hoijima on 18.07.17.
 */
public class MessageAdvInv_SYNC implements IMessage {
    public NonNullList<String> item_id = NonNullList.withSize(12, "item.air");
    public NonNullList<String> item_count = NonNullList.withSize(12, "0");
    public NonNullList<String> item_damage = NonNullList.withSize(12, "0");
    public IAdvInventory iAdvInventory;

    public MessageAdvInv_SYNC() {
    }

    public MessageAdvInv_SYNC(IAdvInventory iAdvInventory) {

        ArrayList export = InventoryManager.processorIAdv(iAdvInventory);
        item_id = (NonNullList<String>) export.get(0);
        item_count = (NonNullList<String>) export.get(1);
        item_damage = (NonNullList<String>) export.get(2);
    }



    @Override
    public void toBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {
            ByteBufUtils.writeUTF8String(buf, new String(item_id.get(i) + "," + item_count.get(i) + "," + item_damage.get(i)));
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
            item_id.set(i, parts[0]);
            item_count.set(i, parts[1]);
            item_damage.set(i, parts[2]);
        }
    }


    public static class HandlerClient implements IMessageHandler<MessageAdvInv_SYNC, IMessage> {
        @Override
        public IMessage onMessage(MessageAdvInv_SYNC message, MessageContext ctx) {
            ClientOnlyProxy.handleAdv_SYNC(message);
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageAdvInv_SYNC, IMessage> {
        @Override
        public IMessage onMessage(MessageAdvInv_SYNC message, MessageContext ctx) {
            DedicatedServerProxy.handleAdv_SYNC(message, ctx);
            return null;
        }
    }
}
