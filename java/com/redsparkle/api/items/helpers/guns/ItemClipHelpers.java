package com.redsparkle.api.items.helpers.guns;

import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMammo;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMammo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Created by hoijima on 23.06.17.
 */
public class ItemClipHelpers {

    public static ItemStack TenMMFind(EntityPlayer player) {
        for (int slot = 0; slot < player.inventory.getSizeInventory(); ++slot)
            if (player.inventory.getStackInSlot(slot).getItem() instanceof TenMMammo)
                return player.inventory.getStackInSlot(slot);

        return ItemStack.EMPTY;
    }

    public static ItemStack TenMMClipStackHelper(ItemStack clip, World worldIn, EntityPlayer playerIn, int MaxDamage) {
        if (clip.getItemDamage() > 1 && clip.getItemDamage() <= MaxDamage) {
            ItemStack ammo = TenMMFind(playerIn);
            triggers(clip, ammo, worldIn, playerIn);
        }
        return clip;

    }

    public static ItemStack FourTenMMFind(EntityPlayer player) {
        for (int slot = 0; slot < player.inventory.getSizeInventory(); ++slot)
            if (player.inventory.getStackInSlot(slot).getItem() instanceof FourTenMMammo)
                return player.inventory.getStackInSlot(slot);

        return ItemStack.EMPTY;
    }

    public static ItemStack FourTenMMClipStackHelper(ItemStack clip, World worldIn, EntityPlayer playerIn, int MaxDamage) {
        if (clip.getItemDamage() > 1 && clip.getItemDamage() <= MaxDamage) {
            ItemStack ammo = ItemClipHelpers.FourTenMMFind(playerIn);
            triggers(clip, ammo, worldIn, playerIn);
        }
        return clip;

    }

    public static ItemStack triggers(ItemStack clip, ItemStack ammo, World worldIn, EntityPlayer playerIn) {
        if (ammo == ItemStack.EMPTY) {
            return clip;
        } else if (clip.getItemDamage() <= 0) {
            return clip;
        } else {
            ammo.shrink(1);
            clip.setItemDamage(clip.getItemDamage() - 1);
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.clip_load, SoundCategory.HOSTILE, 1.0F, 1.0F);
            return clip;
        }
    }

}