package com.redsparkle.foe.network.helpers;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.api.utils.GlobalItemArray_For_init;
import com.redsparkle.api.utils.InventoryManager;
import com.redsparkle.foe.items.guns.ammo.FlareShell.FlareShell;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMClip;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.ammo.shell.SShell;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunReloadReply;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

/**
 * Created by hoijima on 20.01.17.
 */
public class gunReload {

    public static ItemStack findAmmo(EntityPlayer player, String ammo) {
        Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);

            if (isAmmo(itemstack, ammo)) {
                if (itemstack.getItemDamage() >= 12) {
                    return ItemStack.EMPTY;
                } else {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY;
    }


    public static boolean isAmmo(ItemStack stack, String ammo) {
        if (ammo == "TenMM") {
            return stack.getItem() instanceof TenMMClip;
        }
        if (ammo == "LaserPistol") {
            return stack.getItem() instanceof Battery;
        }
        if (ammo == "FourTenMM") {
            return stack.getItem() instanceof FourTenMMClip;
        }
        if (ammo == "Shotgun") {
            return stack.getItem() instanceof SShell;
        }
        if (ammo == "FlareGun") {
            return stack.getItem() instanceof FlareShell;
        }
        return false;

    }

    public static void ClipLoaded(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(() -> {
            Item clip =null;
            int clipOut=0,clipIn = 0;
            String gunName = "";
            if(heldItem.getItem() == GlobalItemArray_For_init.AllInit[21]){
                clip = GlobalItemArray_For_init.AllInit[16];
                clipIn=0;
                clipOut=1;
                gunName="TenMM";
            }
            if(heldItem.getItem() == GlobalItemArray_For_init.AllInit[23]){
                clip = GlobalItemArray_For_init.AllInit[19];
                clipIn=2;
                clipOut=3;
                gunName="FourTenMM";
            }
            if(heldItem.getItem() == GlobalItemArray_For_init.AllInit[23]) {
                clip = GlobalItemArray_For_init.AllInit[17];
                clipIn=8;
                clipOut=9;
                gunName="LaserPistol";
            }

            ItemStack clipstack = new ItemStack(clip);
            IGunInterface igun = heldItem.getCapability(GunFactoryProvider.GUN,null);
            IAmmoInterface iclip = clipstack.getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
            if (igun.getAmmo() >= 0) {
                if (findAmmo(player, gunName) != ItemStack.EMPTY) {
                    iclip.setAmmo(igun.getAmmo());
                    igun.setAmmo(findAmmo(player, gunName).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null).getAmmo());
                    findAmmo(player, gunName).shrink(1);
                    player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                } else {
                    Return_Empty_Clip(player, clip, heldItem);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipOut), player);
                }
            } else if (igun.getAmmo() == 0) {
                if (findAmmo(player, gunName) != ItemStack.EMPTY) {
                    igun.setAmmo(findAmmo(player, gunName).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null).getAmmo());
                    findAmmo(player, gunName).shrink(1);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);

                }
            }
        });

    }





    public static void Shotgun(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(() -> {
            if (heldItem.getItemDamage() == GlobalsGunStats.DB_SHOUTGUN.Empty()) {
                if (findAmmo(player, "Shotgun") != ItemStack.EMPTY) {
                    heldItem.setItemDamage(0);
                    findAmmo(player, "Shotgun").shrink(2);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(4), player);
                }
            } else if (heldItem.getItemDamage() == GlobalsGunStats.DB_SHOUTGUN.NearEmpty()) {
                if (findAmmo(player, "Shotgun") != ItemStack.EMPTY) {
                    heldItem.setItemDamage(heldItem.getItemDamage() - 1);
                    findAmmo(player, "Shotgun").shrink(1);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(5), player);
                }
            }
        });
    }

    public static void FlareGun(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(() -> {
            if (heldItem.getItemDamage() == 0) {
                Item removeFlare = GlobalItemArray_For_init.AllInit[26];
                ItemStack flareStack = new ItemStack(removeFlare);
                heldItem.setItemDamage(1);
                player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), flareStack);
                main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(7), player);
            } else if (heldItem.getItemDamage() == 1) {
                if (findAmmo(player, "FlareGun") != ItemStack.EMPTY) {
                    heldItem.setItemDamage(heldItem.getItemDamage() - 1);
                    findAmmo(player, "FlareGun").shrink(1);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(6), player);
                }
            }
        });
    }


    private static void Return_Empty_Clip(EntityPlayerMP player, Item emptyclip, ItemStack heldItem) {
        ItemStack emptyClipStack = new ItemStack(emptyclip);
        emptyClipStack.getCapability(AmmoFactoryProvider.AMMO_STORAGE,null).setAmmo(0);
        heldItem.getCapability(GunFactoryProvider.GUN,null).setAmmo(0);
        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), emptyClipStack);

    }
}


