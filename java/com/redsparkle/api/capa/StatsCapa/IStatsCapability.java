package com.redsparkle.api.capa.StatsCapa;

import com.redsparkle.foe.inventory.Additional_Inventory;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 07.07.17.
 */
public interface IStatsCapability {


    Additional_Inventory getAdditional_Inventory();

    void setAdditional_Inventory(Additional_Inventory additional_inventory);

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

    ItemStack getLastDeviceSlot1();

    ////////////////////////
    void setLastDeviceSlot1(ItemStack lastdeviceSlot1);

    ItemStack getLastDeviceSlot2();

    void setLastDeviceSlot2(ItemStack lastdeviceSlot2);

    ItemStack getLastDeviceSlot3();

    void setLastDeviceSlot3(ItemStack lastdeviceSlot3);

    ItemStack getLastDeviceSlot4();

    void setLastDeviceSlot4(ItemStack lastdeviceSlot4);

    ItemStack getLastPipBuckSlot();

    void setLastPipBuckSlot(ItemStack lastpipBuckSlot);

    ItemStack getLastAmmoSlot1();

    void setLastAmmoSlot1(ItemStack lastammoSlot1);

    ItemStack getLastAmmoSlot2();

    void setLastAmmoSlot2(ItemStack lastammoSlot2);

    ItemStack getLastAmmoSlot3();

    void setLastAmmoSlot3(ItemStack lastammoSlot3);

    ItemStack getLastAmmoSlot4();

    void setLastAmmoSlot4(ItemStack lastammoSlot4);

    ItemStack getLastGunSlot1();

    void setLastGunSlot1(ItemStack lastgunSlot1);

    ItemStack getLastGunSlot2();

    void setLastGunSlot2(ItemStack lastgunSlot2);

    ItemStack getLastHarnessSlot();

    void setLastHarnessSlot(ItemStack lastharnessSlot);

}
