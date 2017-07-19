package com.redsparkle.api.capa.Inventory;

import com.redsparkle.api.utils.ItemCatalog;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageAdvInv;
import com.sun.org.apache.xml.internal.resolver.Catalog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
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
    public void inserProcesser(String[] item_id, int[] item_count, int[] item_damage, EntityPlayerMP player) {

        for (int i = 0; i < 12; i++) {
            Item item = null;
            ItemStack quack= null;
            item = ItemCatalog.Request(item_id[i]);
            quack = ItemCatalog.RequestStack(item,item_count[i],item_damage[i]);
            this.setStackInSlot(i,quack);
        }

        main.simpleNetworkWrapper.sendTo(new MessageAdvInv(stacks, true), player);

    }
    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack)
    {
//        validateSlotIndex(slot);
        if (ItemStack.areItemStacksEqual(this.stacks.get(slot), stack))
            return;
        this.stacks.set(slot, stack);
//        onContentsChanged(slot);
    }

    @Override
    public int getSlots()
    {
        return stacks.size();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot)
    {
        validateSlotIndex(slot);
        return this.stacks.get(slot);
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate)
        {
            if (existing.isEmpty())
            {
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
    }

    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract)
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemStack.EMPTY);
                onContentsChanged(slot);
            }
            return existing;
        }
        else
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                onContentsChanged(slot);
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    @Override

    protected int getStackLimit(int slot, @Nonnull ItemStack stack)
    {
        return Math.min(getSlotLimit(slot), stack.getMaxStackSize());
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

//    @Override
//    public NBTTagCompound serializeNBT() {
//
//        NBTTagCompound nbt = new NBTTagCompound();
//        NBTTagCompound tags = new NBTTagCompound();
//
//
//        for(int i=0; i < stacks.size();i++){
//            nbt.setTag("Slot_" + i, tags);
//
//            getStackInSlot(i).writeToNBT(tags);
//        }
//
//
//        return nbt;
//    }
//
//    @Override
//    public void deserializeNBT(NBTTagCompound nbt) {
//
//        for(int i=0;i <12;i++) {
//            ItemStack stack;
//            Item item;
//            NBTTagCompound tag = nbt.getCompoundTag("Slot_" + i);
//            item = ItemCatalog.Request(tag.getString("id"));
//            stack = new ItemStack(item);
//            stack.setCount(tag.getByte("Count"));
//            stack.setItemDamage(tag.getShort("Damage"));
//            this.setStackInSlot(i,stack);
//
//        }
//    }
}
