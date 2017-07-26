package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
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
public class MessageAdvInv_SLOT implements IMessage {

    public NonNullList<String> item_id = NonNullList.withSize(12, "item.air");
    public NonNullList<Integer> item_count = NonNullList.withSize(12, 0);
    public NonNullList<Integer> item_damage = NonNullList.withSize(12, 0);
    public int type = 0;
    public int slot = 0;
    public IAdvInventory iAdvInventory;

    public MessageAdvInv_SLOT() {
    }

    //Client to Server SLOT SYNC
    public MessageAdvInv_SLOT(int slot, ItemStack stack, int type) {
        this.slot = slot;
        this.type = type;
        String item_name = delegeteName(stack.getItem());
        item_id.set(slot, item_name);
        item_count.set(slot, stack.getCount());
        item_damage.set(slot, stack.getItemDamage());
    }


    public String delegeteName(Item item) {
        String name = item.delegate.name().toString();//+":"+item.getUnlocalizedName();
        return name;
    }


    @Override
    public void toBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {
            String message = new String(item_id.get(i) + "," + item_count.get(i) + "," + item_damage.get(i));
            ByteBufUtils.writeUTF8String(buf, message);

        }
        buf.writeInt(type);
        buf.writeInt(slot);
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
        type = buf.readInt();
        slot = buf.readInt();

    }

    public static class HandlerServer implements IMessageHandler<MessageAdvInv_SLOT, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInv_SLOT message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleAdv_SLOT(message, playerMP);
            return null;
        }
    }


}
