package com.redsparkle.foe.items.meds;

import com.redsparkle.foe.InitCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
    public static final RadAway instance = new RadAway();
    protected String name = "RadAway";
    int collide_rad;

    public RadAway(){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setMaxStackSize(2);
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
        //PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }

        if (!worldIn.isRemote)
        {
            collide_rad = collide_rad-30;
        }
        return stack;
    }


    public void readFromNBT(NBTTagCompound compound)
    {
        readFromNBT(compound);
        this.collide_rad = compound.getInteger("Rads");

    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        writeToNBT(compound);
        compound.setInteger("Rads", collide_rad);

        return compound;
    }
}
