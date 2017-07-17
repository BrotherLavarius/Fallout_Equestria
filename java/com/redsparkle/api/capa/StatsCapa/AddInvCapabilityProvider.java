package com.redsparkle.api.capa.StatsCapa;

import com.redsparkle.foe.inventory.AddInv_impl;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageAdvInvToClientSync;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by hoijima on 07.07.17.
 */
public class AddInvCapabilityProvider implements IAddInvCapability, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(IAddInvCapability.class)
    public static Capability<IAddInvCapability> STATS_CAPA = null;
    public ItemStack DeviceSlot1,
            DeviceSlot2,
            DeviceSlot3,
            DeviceSlot4,
            PipBuckSlot,
            AmmoSlot1,
            AmmoSlot2,
            AmmoSlot3,
            AmmoSlot4,
            GunSlot1,
            GunSlot2,
            HarnessSlot;
    private boolean dirty = true;

    public AddInvCapabilityProvider() {
    }


    public static IAddInvCapability instanceFor(EntityPlayer player) {
        return player.getCapability(STATS_CAPA, null);
    }

    @Override
    public void newPLayerInit() {
        this.DeviceSlot1 = ItemStack.EMPTY;
        this.DeviceSlot2 = ItemStack.EMPTY;
        this.DeviceSlot3 = ItemStack.EMPTY;
        this.DeviceSlot4 = ItemStack.EMPTY;
        this.PipBuckSlot = ItemStack.EMPTY;
        this.AmmoSlot1 = ItemStack.EMPTY;
        this.AmmoSlot2 = ItemStack.EMPTY;
        this.AmmoSlot3 = ItemStack.EMPTY;
        this.AmmoSlot4 = ItemStack.EMPTY;
        this.GunSlot1 = ItemStack.EMPTY;
        this.GunSlot2 = ItemStack.EMPTY;
        this.HarnessSlot = ItemStack.EMPTY;
    }


    @Override
    public AddInv_impl getInventory() {
        AddInv_impl inventory = new AddInv_impl();

        if (DeviceSlot1 == null) {
            newPLayerInit();
        }

        inventory.setInventorySlotContents(0, getPipBuckSlot());
        inventory.setInventorySlotContents(1, getDeviceSlot1());
        inventory.setInventorySlotContents(2, getDeviceSlot2());
        inventory.setInventorySlotContents(3, getDeviceSlot3());
        inventory.setInventorySlotContents(4, getDeviceSlot4());
        inventory.setInventorySlotContents(5, getHarnessSlot());
        inventory.setInventorySlotContents(6, getGunSlot1());
        inventory.setInventorySlotContents(7, getGunSlot2());
        inventory.setInventorySlotContents(8, getAmmoSlot1());
        inventory.setInventorySlotContents(9, getAmmoSlot2());
        inventory.setInventorySlotContents(10, getAmmoSlot3());
        inventory.setInventorySlotContents(11, getAmmoSlot4());

        if (DeviceSlot1 == null) {
            newPLayerInit();
        }

        return inventory;
    }

    @Override
    public ItemStack getDeviceSlot1() {
        return DeviceSlot1;
    }

    @Override
    public void setDeviceSlot1(ItemStack deviceSlot1) {
        DeviceSlot1 = deviceSlot1;
    }

    @Override
    public ItemStack getDeviceSlot2() {
        return DeviceSlot2;
    }

    @Override
    public void setDeviceSlot2(ItemStack deviceSlot2) {
        DeviceSlot2 = deviceSlot2;
    }

    @Override
    public ItemStack getDeviceSlot3() {
        return DeviceSlot3;
    }

    @Override
    public void setDeviceSlot3(ItemStack deviceSlot3) {
        DeviceSlot3 = deviceSlot3;
    }

    @Override
    public ItemStack getDeviceSlot4() {
        return DeviceSlot4;
    }

    @Override
    public void setDeviceSlot4(ItemStack deviceSlot4) {
        DeviceSlot4 = deviceSlot4;
    }

    @Override
    public ItemStack getPipBuckSlot() {
        return PipBuckSlot;
    }

    @Override
    public void setPipBuckSlot(ItemStack pipBuckSlot) {
        PipBuckSlot = pipBuckSlot;
    }

    @Override
    public ItemStack getAmmoSlot1() {
        return AmmoSlot1;
    }

    @Override
    public void setAmmoSlot1(ItemStack ammoSlot1) {
        AmmoSlot1 = ammoSlot1;
    }

    @Override
    public ItemStack getAmmoSlot2() {
        return AmmoSlot2;
    }

    @Override
    public void setAmmoSlot2(ItemStack ammoSlot2) {
        AmmoSlot2 = ammoSlot2;
    }

    @Override
    public ItemStack getAmmoSlot3() {
        return AmmoSlot3;
    }

    @Override
    public void setAmmoSlot3(ItemStack ammoSlot3) {
        AmmoSlot3 = ammoSlot3;
    }

    @Override
    public ItemStack getAmmoSlot4() {
        return AmmoSlot4;
    }

    @Override
    public void setAmmoSlot4(ItemStack ammoSlot4) {
        AmmoSlot4 = ammoSlot4;
    }

    @Override
    public ItemStack getGunSlot1() {
        return GunSlot1;
    }

    @Override
    public void setGunSlot1(ItemStack gunSlot1) {
        GunSlot1 = gunSlot1;
    }

    @Override
    public ItemStack getGunSlot2() {
        return GunSlot2;
    }

    @Override
    public void setGunSlot2(ItemStack gunSlot2) {
        GunSlot2 = gunSlot2;
    }

    @Override
    public ItemStack getHarnessSlot() {
        return HarnessSlot;
    }

    @Override
    public void setHarnessSlot(ItemStack harnessSlot) {
        HarnessSlot = harnessSlot;
    }

    @Override
    public void updateClient(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            if (dirty) main.simpleNetworkWrapper.sendTo(new MessageAdvInvToClientSync(this), (EntityPlayerMP) player);
        }
    }

    @Override
    public void updateServer(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            if (dirty)
                main.simpleNetworkWrapper.sendToServer(new MessageAdvInvToClientSync(this), (EntityPlayerMP) player);
        }
    }


    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }


    @Override
    public NBTTagCompound serializeNBT() {
        int i = 0;

        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagCompound tags = new NBTTagCompound();

        ItemStack buf = ItemStack.EMPTY;
        nbt.setTag("Slot_" + i, tags);
        if (getPipBuckSlot() == null) {
            buf.writeToNBT(tags);
        } else {
            getPipBuckSlot().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getDeviceSlot1() == null) {
            buf.writeToNBT(tags);
        } else {
            getDeviceSlot1().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getDeviceSlot2() == null) {
            buf.writeToNBT(tags);
        } else {
            getDeviceSlot2().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getDeviceSlot3() == null) {
            buf.writeToNBT(tags);
        } else {
            getDeviceSlot3().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getDeviceSlot4() == null) {
            buf.writeToNBT(tags);
        } else {
            getDeviceSlot4().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getHarnessSlot() == null) {
            buf.writeToNBT(tags);
        } else {
            getHarnessSlot().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getGunSlot1() == null) {
            buf.writeToNBT(tags);
        } else {
            getGunSlot1().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getGunSlot2() == null) {
            buf.writeToNBT(tags);
        } else {
            getGunSlot2().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getAmmoSlot1() == null) {
            buf.writeToNBT(tags);
        } else {
            getAmmoSlot1().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getAmmoSlot2() == null) {
            buf.writeToNBT(tags);
        } else {
            getAmmoSlot2().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getAmmoSlot3() == null) {
            buf.writeToNBT(tags);
        } else {
            getAmmoSlot3().writeToNBT(tags);
        }

        nbt.setTag("Slot_" + i++, tags);
        if (getAmmoSlot4() == null) {
            buf.writeToNBT(tags);
        } else {
            getAmmoSlot4().writeToNBT(tags);
        }

        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

        for(int i=0;i <12;i++) {
            ItemStack stack;
            Item item;
            NBTTagCompound tag = nbt.getCompoundTag("Slot_" + i);
            item = Item.getByNameOrId(tag.getString("id"));
            stack = new ItemStack(item);
            stack.setCount(tag.getByte("Count"));
            stack.setItemDamage(tag.getShort("Damage"));
            if (i == 0) {
                setPipBuckSlot(stack);
            }
            if (i == 1) {
                setDeviceSlot1(stack);
            }
            if (i == 2) {
                setDeviceSlot2(stack);
            }
            if (i == 3) {
                setDeviceSlot3(stack);
            }
            if (i == 4) {
                setDeviceSlot4(stack);
            }
            if (i == 5) {
                setHarnessSlot(stack);
            }
            if (i == 6) {
                setGunSlot1(stack);
            }
            if (i == 7) {
                setGunSlot2(stack);
            }
            if (i == 8) {
                setAmmoSlot1(stack);
            }
            if (i == 9) {
                setAmmoSlot2(stack);
            }
            if (i == 10) {
                setAmmoSlot3(stack);
            }
            if (i == 11) {
                setAmmoSlot4(stack);
            }

        }
    }


    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == STATS_CAPA;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == STATS_CAPA ? (T) this : null;
    }



}
