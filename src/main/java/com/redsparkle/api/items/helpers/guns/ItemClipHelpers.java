package com.redsparkle.api.items.helpers.guns;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageClientPlaySound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

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
            if (!worldIn.isRemote & Side.SERVER.isServer()) {

                double x = playerIn.getPosition().getX();
                double y = playerIn.getPosition().getY();
                double z = playerIn.getPosition().getZ();

                main.simpleNetworkWrapper.sendToAllAround(new MessageClientPlaySound("gun_clipReload", x + "," + y + "," + z), new NetworkRegistry.TargetPoint(0,  x,  y, z, 10.0));
                main.simpleNetworkWrapper.sendTo(new MessageClientPlaySound("gun_clipReload", x + "," + y + "," + z), (EntityPlayerMP) playerIn);
            }
            return clip;
        }
        return clip;
    }
}