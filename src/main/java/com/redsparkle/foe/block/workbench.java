package com.redsparkle.foe.block;

import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class workbench extends FoeBlock {

    public workbench(String blockName, String bb) {
        super(Material.IRON, blockName, bb);
        this.setLightLevel(1);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(InitCreativeTabs.Fallout_blocks);
        this.setSoundType(SoundType.METAL);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof workbench_TE) {
                ((workbench_TE) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }


    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

}


