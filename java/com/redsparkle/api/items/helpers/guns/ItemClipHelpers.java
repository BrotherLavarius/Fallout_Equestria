package com.redsparkle.api.items.helpers.guns;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.Init.SoundInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
/**
 * Created by hoijima on 23.06.17.
 */
public class ItemClipHelpers {
    public static ItemStack FindAmmo(EntityPlayer player,ItemStack clip) {
        Item ammoToFind = (Item)ItemInit.Clip_ammo_lookup.get(clip.getItem());
        for (int slot = 0; slot < player.inventory.getSizeInventory(); ++slot)
            if (player.inventory.getStackInSlot(slot).getItem() == ammoToFind )
                return player.inventory.getStackInSlot(slot);
        return ItemStack.EMPTY;
    }


    public static ItemStack Cliphelper(ItemStack clip, World worldIn, EntityPlayer playerIn, int MaxDamage) {
        IAmmoInterface capa = clip.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);
        if (capa.getAmmo() <= capa.getMaxAmmo()) {
            ItemStack ammo = ItemClipHelpers.FindAmmo(playerIn,clip);
            triggers(clip, ammo, worldIn, playerIn);
        }
        return clip;
    }


    public static ItemStack triggers(ItemStack clip, ItemStack ammo, World worldIn, EntityPlayer playerIn) {
        IAmmoInterface capa = clip.getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
        if (ammo == ItemStack.EMPTY) {
            return clip;
        } else if (capa.getAmmo() < capa.getMaxAmmo()){
            ammo.shrink(1);
            clip.getCapability(AmmoFactoryProvider.AMMO_STORAGE,null).addAmmo(1);
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.clip_load, SoundCategory.HOSTILE, 1.0F, 1.0F);
            return clip;
        }
        return clip;
    }
}