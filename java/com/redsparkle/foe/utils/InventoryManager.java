package com.redsparkle.foe.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;


/**
 * Created by hoijima on 09.01.17.
 */
public class InventoryManager {
    public static int FindEmpty(EntityPlayer player) {
        int errorCode = 99;
        for (int i = 9; i <= player.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);

            if (itemstack == ItemStack.EMPTY || itemstack == null || itemstack != player.inventory.getStackInSlot(i)) {
                if (itemstack.getItem() == Item.getByNameOrId("minecraft:air")) {
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
}
