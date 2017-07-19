package com.redsparkle.api.capa.Inventory;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageAdvInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;


/**
 * Created by hoijima on 18.07.17.
 */
public class IAdvProvider extends ItemStackHandler implements IAdvInventory, ICapabilitySerializable<NBTTagCompound> {


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
    public void inserProcesser(List<ItemStack> items, EntityPlayer player) {

        for (int i = 0; i < 12; i++) {
            this.setStackInSlot(i, stacks.get(i));
        }
        main.simpleNetworkWrapper.sendTo(new MessageAdvInv(stacks, true), (EntityPlayerMP) player);

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
    public NBTTagCompound serializeNBT() {
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < stacks.size(); i++) {
            if (!stacks.get(i).isEmpty()) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setInteger("Slot", i);
                stacks.get(i).writeToNBT(itemTag);
                nbtTagList.appendTag(itemTag);
            }
        }
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("Items", nbtTagList);
        nbt.setInteger("Size", stacks.size());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setSize(nbt.hasKey("Size", Constants.NBT.TAG_INT) ? nbt.getInteger("Size") : stacks.size());
        NBTTagList tagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound itemTags = tagList.getCompoundTagAt(i);
            int slot = itemTags.getInteger("Slot");

            if (slot >= 0 && slot < stacks.size()) {
                stacks.set(slot, new ItemStack(itemTags));
            }
        }
    }
}
