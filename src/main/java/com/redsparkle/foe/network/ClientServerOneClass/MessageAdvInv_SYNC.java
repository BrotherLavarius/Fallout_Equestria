package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 18.07.17.
 */
public class MessageAdvInv_SYNC implements IMessage {
    public NonNullList<String> item_id = NonNullList.withSize(12, "item.air");
    public NonNullList<Integer> item_count = NonNullList.withSize(12, 0);
    public NonNullList<Integer> item_damage = NonNullList.withSize(12, 0);
    public IAdvInventory iAdvInventory;

    public MessageAdvInv_SYNC() {
    }

    public MessageAdvInv_SYNC(NonNullList<ItemStack> stacks) {

        for (int i = 0; i < 12; i++) {
            String item_name = delegeteName(stacks.get(i).getItem());
            item_id.set(i, item_name);
            item_count.set(i, stacks.get(i).getCount());
            item_damage.set(i, stacks.get(i).getItemDamage());
        }
    }

    public MessageAdvInv_SYNC(IAdvInventory iAdvInventory) {

        this.iAdvInventory = iAdvInventory;
        for (int i = 0; i < 12; i++) {
            String item_name = delegeteName(iAdvInventory.getStackInSlot(i).getItem());
            item_id.set(i, item_name);
            item_count.set(i, iAdvInventory.getStackInSlot(i).getCount());
            item_damage.set(i, iAdvInventory.getStackInSlot(i).getItemDamage());
        }
    }

    public String delegeteName(Item item) {
        String name = item.delegate.name().toString();//+":"+item.getUnlocalizedName();
        return name;
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
            item_count.set(i, Integer.parseInt(parts[1]));
            item_damage.set(i, Integer.parseInt(parts[2]));
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
