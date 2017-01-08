package com.redsparkle.foe.items.guns.ammo;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by NENYN on 1/2/2017.
 */
public class TenMMClip extends Item {

    public int MaxDamage = 13;
    public TenMMClip()
    {
        final int NUMBER_OF_BOXES = 1;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);

    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("Ammo clip for 10mm pistol");
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if(playerIn.getHeldItem(handIn).getItemDamage() >= MaxDamage){
            findAmmo(playerIn).grow(-1);
            playerIn.getHeldItem(handIn).setItemDamage(playerIn.getHeldItem(handIn).getItemDamage() -1);
            return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }else{
            return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        }


    }


    public ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isAmmo(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            if (player.getHeldItem(EnumHand.OFF_HAND).getItemDamage() <= 12){return player.getHeldItem(EnumHand.OFF_HAND);}
        }
        else if (this.isAmmo(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            if (player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() <= 12){return player.getHeldItem(EnumHand.MAIN_HAND);}
        }
        else{
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isAmmo(itemstack) || itemstack.getItemDamage() <= 12)
                {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean isAmmo(ItemStack stack)
    {
        return stack.getItem() instanceof TenMMammo;
    }
}
