package com.redsparkle.api.capa.StatsCapa;

import com.redsparkle.foe.inventory.Additional_Inventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by hoijima on 07.07.17.
 */
public class StatsCapabilityProvider implements IStatsCapability, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(IStatsCapability.class)
    public static Capability<IStatsCapability> STATS_CAPA = null;
    public Additional_Inventory additional_inventory = new Additional_Inventory();
    public NonNullList<ItemStack> stacks = NonNullList.withSize(2, ItemStack.EMPTY);
    public ItemStack DeviceSlot1,
            lastDeviceSlot1,
            DeviceSlot2,
            lastDeviceSlot2,
            DeviceSlot3,
            lastDeviceSlot3,
            DeviceSlot4,
            lastDeviceSlot4,
            PipBuckSlot,
            lastPipBuckSlot,
            AmmoSlot1,
            lastAmmoSlot1,
            AmmoSlot2,
            lastAmmoSlot2,
            AmmoSlot3,
            lastAmmoSlot3,
            AmmoSlot4,
            lastAmmoSlot4,
            GunSlot1,
            lastGunSlot1,
            GunSlot2,
            lastGunSlot2,
            HarnessSlot,
            lastHarnessSlot;
    private boolean dirty = true;

    public StatsCapabilityProvider() {
        this(
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY);
    }

    public StatsCapabilityProvider(
            ItemStack DeviceSlot1,
            ItemStack lastDeviceSlot1,
            ItemStack DeviceSlot2,
            ItemStack lastDeviceSlot2,
            ItemStack DeviceSlot3,
            ItemStack lastDeviceSlot3,
            ItemStack DeviceSlot4,
            ItemStack lastDeviceSlot4,
            ItemStack PipBuckSlot,
            ItemStack lastPipBuckSlot,
            ItemStack AmmoSlot1,
            ItemStack lastAmmoSlot1,
            ItemStack AmmoSlot2,
            ItemStack lastAmmoSlot2,
            ItemStack AmmoSlot3,
            ItemStack lastAmmoSlot3,
            ItemStack AmmoSlot4,
            ItemStack lastAmmoSlot4,
            ItemStack GunSlot1,
            ItemStack lastGunSlot1,
            ItemStack GunSlot2,
            ItemStack lastGunSlot2,
            ItemStack HarnessSlot,
            ItemStack lastHarnessSlot) {

        this.DeviceSlot1 = DeviceSlot1;
        this.lastDeviceSlot1 = lastDeviceSlot1;
        this.DeviceSlot2 = DeviceSlot2;
        this.lastDeviceSlot2 = lastDeviceSlot2;
        this.DeviceSlot3 = DeviceSlot3;
        this.lastDeviceSlot3 = lastDeviceSlot3;
        this.DeviceSlot4 = DeviceSlot4;
        this.lastDeviceSlot4 = lastDeviceSlot4;
        this.PipBuckSlot = PipBuckSlot;
        this.lastPipBuckSlot = lastPipBuckSlot;
        this.AmmoSlot1 = AmmoSlot1;
        this.lastAmmoSlot1 = lastAmmoSlot1;
        this.AmmoSlot2 = AmmoSlot2;
        this.lastAmmoSlot2 = lastAmmoSlot2;
        this.AmmoSlot3 = AmmoSlot3;
        this.lastAmmoSlot3 = lastAmmoSlot3;
        this.AmmoSlot4 = AmmoSlot4;
        this.lastAmmoSlot4 = lastAmmoSlot4;
        this.GunSlot1 = GunSlot1;
        this.lastGunSlot1 = lastGunSlot1;
        this.GunSlot2 = GunSlot2;
        this.lastGunSlot2 = lastGunSlot2;
        this.HarnessSlot = HarnessSlot;
        this.lastHarnessSlot = lastHarnessSlot;

    }


    public static IStatsCapability instanceFor(EntityPlayer player) {
        return player.getCapability(STATS_CAPA, null);
    }


    @Override
    public Additional_Inventory getAdditional_Inventory() {
        return additional_inventory;
    }

    @Override
    public void setAdditional_Inventory(Additional_Inventory additional_inventory) {
        this.additional_inventory = additional_inventory;
    }

    @Override
    public ItemStack getDeviceSlot1() {
        return DeviceSlot1;
    }

    @Override
    public void setDeviceSlot1(ItemStack deviceSlot1) {
        this.DeviceSlot1 = deviceSlot1;
    }

    @Override
    public ItemStack getDeviceSlot2() {
        return DeviceSlot2;
    }

    @Override
    public void setDeviceSlot2(ItemStack deviceSlot2) {
        this.DeviceSlot2 = deviceSlot2;
    }

    @Override
    public ItemStack getDeviceSlot3() {
        return DeviceSlot3;
    }

    @Override
    public void setDeviceSlot3(ItemStack deviceSlot3) {
        this.DeviceSlot1 = deviceSlot3;
    }

    @Override
    public ItemStack getDeviceSlot4() {
        return DeviceSlot4;
    }

    @Override
    public void setDeviceSlot4(ItemStack deviceSlot4) {
        this.DeviceSlot4 = deviceSlot4;
    }

    @Override
    public ItemStack getPipBuckSlot() {
        return PipBuckSlot;
    }

    @Override
    public void setPipBuckSlot(ItemStack pipBuckSlot) {
        this.PipBuckSlot = pipBuckSlot;
    }

    @Override
    public ItemStack getAmmoSlot1() {
        return AmmoSlot1;
    }

    @Override
    public void setAmmoSlot1(ItemStack ammoSlot1) {
        this.AmmoSlot1 = ammoSlot1;
    }

    @Override
    public ItemStack getAmmoSlot2() {
        return AmmoSlot2;
    }

    @Override
    public void setAmmoSlot2(ItemStack ammoSlot2) {
        this.AmmoSlot2 = ammoSlot2;
    }

    @Override
    public ItemStack getAmmoSlot3() {
        return AmmoSlot3;
    }

    @Override
    public void setAmmoSlot3(ItemStack ammoSlot3) {
        this.AmmoSlot1 = ammoSlot3;
    }

    @Override
    public ItemStack getAmmoSlot4() {
        return AmmoSlot4;
    }

    @Override
    public void setAmmoSlot4(ItemStack ammoSlot4) {
        this.AmmoSlot4 = ammoSlot4;
    }

    @Override
    public ItemStack getGunSlot1() {
        return GunSlot1;
    }

    @Override
    public void setGunSlot1(ItemStack gunSlot1) {
        this.GunSlot1 = gunSlot1;
    }

    @Override
    public ItemStack getGunSlot2() {
        return GunSlot2;
    }

    @Override
    public void setGunSlot2(ItemStack gunSlot2) {
        this.GunSlot2 = gunSlot2;
    }

    @Override
    public ItemStack getHarnessSlot() {
        return HarnessSlot;
    }

    @Override
    public void setHarnessSlot(ItemStack harnessSlot) {
        this.HarnessSlot = harnessSlot;
    }

    @Override
    public ItemStack getLastDeviceSlot1() {
        return lastDeviceSlot1;
    }

    ////////////////////////
    @Override
    public void setLastDeviceSlot1(ItemStack lastdeviceSlot1) {
        this.lastDeviceSlot1 = lastdeviceSlot1;
    }

    @Override
    public ItemStack getLastDeviceSlot2() {
        return lastDeviceSlot2;
    }

    @Override
    public void setLastDeviceSlot2(ItemStack lastdeviceSlot2) {
        this.lastDeviceSlot2 = lastdeviceSlot2;
    }

    @Override
    public ItemStack getLastDeviceSlot3() {
        return lastDeviceSlot3;
    }

    @Override
    public void setLastDeviceSlot3(ItemStack lastdeviceSlot3) {
        this.lastDeviceSlot1 = lastdeviceSlot3;
    }

    @Override
    public ItemStack getLastDeviceSlot4() {
        return lastDeviceSlot4;
    }

    @Override
    public void setLastDeviceSlot4(ItemStack lastdeviceSlot4) {
        this.lastDeviceSlot4 = lastdeviceSlot4;
    }

    @Override
    public ItemStack getLastPipBuckSlot() {
        return lastPipBuckSlot;
    }

    @Override
    public void setLastPipBuckSlot(ItemStack lastpipBuckSlot) {
        this.lastPipBuckSlot = lastpipBuckSlot;
    }

    @Override
    public ItemStack getLastAmmoSlot1() {
        return lastAmmoSlot1;
    }

    @Override
    public void setLastAmmoSlot1(ItemStack lastammoSlot1) {
        this.lastAmmoSlot1 = lastammoSlot1;
    }

    @Override
    public ItemStack getLastAmmoSlot2() {
        return lastAmmoSlot2;
    }

    @Override
    public void setLastAmmoSlot2(ItemStack lastammoSlot2) {
        this.lastAmmoSlot2 = lastammoSlot2;
    }

    @Override
    public ItemStack getLastAmmoSlot3() {
        return lastAmmoSlot3;
    }

    @Override
    public void setLastAmmoSlot3(ItemStack lastammoSlot3) {
        this.lastAmmoSlot1 = lastammoSlot3;
    }

    @Override
    public ItemStack getLastAmmoSlot4() {
        return lastAmmoSlot4;
    }

    @Override
    public void setLastAmmoSlot4(ItemStack lastammoSlot4) {
        this.lastAmmoSlot4 = lastammoSlot4;
    }

    @Override
    public ItemStack getLastGunSlot1() {
        return lastGunSlot1;
    }

    @Override
    public void setLastGunSlot1(ItemStack lastgunSlot1) {
        this.lastGunSlot1 = lastgunSlot1;
    }

    @Override
    public ItemStack getLastGunSlot2() {
        return lastGunSlot2;
    }

    @Override
    public void setLastGunSlot2(ItemStack lastgunSlot2) {
        this.lastGunSlot2 = lastgunSlot2;
    }

    @Override
    public ItemStack getLastHarnessSlot() {
        return lastHarnessSlot;
    }

    @Override
    public void setLastHarnessSlot(ItemStack lastharnessSlot) {
        this.lastHarnessSlot = lastharnessSlot;
    }

    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("AdditionalInventoryFOE", this.additional_inventory.writeToNBT(new NBTTagList()));
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.stacks.size(); ++i) {
            ItemStack itemstack = this.stacks.get(i);

            if (!itemstack.isEmpty()) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                itemstack.writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        try {
            NBTTagList nbttaglist = nbt.getTagList("Inventory", 10);
            this.additional_inventory.readFromNBTOld(nbttaglist);

            if (nbt.hasKey("AdditionalInventoryFOE")) {
                this.additional_inventory.readFromNBT(nbt.getTagList("AdditionalInventoryFOE", 10));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == STATS_CAPA;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == STATS_CAPA ? (T) this : null;
    }


}
