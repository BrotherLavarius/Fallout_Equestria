package com.redsparkle.foe.items.guns.ammo;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by NENYN on 1/2/2017.
 */
public class TenMMClip extends Item {

    public Item ammo = ItemInit.tenMMAmmo;
    public int MaxDamage = 13;

    public TenMMClip() {
        final int NUMBER_OF_BOXES = 1;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);

    }

    public static ItemStack findItemOffBar(EntityPlayer player) {
        for (int slot = 0; slot < player.inventory.getSizeInventory(); ++slot)
            if (player.inventory.getStackInSlot(slot).getItem() instanceof TenMMammo)
                return player.inventory.getStackInSlot(slot);

        return ItemStack.EMPTY;

    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Ammo clip for 10mm pistol");
    }

    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {

        /*
            -------------------THIS FOR INSTA LOADING------------
                        Ill keep this here just because.

                ItemStack stack = player.getHeldItem(hand);
        if (stack.getItemDamage() <= MaxDamage ) {
            ItemStack found = findItemOffBar(player);
            if (found == ItemStack.EMPTY) {
                return new ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand));
            }else if(stack.getItemDamage() <= 0){
                return new ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand));
            } else {
                found.shrink(1);
                stack.setItemDamage(stack.getItemDamage() - 1);
                return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            }
        }

         */

        worldIn.setActiveHand(playerIn);
        return new ActionResult(EnumActionResult.SUCCESS, worldIn.getHeldItem(playerIn));
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer playerIn = (EntityPlayer) entityLiving;
        playerIn.getHeldItem(EnumHand.MAIN_HAND);
        if (stack.getItemDamage() > 1 && stack.getItemDamage() <= MaxDamage ) {
            ItemStack found = findItemOffBar(playerIn);
            if (found == ItemStack.EMPTY) {
                return stack;
            }else if(stack.getItemDamage() <= 0){
                return stack;
            } else {
                found.shrink(1);
                stack.setItemDamage(stack.getItemDamage() - 1);
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.tenMMClipLoad, SoundCategory.HOSTILE, 1.0F, 1.0F);
                return stack;
            }
        }
        return stack;

    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }

}
