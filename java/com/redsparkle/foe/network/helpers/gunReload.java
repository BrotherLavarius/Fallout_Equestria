package com.redsparkle.foe.network.helpers;

import com.redsparkle.api.inventory.GlobalsGunStats;
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

    public static void TenMM(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(() -> {

            Item clip = GlobalItemArray_For_init.AllInit[16];
            ItemStack clipstack = new ItemStack(clip);
            if (heldItem.getItemDamage() <= GlobalsGunStats.TEN_MM.NearEmpty()) {
                if (findAmmo(player, "TenMM") != ItemStack.EMPTY) {
                    clipstack.setItemDamage(heldItem.getItemDamage());
                    heldItem.setItemDamage(findAmmo(player, "TenMM").getItemDamage());
                    findAmmo(player, "TenMM").shrink(1);
                    player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(0), player);
                } else {
                    Return_Empty_Clip(player, clip, heldItem, GlobalsGunStats.TEN_MM.Empty());
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(1), player);
                }
            } else if (heldItem.getItemDamage() == GlobalsGunStats.TEN_MM.Empty()) {
                if (findAmmo(player, "TenMM") != ItemStack.EMPTY) {
                    heldItem.setItemDamage(findAmmo(player, "TenMM").getItemDamage());
                    findAmmo(player, "TenMM").shrink(1);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(0), player);

                }
            }
        });

    }

    public static void FourTenMM(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(() -> {
            Item clip = GlobalItemArray_For_init.AllInit[19];
            ItemStack clipstack = new ItemStack(clip);
            if (heldItem.getItemDamage() <= GlobalsGunStats.FOUR_TEN_MM.NearEmpty()) {
                if (findAmmo(player, "FourTenMM") != ItemStack.EMPTY) {
                    clipstack.setItemDamage(heldItem.getItemDamage());
                    heldItem.setItemDamage(findAmmo(player, "FourTenMM").getItemDamage());
                    findAmmo(player, "FourTenMM").shrink(1);
                    player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(2), player);
                } else {
                    Return_Empty_Clip(player, clip, heldItem, GlobalsGunStats.FOUR_TEN_MM.Empty());
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(3), player);
                }
                if (heldItem.getItemDamage() == GlobalsGunStats.FOUR_TEN_MM.Empty()) {
                    if (findAmmo(player, "FourTenMM") != ItemStack.EMPTY) {
                        heldItem.setItemDamage(findAmmo(player, "FourTenMM").getItemDamage());
                        findAmmo(player, "FourTenMM").shrink(1);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(2), player);

                    }
                }


            }
        });
    }

    public static void LaserPistol(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(() -> {

            Item emptyclip = GlobalItemArray_For_init.AllInit[17];
            ItemStack clipstack = new ItemStack(GlobalItemArray_For_init.AllInit[17]);

            if (heldItem.getItemDamage() <= GlobalsGunStats.LASER_PISTOL.NearEmpty()) {
                if (findAmmo(player, "LaserPistol") != ItemStack.EMPTY) {
                    clipstack.setItemDamage(heldItem.getItemDamage());
                    heldItem.setItemDamage(findAmmo(player, "LaserPistol").getItemDamage());
                    findAmmo(player, "LaserPistol").shrink(1);
                    player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(8), player);
                } else {
                    Return_Empty_Clip(player, emptyclip, heldItem, GlobalsGunStats.LASER_PISTOL.Empty());
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(9), player);
                }
            }
            if (heldItem.getItemDamage() == GlobalsGunStats.LASER_PISTOL.Empty()) {
                if (findAmmo(player, "LaserPistol") != ItemStack.EMPTY) {
                    heldItem.setItemDamage(findAmmo(player, "LaserPistol").getItemDamage());
                    findAmmo(player, "LaserPistol").shrink(1);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(8), player);
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


    private static void Return_Empty_Clip(EntityPlayerMP player, Item emptyclip, ItemStack heldItem, int empty) {
        ItemStack emptyClipStack = new ItemStack(emptyclip);
        emptyClipStack.setItemDamage(heldItem.getItemDamage());
        heldItem.setItemDamage(empty);
        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), emptyClipStack);

    }
}


