package com.redsparkle.api.capa.StatsCapa;

import com.redsparkle.foe.inventory.AddInv_impl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 07.07.17.
 */
public interface IAddInvCapability {


    void newPLayerInit();

    AddInv_impl getInventory();

    ItemStack getDeviceSlot1();

    void setDeviceSlot1(ItemStack deviceSlot1);

    ItemStack getDeviceSlot2();

    void setDeviceSlot2(ItemStack deviceSlot2);

    ItemStack getDeviceSlot3();

    void setDeviceSlot3(ItemStack deviceSlot3);

    ItemStack getDeviceSlot4();

    void setDeviceSlot4(ItemStack deviceSlot4);

    ItemStack getPipBuckSlot();

    void setPipBuckSlot(ItemStack pipBuckSlot);

    ItemStack getAmmoSlot1();

    void setAmmoSlot1(ItemStack ammoSlot1);

    ItemStack getAmmoSlot2();

    void setAmmoSlot2(ItemStack ammoSlot2);

    ItemStack getAmmoSlot3();

    void setAmmoSlot3(ItemStack ammoSlot3);

    ItemStack getAmmoSlot4();

    void setAmmoSlot4(ItemStack ammoSlot4);

    ItemStack getGunSlot1();

    void setGunSlot1(ItemStack gunSlot1);

    ItemStack getGunSlot2();

    void setGunSlot2(ItemStack gunSlot2);

    ItemStack getHarnessSlot();

    void setHarnessSlot(ItemStack harnessSlot);

    void updateClient(EntityPlayer player);

    void updateServer(EntityPlayer player);
}
