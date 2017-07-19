package com.redsparkle.api.capa.Inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

/**
 * Created by hoijima on 18.07.17.
 */
public interface IAdvInventory extends IItemHandler {
    void inserProcesser(List<String> item_id, List<Integer> item_count, List<Integer> item_damage, EntityPlayerMP playerMP);

    void updateClient(EntityPlayer player);
}
