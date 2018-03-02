package com.redsparkle.api.Capability.Player.Inventory;

import com.google.gson.JsonObject;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageAdvInv_SYNC;
import com.redsparkle.foe.network.UnifiedMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.ItemStackHandler;

import java.io.Serializable;

/**
 * Created by hoijima on 18.07.17.
 */
public class IAdvProvider extends ItemStackHandler implements IAdvInventory, ICapabilitySerializable<NBTTagCompound>, Serializable {
    @CapabilityInject(IAdvInventory.class)
    public static Capability<IAdvInventory> Adv_Inv = null;

    public IAdvProvider() {
        this(12);
    }

    public IAdvProvider(int size) {
        stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public IAdvProvider(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    public static IAdvInventory instanceFor(EntityPlayer player) {
        return player.getCapability(Adv_Inv, null);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == Adv_Inv;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == Adv_Inv ? (T) this : null;
    }

    @Override
    public void updateClient(EntityPlayer player) {

        JsonObject message = new JsonObject();
        JsonObject body = new JsonObject();

        message.addProperty("type", "sync_adv_inv");

        for (int i = 0; i < this.getSlots(); i++) {
            JsonObject slot = new JsonObject();
            slot.addProperty("name", this.getStackInSlot(i).getItem().delegate.name().toString());
            slot.addProperty("count", this.getStackInSlot(i).getCount());
            slot.addProperty("damage", this.getStackInSlot(i).getItemDamage());
            body.add("slot_" + i, slot);
        }
        message.add("details", body);

        main.simpleNetworkWrapper.sendTo(new UnifiedMessage(message), (EntityPlayerMP) player);
        main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC(this), (EntityPlayerMP) player);
    }
}
