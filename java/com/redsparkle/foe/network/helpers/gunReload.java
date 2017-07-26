package com.redsparkle.foe.network.helpers;
import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
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
//TODO: fix reload logic
    //TODO:FIX FLARE SOUND
    public static ItemStack findAmmo(EntityPlayer player, String ammo) {
        Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (isAmmo(itemstack, ammo)) {
                    return itemstack;
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
            if (heldItem.getItem() == GlobalItemArray_For_init.AllInit[22]) {
                clip = GlobalItemArray_For_init.AllInit[17];
                clipIn=8;
                clipOut=9;
                gunName="LaserPistol";
            }
            ItemStack clipstack = new ItemStack(clip);
            IGunInterface igun = heldItem.getCapability(GunFactoryProvider.GUN,null);
            IAmmoInterface iclip = clipstack.getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
            if(igun.clipInserted()){//clip is in
                if (findAmmo(player, gunName) != ItemStack.EMPTY){//if there IS clips available
                    iclip.setAmmo(igun.getAmmo());
                    igun.setAmmo(findAmmo(player, gunName).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null).getAmmo());
                    findAmmo(player, gunName).shrink(1);
                    igun.setClipStatus(true);
                    player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                }
                else if (findAmmo(player, gunName) == ItemStack.EMPTY){//if NO clips available
                    iclip.setAmmo(igun.getAmmo());
                    igun.setAmmo(0);
                    igun.setClipStatus(false);
                    player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipOut), player);
                }
            }else if (!igun.clipInserted()){//clip is out
                if (findAmmo(player, gunName) != ItemStack.EMPTY){//if there IS clips available
                    iclip.setAmmo(igun.getAmmo());
                    igun.setAmmo(findAmmo(player, gunName).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null).getAmmo());
                    findAmmo(player, gunName).shrink(1);
                    igun.setClipStatus(true);
                    main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(clipIn), player);
                }
                else if (findAmmo(player, gunName) == ItemStack.EMPTY){//if NO clips available
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
            if (heldItem.getItem() == GlobalItemArray_For_init.AllInit[24]) {
                gunName = "Shotgun";
                ammoIN = 4;
                ammoOut = 5;
            }
            if (heldItem.getItem() == GlobalItemArray_For_init.AllInit[27]) {
                gunName = "FlareGun";
                ammoIN = 6;
                ammoOut = 7;
            }
            if (maxAmmo > 1) {
                if (igun.getAmmo() < maxAmmo) {
                    if (findAmmo(player, gunName) != ItemStack.EMPTY) {
                        findAmmo(player, gunName).shrink(igun.getMaxAmmo() - igun.getAmmo());
                        igun.addAmmo(igun.getMaxAmmo()- igun.getAmmo());
                        main.simpleNetworkWrapper.sendTo(new MessageGunReloadReply(ammoIN), player);
                    }
                }
            }
            if (igun.getAmmo() == 0) {
                if (findAmmo(player, gunName) != ItemStack.EMPTY) {
                    findAmmo(player, gunName).shrink(maxAmmo);
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
