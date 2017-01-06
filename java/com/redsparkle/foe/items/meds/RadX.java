package com.redsparkle.foe.items.meds;

import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
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
 * Created by hoijima desu on 29.07.16 desu.
 */
public class RadX extends Item {
    public RadX(){
        this.setMaxStackSize(15);
        this.setCreativeTab(InitCreativeTabs.Fallout_meds);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        EntityPlayerMP entityplayer = entityLiving instanceof EntityPlayerMP ? (EntityPlayerMP)entityLiving : null;
         if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            if (entityLiving.hasCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null)) {
                entityLiving.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).removeRadiation(20);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).updateClient(entityplayer);

                System.out.println("Your Rads are now: "+entityLiving.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY,null).getRadiation());
            }
            //here was --stack.stackSize; ..im sad that it was removed
            stack.grow(-1);
        }

        if (!worldIn.isRemote)
        {

        }
        return stack;
    }
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }


    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn)
    {
        worldIn.setActiveHand(playerIn);
        return new ActionResult(EnumActionResult.SUCCESS, worldIn.getHeldItem(playerIn));
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("Best item to have when radiation is around!");
        tooltip.add("Has a nasty orange after taste. Yuhc");
    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return false;
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */

}
