package com.redsparkle.api.Capability.Player.Inventory;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageAdvInv_SYNC;
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

//    @Override
//    public void inserProcesser(List<String> item_id, List<Integer> item_count, List<Integer> item_damage, EntityPlayerMP player) {
//
//        for (int i = 0; i < 12; i++) {
//            Item item = null;
//            ItemStack quack= null;
//            item = ItemCatalog.Request(item_id.get(i));
//            quack = ItemCatalog.RequestStack(item, item_count.get(i), item_damage.get(i));
//            this.setStackInSlot(i,quack);
//        }
//
//
//    }


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
        main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC(stacks), (EntityPlayerMP) player);
    }
    }



