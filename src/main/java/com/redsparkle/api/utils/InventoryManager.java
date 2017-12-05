package com.redsparkle.api.utils;

import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;

/**
 * Created by hoijima on 09.01.17.
 */
public class InventoryManager {
    public static int FindEmpty(EntityPlayer player) {
        int errorCode = 99;
        for (int i = 9; i <= player.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (itemstack == ItemStack.EMPTY || itemstack == null || itemstack != player.inventory.getStackInSlot(i)) {
                if (itemstack.getItem().equals(Items.AIR)) {
                    return i;
                }
            }
        }
        return errorCode;
    }

    public static ItemStack findItemOnhotBar(EntityPlayer player, ItemStack stack) {
        Integer[] invArrayOnBar = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        if (stack == (player.getHeldItem(EnumHand.OFF_HAND))) {
            if (player.getHeldItem(EnumHand.OFF_HAND).getItemDamage() <= 12) {
                return player.getHeldItem(EnumHand.OFF_HAND);
            }
        } else if (stack == (player.getHeldItem(EnumHand.MAIN_HAND))) {
            if (player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() <= 12) {
                return player.getHeldItem(EnumHand.MAIN_HAND);
            }
        } else {
            for (int i = 0; i < invArrayOnBar.length; ++i) {
                ItemStack itemstack = player.inventory.getStackInSlot(i);
                if (stack == (itemstack)) {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack findItemOffBar(EntityPlayer player, ItemStack stack) {
        for (int i = 9; i <= player.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (stack == (itemstack)) {
                return itemstack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack AddItemToExistingStack(EntityPlayer player, ItemStack stack) {
        //TODO: kick the Case's to player main inv, not the hotbar
        for (int i = 0; i <= player.inventory.getSizeInventory(); i++) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (itemstack.getItem() == stack.getItem() && itemstack.getCount() <= stack.getMaxStackSize()) {
                return itemstack;
            }
        }
        return ItemStack.EMPTY;
    }


    //-------------------------------------------------------------------------
    public static NonNullList<String> item_id = NonNullList.withSize(12, "item.air");
    public static NonNullList<String> item_count = NonNullList.withSize(12, "0");
    public static NonNullList<String> item_damage = NonNullList.withSize(12, "0");
    public static IAdvInventory iAdvInventory;

    public static ArrayList processorIAdv(IAdvInventory iadvInventory) {
        ArrayList params = new ArrayList();
        iAdvInventory = iadvInventory;
        for (int i = 0; i < 12; i++) {
            String item_name = delegeteName(iAdvInventory.getStackInSlot(i).getItem());
            item_id.set(i, item_name);
            item_count.set(i, Integer.toString(iAdvInventory.getStackInSlot(i).getCount()));
            item_damage.set(i, Integer.toString(iAdvInventory.getStackInSlot(i).getItemDamage()));
        }

        params.add(item_id);
        params.add(item_count);
        params.add(item_damage);
        return params;
    }

    public static String delegeteName(Item item) {
        String name = item.delegate.name().toString();//+":"+item.getUnlocalizedName();
        return name;
    }
}
