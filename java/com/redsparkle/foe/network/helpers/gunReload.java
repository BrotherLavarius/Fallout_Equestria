package com.redsparkle.foe.network.helpers;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.utils.InventoryManager;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.items.guns.ammo.FlareShell.FlareShell;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMClip;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.ammo.shell.SShell;
import com.redsparkle.foe.items.saddlebags.ammo.Seven_mmAmmo;
import com.redsparkle.foe.items.saddlebags.guns.Seven_mm_rifle;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunReloadReply;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

import java.awt.*;


/**
 * Created by hoijima on 20.01.17.
 */
@SuppressWarnings("Duplicates")
public class gunReload {
//TODO: fix reload logic
    //TODO:FIX FLARE SOUND
public static ItemStack findAmmo(EntityPlayer player, String ammo, boolean saddlegun) {
    if (saddlegun) {
        Integer[] invArray = {8, 9, 10, 1};
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(invArray[i]);
            if (isAmmo(itemstack, ammo)) {
                return itemstack;
            }
        }
    } else {
        Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (isAmmo(itemstack, ammo)) {
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
        if (ammo == "Seven_mm") {
            return stack.getItem() instanceof Seven_mmAmmo;
        }
        return false;
    }

    public static void ClipLoaded(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player, Boolean saddlebag_gun) {
        mainThread.addScheduledTask(() -> {
            Item clip =null;
            int clipOut=0,clipIn = 0;
            String gunName = "";
            ItemStack LS = ItemStack.EMPTY;
            ItemStack RS = ItemStack.EMPTY;
            IGunInterface igun_LS = null;
            IGunInterface igun_RS = null;
            List guns_reload = new List();
            guns_reload.add("false");
            guns_reload.add("false");
            if (saddlebag_gun) {

                if (player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6) != ItemStack.EMPTY) {
                    LS = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6);
                    igun_LS = LS.getCapability(GunFactoryProvider.GUN, null);
                    guns_reload.add("true", 0);

                }
                if (player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7) != ItemStack.EMPTY) {
                    RS = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7);
                    igun_RS = RS.getCapability(GunFactoryProvider.GUN, null);
                    guns_reload.add("true", 1);

                }


                if (LS.getItem() instanceof Seven_mm_rifle) {
                    clip = ItemInit.seven_mmClip;
                    clipIn = 0;
                    clipOut = 1;
                    gunName = "Seven_mm";
                }
                if (RS.getItem() instanceof Seven_mm_rifle) {
                    clip = ItemInit.seven_mmClip;
                    clipIn = 0;
                    clipOut = 1;
                    gunName = "Seven_mm";

                }
                ItemStack clipstack = new ItemStack(clip);
                IAmmoInterface iclip = clipstack.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);

                if (guns_reload.getItem(0) == "true") {
                    if (igun_LS.getAmmo() == 0) {
                        if (igun_LS.clipInserted()) {
                            if (findAmmo(player, gunName, true) != ItemStack.EMPTY) {//if there IS clips available
                                iclip.setAmmo(igun_LS.getAmmo());
                                igun_LS.setAmmo(findAmmo(player, gunName, true).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo());
                                findAmmo(player, gunName, true).shrink(1);
                                igun_LS.setClipStatus(true);
                                player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                                main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                            } else if (findAmmo(player, gunName, true) == ItemStack.EMPTY) {//if NO clips available
                                iclip.setAmmo(igun_LS.getAmmo());
                                igun_LS.setAmmo(0);
                                igun_LS.setClipStatus(false);
                                player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                                main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipOut), player);
                            }
                        } else if (!igun_LS.clipInserted()) {
                            if (findAmmo(player, gunName, true) != ItemStack.EMPTY) {//if there IS clips available
                                iclip.setAmmo(igun_LS.getAmmo());
                                igun_LS.setAmmo(findAmmo(player, gunName, true).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo());
                                findAmmo(player, gunName, true).shrink(1);
                                igun_LS.setClipStatus(true);
                                main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                            } else if (findAmmo(player, gunName, true) == ItemStack.EMPTY) {//if NO clips available
                            }
                        }
                    }
                }
                if (guns_reload.getItem(1) == "true") {
                    if (igun_RS.getAmmo() == 0) {
                        if (igun_RS.clipInserted()) {
                            if (findAmmo(player, gunName, true) != ItemStack.EMPTY) {//if there IS clips available
                                iclip.setAmmo(igun_RS.getAmmo());
                                igun_RS.setAmmo(findAmmo(player, gunName, true).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo());
                                findAmmo(player, gunName, true).shrink(1);
                                igun_RS.setClipStatus(true);
                                player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                                main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                            } else if (findAmmo(player, gunName, true) == ItemStack.EMPTY) {//if NO clips available
                                iclip.setAmmo(igun_RS.getAmmo());
                                igun_RS.setAmmo(0);
                                igun_RS.setClipStatus(false);
                                player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                                main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipOut), player);
                            }
                        }
                    } else if (!igun_RS.clipInserted()) {
                        if (findAmmo(player, gunName, true) != ItemStack.EMPTY) {//if there IS clips available
                            iclip.setAmmo(igun_RS.getAmmo());
                            igun_RS.setAmmo(findAmmo(player, gunName, true).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo());
                            findAmmo(player, gunName, true).shrink(1);
                            igun_RS.setClipStatus(true);
                            main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                        } else if (findAmmo(player, gunName, true) == ItemStack.EMPTY) {//if NO clips available
                        }
                    }
                }
            } else if (!saddlebag_gun) {
                if (heldItem.getItem() == ItemInit.tenMM) {
                    clip = ItemInit.tenMMClip;
                    clipIn = 0;
                    clipOut = 1;
                    gunName = "TenMM";
                }
                if (heldItem.getItem() == ItemInit.fourTenMM) {
                    clip = ItemInit.fourTenMMClip;
                    clipIn = 2;
                    clipOut = 3;
                    gunName = "FourTenMM";
                }
                if (heldItem.getItem() == ItemInit.laserPistol) {
                    clip = ItemInit.battery;
                    clipIn = 8;
                    clipOut = 9;
                    gunName = "LaserPistol";
                }
                ItemStack clipstack = new ItemStack(clip);
                IGunInterface igun = heldItem.getCapability(GunFactoryProvider.GUN, null);
                IAmmoInterface iclip = clipstack.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);
                if (igun.clipInserted()) {//clip is in
                    if (findAmmo(player, gunName, false) != ItemStack.EMPTY) {//if there IS clips available
                        iclip.setAmmo(igun.getAmmo());
                        igun.setAmmo(findAmmo(player, gunName, false).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo());
                        findAmmo(player, gunName, false).shrink(1);
                        igun.setClipStatus(true);
                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                    } else if (findAmmo(player, gunName, false) == ItemStack.EMPTY) {//if NO clips available
                        iclip.setAmmo(igun.getAmmo());
                        igun.setAmmo(0);
                        igun.setClipStatus(false);
                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipOut), player);
                    }
                } else if (!igun.clipInserted()) {//clip is out
                    if (findAmmo(player, gunName, false) != ItemStack.EMPTY) {//if there IS clips available
                        iclip.setAmmo(igun.getAmmo());
                        igun.setAmmo(findAmmo(player, gunName, false).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo());
                        findAmmo(player, gunName, false).shrink(1);
                        igun.setClipStatus(true);
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                    } else if (findAmmo(player, gunName, false) == ItemStack.EMPTY) {//if NO clips available
                    }
                }
            }
        });
    }
    public static void BulletLoaded(WorldServer mainThread, ItemStack heldItem, EntityPlayerMP player) {
        mainThread.addScheduledTask(() -> {
            String gunName = "";
            int ammoIN = 0;
            int ammoOut = 0;
            IGunInterface igun = heldItem.getCapability(GunFactoryProvider.GUN, null);
            int maxAmmo = igun.getMaxAmmo();
            if (heldItem.getItem() == ItemInit.sb_shoutgun) {
                gunName = "Shotgun";
                ammoIN = 4;
                ammoOut = 5;
            }
            if (heldItem.getItem() == ItemInit.flare) {
                gunName = "FlareGun";
                ammoIN = 6;
                ammoOut = 7;
            }
            if (maxAmmo > 1) {
                if (igun.getAmmo() < maxAmmo) {
                    if (findAmmo(player, gunName, false) != ItemStack.EMPTY) {
                        findAmmo(player, gunName, false).shrink(igun.getMaxAmmo() - igun.getAmmo());
                        igun.addAmmo(igun.getMaxAmmo()- igun.getAmmo());
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(ammoIN), player);
                    }
                }
            }
            if (igun.getAmmo() == 0) {
                if (findAmmo(player, gunName, false) != ItemStack.EMPTY) {
                    findAmmo(player, gunName, false).shrink(maxAmmo);
                    igun.setAmmo(igun.getMaxAmmo());
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(ammoOut), player);
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
