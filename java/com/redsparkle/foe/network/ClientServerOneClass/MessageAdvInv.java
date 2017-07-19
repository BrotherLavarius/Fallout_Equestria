package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.capa.Inventory.IAdvInventory;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 18.07.17.
 */
public class MessageAdvInv implements IMessage {

    public NonNullList<ItemStack> itemArray = NonNullList.withSize(12, ItemStack.EMPTY);
    public NonNullList<String> item_id = NonNullList.withSize(12, "item.air");
    public NonNullList<Integer> item_count = NonNullList.withSize(12, 0);
    public NonNullList<Integer> item_damage = NonNullList.withSize(12, 0);
    public boolean sync;

    public IAdvInventory advInventory;

    public MessageAdvInv() {
    }

    public MessageAdvInv(IAdvInventory addinv, boolean sync) {
        this.advInventory = addinv;

        this.sync = sync;
        if (sync) {
            for (int i = 0; i < 12; i++) {
                item_id.set(i, addinv.getStackInSlot(i).getUnlocalizedName());
                item_count.set(i, addinv.getStackInSlot(i).getCount());
                item_damage.set(i, addinv.getStackInSlot(i).getItemDamage());
            }
        }
    }

    public MessageAdvInv(NonNullList<ItemStack> stacks, boolean sync) {
        this.sync = sync;
        if (sync) {
            for (int i = 0; i < 12; i++) {
                item_id.set(i, stacks.get(i).getUnlocalizedName());
                item_count.set(i, stacks.get(i).getCount());
                item_damage.set(i, stacks.get(i).getItemDamage());
            }
        }
    }


    @Override
    public void toBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {
            String message = new String(item_id.get(i) + "," + item_count.get(i) + "," + item_damage.get(i));
            ByteBufUtils.writeUTF8String(buf, message);

        }
        buf.writeBoolean(sync);
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
        sync = buf.readBoolean();
    }

    public static class HandlerClient implements IMessageHandler<MessageAdvInv, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInv message, MessageContext ctx) {
            ClientOnlyProxy.handleAdv(message);
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageAdvInv, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInv message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            if (message.sync) {
                DedicatedServerProxy.handleAdv(message, playerMP);
            } else {
                DedicatedServerProxy.handleAdv_requestSync(playerMP);
            }
            return null;
        }
    }


}
