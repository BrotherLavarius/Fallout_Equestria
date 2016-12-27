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

import javax.annotation.Nullable;
import java.util.List;



/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class RadAway extends Item {
    public RadAway(){
        this.setMaxStackSize(10);
        this.setCreativeTab(InitCreativeTabs.Fallout_meds);
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

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        playerIn.setActiveHand(hand);
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
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
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        EntityPlayerMP entityplayer = entityLiving instanceof EntityPlayerMP ? (EntityPlayerMP)entityLiving : null;
        if (entityLiving.hasCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null)) {
            entityLiving.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).removeRadiation(10);
        }
        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            //--stack.stackSize;
        }

        if (!worldIn.isRemote)
        {

        }
        return stack;
    }

}
