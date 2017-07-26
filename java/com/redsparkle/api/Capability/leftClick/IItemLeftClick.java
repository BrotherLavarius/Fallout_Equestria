package com.redsparkle.api.Capability.leftClick;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
/**
 * Created by NENYN on 1/5/2017.
 */
public interface IItemLeftClick {
    void onLeftClicked(ItemStack stack, EntityPlayer player, World world);
    void leftClickStart(ItemStack stack, EntityPlayer player, World world);
    void leftClickStop(ItemStack stack, EntityPlayer player, World world);
}
