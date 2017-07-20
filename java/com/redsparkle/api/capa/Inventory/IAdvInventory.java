package com.redsparkle.api.capa.Inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by hoijima on 18.07.17.
 */
public interface IAdvInventory extends IItemHandler {

    void updateClient(EntityPlayer player);
}
