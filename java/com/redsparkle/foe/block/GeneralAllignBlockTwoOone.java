package com.redsparkle.foe.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by hoijima on 02.06.17.
 */
public class GeneralAllignBlockTwoOone extends GeneralAllignBlockOneOone {


    public GeneralAllignBlockTwoOone(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    public GeneralAllignBlockTwoOone(Material materialIn) {
        super(materialIn);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BBhelper.caseTwo(state);
    }

    // ###########################################################3
    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return BBhelper.caseTwo(state);
    }

}
