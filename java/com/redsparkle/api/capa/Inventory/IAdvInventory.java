package com.redsparkle.api.capa.Inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

/**
 * Created by hoijima on 18.07.17.
 */
public interface IAdvInventory extends IItemHandler {
    void inserProcesser(List<ItemStack> items, EntityPlayer player);
}
