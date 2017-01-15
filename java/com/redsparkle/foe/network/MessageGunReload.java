package com.redsparkle.foe.network;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.items.guns.TenMM;
import com.redsparkle.foe.items.guns.ammo.TenMMClip;
import com.redsparkle.foe.main;
import com.redsparkle.foe.utils.InventoryManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/13/2017.
 */
public class MessageGunReload implements IMessage {


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageGunReload, IMessage> {

        public Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        @Override
        public IMessage onMessage(MessageGunReload message, MessageContext ctx) {
            final EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            WorldServer mainThread = (WorldServer) (player.world);
            ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
            if (heldItem != null  ) { //&& heldItem.getTagCompound().getBoolean("isgun")
                if (heldItem.getItem() instanceof TenMM) {
                    mainThread.addScheduledTask(new Runnable() {
                        @Override
                        public void run() {
                            if (heldItem.getItemDamage() <= 12){
                                if (findAmmo(player, "TenMM") != ItemStack.EMPTY) {
                                    heldItem.setItemDamage(findAmmo(player, "TenMM").getItemDamage());
                                    findAmmo(player, "TenMM").shrink(1);
                                }else {
                                        Item emptyclip = ItemInit.tenMMClip;
                                        ItemStack emptyClipStack = new ItemStack(emptyclip);
                                        emptyClipStack.setItemDamage(heldItem.getItemDamage());
                                        heldItem.setItemDamage(13);
                                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), emptyClipStack);
                                        //main.simpleNetworkWrapper.sendTo(new MessageGunReloadToClient(0), player);
                                    }
                            }else if (heldItem.getItemDamage() == 13){
                                if (findAmmo(player, "TenMM") != ItemStack.EMPTY) {
                                    heldItem.setItemDamage(findAmmo(player, "TenMM").getItemDamage());
                                    findAmmo(player, "TenMM").shrink(1);
                                    //main.simpleNetworkWrapper.sendTo(new MessageGunReloadToClient(0), player);
                                }
                            }
                        }
                    });
                }

            }
            return null;
        }



        public ItemStack findAmmo(EntityPlayer player, String ammo) {
            for (int i = 0; i < invArray.length; ++i) {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isAmmo(itemstack,ammo)) {
                    if (itemstack.getItemDamage() >= 12) {
                        return ItemStack.EMPTY;
                    } else {
                        return itemstack;
                    }
                }
                // }
            }
            return ItemStack.EMPTY;
        }

        public boolean isAmmo(ItemStack stack,String ammo) {
            if (ammo == "TenMM"){
                return stack.getItem() instanceof TenMMClip;
            }
            return false;

        }
    }
}
