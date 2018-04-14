package com.redsparkle.foe.block;

import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class alarm_lamp extends FoeBlock implements ITileEntityProvider {

    public alarm_lamp(String blockName, String bb) {
        super(Material.IRON, blockName, bb);
        this.setLightLevel(0);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(InitCreativeTabs.Fallout_blocks);
        this.setSoundType(SoundType.METAL);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

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

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public alarm_lamp_TE createNewTileEntity(World worldIn, int meta) {
        return new alarm_lamp_TE();
    }
}


