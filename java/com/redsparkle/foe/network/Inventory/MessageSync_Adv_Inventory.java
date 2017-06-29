package com.redsparkle.foe.network.Inventory;

import com.lothrazar.powerinventory.util.UtilPlayerInventoryFilestorage;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.network.MessageOpenGuiClient;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by hoijima on 29.06.17.
 */
public class MessageSync_Adv_Inventory implements IMessage {

    public int slot;
    public int playerId;
    public ItemStack itemStack = null;
    public MessageSync_Adv_Inventory() {
    }
    public MessageSync_Adv_Inventory(EntityPlayer player, int slot) {
        this.slot = slot;
        this.itemStack = UtilPlayerInventoryFilestorage.getPlayerInventoryStack(player, slot);
        this.playerId = player.getEntityId();
    }
    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buffer
     */
    @Override
    public void fromBytes(ByteBuf buffer) {
        slot = buffer.readByte();
        playerId = buffer.readInt();
        itemStack = ByteBufUtils.readItemStack(buffer);
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buffer
     */
    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeByte(slot);
        buffer.writeInt(playerId);
        ByteBufUtils.writeItemStack(buffer, itemStack);
    }


    @SideOnly(Side.CLIENT)
    public static class HandlerClient implements IMessageHandler<MessageSync_Adv_Inventory, IMessage> {

        /**
         * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
         * is needed.
         *
         * @param message The message
         * @param ctx
         * @return an optional return message
         */
        @Override
        public IMessage onMessage(MessageSync_Adv_Inventory message, MessageContext ctx) {
            ClientOnlyProxy.handle_Sync_Adv_Inv(message,ctx);
            return null;
        }
    }
}
