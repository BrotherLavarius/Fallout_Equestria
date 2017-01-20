package com.redsparkle.foe.network.helpers;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunReloadReply;
import com.redsparkle.foe.utils.InventoryManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

/**
 * Created by hoijima on 20.01.17.
 */
public class gunReload {
    
    
    public static void TenMM(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                if (heldItem.getItemDamage() <= 12){
                    if (findAmmo(player, "TenMM") != ItemStack.EMPTY) {
                        Item clip = ItemInit.tenMMClip;
                        ItemStack clipstack = new ItemStack(clip);
                        clipstack.setItemDamage(heldItem.getItemDamage());
                        heldItem.setItemDamage(findAmmo(player, "TenMM").getItemDamage());
                        findAmmo(player, "TenMM").shrink(1);
                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(0),player);
                    }else {
                        Item emptyclip = ItemInit.tenMMClip;
                        ItemStack emptyClipStack = new ItemStack(emptyclip);
                        if(heldItem.getItemDamage() == 0){emptyClipStack.setItemDamage(1);}else{emptyClipStack.setItemDamage(heldItem.getItemDamage());}
                        heldItem.setItemDamage(13);
                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), emptyClipStack);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(1),player);
                        //CommonProxy.sendSound(0,player);
                    }
                }else if (heldItem.getItemDamage() == 13){
                    if (findAmmo(player, "TenMM") != ItemStack.EMPTY) {
                        heldItem.setItemDamage(findAmmo(player, "TenMM").getItemDamage());
                        findAmmo(player, "TenMM").shrink(1);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(0),player);

                    }
                }
            }
        });
        
    }
    public static ItemStack findAmmo(EntityPlayer player, String ammo) {
        Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);

            if (isAmmo(itemstack,ammo)) {
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

    public static boolean isAmmo(ItemStack stack, String ammo) {
        if (ammo == "TenMM"){
            return stack.getItem() instanceof TenMMClip;
        }
        if (ammo == "LaserPistol"){
            return stack.getItem() instanceof Battery;
        }
        return false;

    }

    public static void LaserPistol(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                if (heldItem.getItemDamage() <= 30){
                    if (findAmmo(player, "LaserPistol") != ItemStack.EMPTY) {
                        Item clip = ItemInit.battery;
                        ItemStack clipstack = new ItemStack(clip);
                        clipstack.setItemDamage(heldItem.getItemDamage());
                        heldItem.setItemDamage(findAmmo(player, "LaserPistol").getItemDamage());
                        findAmmo(player, "LaserPistol").shrink(1);
                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(1),player);
                    }else {
                        Item emptyclip = ItemInit.battery;
                        ItemStack emptyClipStack = new ItemStack(emptyclip);
                        if(heldItem.getItemDamage() == 0){emptyClipStack.setItemDamage(1);}else{emptyClipStack.setItemDamage(heldItem.getItemDamage());}
                        heldItem.setItemDamage(31);
                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), emptyClipStack);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(1),player);

                    }
                }else if (heldItem.getItemDamage() == 31){
                    if (findAmmo(player, "LaserPistol") != ItemStack.EMPTY) {
                        heldItem.setItemDamage(findAmmo(player, "LaserPistol").getItemDamage());
                        findAmmo(player, "LaserPistol").shrink(1);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(1),player);

                    }
                }
            }
        });
    }
}

