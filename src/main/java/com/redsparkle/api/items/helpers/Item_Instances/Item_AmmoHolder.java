package com.redsparkle.api.items.helpers.Item_Instances;

import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.items.helpers.guns.ItemClipHelpers;
import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 25.07.17.
 */
public abstract class Item_AmmoHolder extends FoeItem {
    public String clipInfo;
    public int clipsize;

    public Item_AmmoHolder(String itemName, int clipsize, String clipInfo) {

        super(itemName);
        final int NUMBER_OF_BOXES = 1;
        this.clipsize = clipsize;
        this.clipInfo = clipInfo;
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(clipsize);
    }


    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        IAmmoInterface capa = stack.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);
        tooltip.add(clipInfo);
        tooltip.add("AMMO LEFT:" + capa.getAmmo() + "/" + capa.getMaxAmmo());
        tooltip.add("DEBUG:" + stack.hasCapability(AmmoFactoryProvider.AMMO_STORAGE, null));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        IAmmoInterface iammo;
        if (stack.hasCapability(AmmoFactoryProvider.AMMO_STORAGE, null)) {
            iammo = stack.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);
            if (iammo.getMaxAmmo() == 0) {
                iammo.setMaxAmmo(clipsize);
            }
            stack.setItemDamage(iammo.getMaxAmmo() - iammo.getAmmo());
        }
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 5;
    }

    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {
        worldIn.setActiveHand(playerIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, worldIn.getHeldItem(playerIn));
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer playerIn = (EntityPlayer) entityLiving;
        ItemStack Istack = ItemClipHelpers.Cliphelper(stack, worldIn, playerIn, clipsize);
        return Istack;
    }
}
