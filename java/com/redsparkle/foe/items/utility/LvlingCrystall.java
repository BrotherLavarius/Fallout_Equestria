package com.redsparkle.foe.items.utility;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.main;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
 * Created by hoijima on 29.05.17.
 */
public class LvlingCrystall extends Item {

    public LvlingCrystall() {
        this.setMaxStackSize(1);
        this.setCreativeTab(InitCreativeTabs.Fallout_meds);   // the item will appear on the Miscellaneous tab in creative
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

        stack.setCount(0);
        return stack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }


    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer player, EnumHand playerIn) {
        player.setActiveHand(playerIn);
        player.openGui(main.instance, 4, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);

        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(playerIn));
    }


    // adds 'tooltip' text
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        tooltip.add("Strainge glowy crystall");
        tooltip.add("One time item to assign S.P.E.C.H.I.A.L stats");
    }
}
