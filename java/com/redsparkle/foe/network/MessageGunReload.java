package com.redsparkle.foe.network;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.items.guns.TenMM;
import com.redsparkle.foe.items.guns.ammo.TenMMClip;
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
            EntityPlayer playerSP = Minecraft.getMinecraft().player;
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            WorldServer world = (WorldServer) player.world;
            ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
            world.addScheduledTask(() -> {
                ItemStack stack = player.getHeldItemMainhand();
                if (stack != null && heldItem.getTagCompound().getBoolean("isgun")) {
                    if ( heldItem.getItem() instanceof TenMM){

                        if(findAmmo(player,"TenMM") != ItemStack.EMPTY){
                            world.playSound(playerSP, player.getPosition(), SoundInit.tenMMReload, SoundCategory.HOSTILE, 1.0F, 1.0F);
                            Item emptyclip = ItemInit.tenMMClip;
                            ItemStack emptyClipStack = new ItemStack(emptyclip);
                            emptyClipStack.setItemDamage(heldItem.getItemDamage());

                            heldItem.setItemDamage(findAmmo(player,"TenMM").getItemDamage());
                            findAmmo(player,"TenMM").shrink(1);

                            player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), emptyClipStack);
                        }else{
                            if (heldItem.getItemDamage() == 12) {
                                world.playSound(playerSP, player.getPosition(), SoundInit.tenMMReload, SoundCategory.HOSTILE, 1.0F, 1.0F);
                                Item emptyclip = ItemInit.tenMMClip;
                                ItemStack emptyClipStack = new ItemStack(emptyclip);
                                emptyClipStack.setItemDamage(12);

                                heldItem.setItemDamage(13);
                                player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), emptyClipStack);
                            }
                        }

                    }



                }
            });
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
