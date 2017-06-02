package com.redsparkle.foe.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by hoijima on 02.06.17.
 */
public class GeneralAllignBlockOneOTwo extends GeneralAllignBlockOneOone {


    public GeneralAllignBlockOneOTwo(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    public GeneralAllignBlockOneOTwo(Material materialIn) {
        super(materialIn);
    }

    @Override
    @Deprecated
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (state.getValue(FACING).toString() == "south") {
            Fixed = FULL_BLOCK_AABB;
        } else if (state.getValue(FACING).toString() == "north") {
            Fixed = FULL_BLOCK_AABB;
        } else if (state.getValue(FACING).toString() == "east") {
            Fixed = FULL_BLOCK_AABB;
        } else if (state.getValue(FACING).toString() == "west") {
            Fixed = FULL_BLOCK_AABB;
        } else {
            Fixed = FULL_BLOCK_AABB;
        }


        return Fixed;

    }

}
