package com.redsparkle.api.items.helpers.guns;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.api.utils.InventoryManager;
import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageClientPlaySound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by hoijima on 02.09.17.
 */
@SuppressWarnings("ALL")
public class Reload {


    public static ItemStack findAmmo(EntityPlayer player, String GunType) {
        Integer[] invArray;

        if (GunType.equalsIgnoreCase("gun_main")) {
            invArray = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
            for (int i = 0; i < invArray.length; ++i) {
                ItemStack gun = player.getHeldItemMainhand();
                ItemStack itemstack = player.inventory.getStackInSlot(invArray[i]);
                if (isAmmo(gun, itemstack)) {
                    return itemstack;
                }
            }

        }
        if (GunType.equalsIgnoreCase("gun_saddlebagLS") || GunType.equalsIgnoreCase("gun_saddlebagRS")) {
            invArray = new Integer[]{8, 9, 10, 11};
            for (int i = 0; i < invArray.length; ++i) {
                ItemStack gun = ItemStack.EMPTY;
                if (GunType.equalsIgnoreCase("gun_saddlebagLS")) {
                    gun = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6);
                }

                if (GunType.equalsIgnoreCase("gun_saddlebagRS")) {
                    gun = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7);
                }
                ItemStack itemstack = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(invArray[i]);
                if (isAmmo(gun, itemstack)) {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY;

    }

    public static boolean isAmmo(ItemStack gun, ItemStack ammo) {
        if (gun.getItem() instanceof Item_Firearm) {
            if (ItemInit.gun_ammo_lookup.get(((Item_Firearm) gun.getItem()).params.getGunName()) == ammo.getItem()) {
                return true;
            }
        }
        if (gun.getItem() instanceof Item_SaggleBagGun) {
            if (ItemInit.gun_ammo_lookup.get(((Item_SaggleBagGun) gun.getItem()).params.getGunName()) == ammo.getItem()) {
                return true;
            }
        }
        return false;
    }

    public static void reload_processor(EntityPlayer player, String gun_to_reload) {
        ItemStack gun = ItemStack.EMPTY;
        GlobalsGunStats gun_params = null;
        IGunInterface igun = null;
        Item clip = Items.AIR;
        ItemStack foundAmmo = ItemStack.EMPTY;
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        int z = player.getPosition().getZ();


        if (gun_to_reload.equalsIgnoreCase("gun_main")) {
            gun = player.getHeldItemMainhand();
            gun_params = ((Item_Firearm) gun.getItem()).params;
        }
        if (gun_to_reload.equalsIgnoreCase("gun_saddlebagLS") || gun_to_reload.equalsIgnoreCase("gun_saddlebagRS")) {
            if (gun_to_reload.equalsIgnoreCase("gun_saddlebagLS")) {
                gun = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6);
            }
            if (gun_to_reload.equalsIgnoreCase("gun_saddlebagRS")) {
                gun = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7);
            }
            gun_params = ((Item_SaggleBagGun) gun.getItem()).params;
        }
        if (gun_params.getLoadType().equalsIgnoreCase("clip")) {


            clip = (Item) ItemInit.gun_ammo_lookup.get(gun_params.getGunName());
            ItemStack clipstack = new ItemStack(clip);

            igun = gun.getCapability(GunFactoryProvider.GUN, null);
            IAmmoInterface iclip = clipstack.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);

            if (igun.clipInserted()) {
                if (igun.getAmmo() >= 0) {
                    if (findAmmo(player, gun_to_reload) != ItemStack.EMPTY) {
                        foundAmmo = findAmmo(player, gun_to_reload);
                        iclip.setAmmo(igun.getAmmo());
                        igun.setAmmo(findAmmo(player, gun_to_reload).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo());
                        foundAmmo.shrink(1);
                        igun.setClipStatus(true);
                        gun.setItemDamage(igun.getMaxAmmo() - iclip.getAmmo());
                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                        SendSoundMessage(player, x, y, z, gun_to_reload + "_reload");
                    } else if (findAmmo(player, gun_to_reload) == ItemStack.EMPTY) {
                        iclip.setAmmo(igun.getAmmo());
                        igun.setAmmo(0);
                        igun.setClipStatus(false);
                        gun.setItemDamage(igun.getMaxAmmo());
                        player.inventory.setInventorySlotContents(InventoryManager.FindEmpty(player), clipstack);
                        SendSoundMessage(player, x, y, z, gun_to_reload + "_clipout");
                    }
                }

            }
            if (!igun.clipInserted()) {
                if (findAmmo(player, gun_to_reload) != ItemStack.EMPTY) {
                    foundAmmo = findAmmo(player, gun_to_reload);
                    iclip.setAmmo(igun.getAmmo());
                    igun.setAmmo(foundAmmo.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo());
                    foundAmmo.shrink(1);
                    igun.setClipStatus(true);
                    gun.setItemDamage(igun.getMaxAmmo() - iclip.getAmmo());
                    SendSoundMessage(player, x, y, z, gun_to_reload + "_reload");
                }
            }


        }

        if (gun_params.getLoadType().equalsIgnoreCase("ammo")) {

            igun = gun.getCapability(GunFactoryProvider.GUN, null);

            if (igun.getMaxAmmo() > 1) {
                if (igun.getAmmo() < igun.getMaxAmmo()) {
                    if (findAmmo(player, gun_to_reload) != ItemStack.EMPTY) {
                        foundAmmo = findAmmo(player, gun_to_reload);
                        foundAmmo.shrink(igun.getMaxAmmo() - igun.getAmmo());
                        igun.addAmmo(igun.getMaxAmmo() - igun.getAmmo());
                        gun.setItemDamage(igun.getMaxAmmo() - igun.getAmmo());
                        SendSoundMessage(player, x, y, z, gun_to_reload + "_reload");
                    }
                }
            }
            if (igun.getAmmo() == 0) {
                if (findAmmo(player, gun_to_reload) != ItemStack.EMPTY) {
                    foundAmmo = findAmmo(player, gun_to_reload);
                    foundAmmo.shrink(igun.getMaxAmmo());
                    igun.setAmmo(igun.getMaxAmmo());
                    gun.setItemDamage(igun.getMaxAmmo() - igun.getAmmo());
                    SendSoundMessage(player, x, y, z, gun_to_reload + "_reload");
                }
            }


        }

        if (gun_params.getLoadType().equalsIgnoreCase("hybrid_ammo")) {

            igun = gun.getCapability(GunFactoryProvider.GUN, null);


            if (findAmmo(player, gun_to_reload) != ItemStack.EMPTY) {
                foundAmmo = findAmmo(player, gun_to_reload);
                if (foundAmmo.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo() >= foundAmmo.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getMaxAmmo()) {

                    igun = gun.getCapability(GunFactoryProvider.GUN, null);

                    if (igun.getMaxAmmo() > 1) {
                        if (igun.getAmmo() < igun.getMaxAmmo()) {
                            if (findAmmo(player, gun_to_reload) != ItemStack.EMPTY) {
                                foundAmmo = findAmmo(player, gun_to_reload);
                                foundAmmo.shrink(igun.getMaxAmmo() - igun.getAmmo());
                                igun.addAmmo(igun.getMaxAmmo() - igun.getAmmo());
                                gun.setItemDamage(igun.getMaxAmmo() - igun.getAmmo());
                                SendSoundMessage(player, x, y, z, gun_to_reload + "_reload");
                            }
                        }
                    }
                    if (igun.getAmmo() == 0) {
                        if (findAmmo(player, gun_to_reload) != ItemStack.EMPTY) {
                            foundAmmo = findAmmo(player, gun_to_reload);
                            foundAmmo.shrink(igun.getMaxAmmo());
                            igun.setAmmo(igun.getMaxAmmo());
                            gun.setItemDamage(igun.getMaxAmmo() - igun.getAmmo());
                            SendSoundMessage(player, x, y, z, gun_to_reload + "_reload");
                        }
                    }


                }
            }

        }
    }
    public static void SendSoundMessage(EntityPlayer playerIn, int x, int y, int z, String type) {
        main.simpleNetworkWrapper.sendToAllAround(new MessageClientPlaySound(type, x + "," + y + "," + z), new NetworkRegistry.TargetPoint(0, (double) x, (double) y, (double) z, 10.0));
        main.simpleNetworkWrapper.sendTo(new MessageClientPlaySound(type, x + "," + y + "," + z), (EntityPlayerMP) playerIn);

    }
}
