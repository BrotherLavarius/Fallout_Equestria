package com.redsparkle.api.capa.Inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

/**
 * Created by hoijima on 18.07.17.
 */
public interface IAdvInventory extends IItemHandler {
    void inserProcesser(String[] item_id, int[] item_count, int[] item_damage, EntityPlayerMP playerMP);
}
