package com.redsparkle.foe.items.utility;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.main;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        player.openGui(main.instance, 4, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);

        return EnumActionResult.PASS;

    }

    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        return 1.0F;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
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
