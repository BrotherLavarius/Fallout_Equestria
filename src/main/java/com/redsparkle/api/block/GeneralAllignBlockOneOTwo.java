package com.redsparkle.api.block;

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
    public GeneralAllignBlockOneOTwo(Material blockMaterialIn, MapColor blockMapColorIn, String Name) {
        super(blockMaterialIn, blockMapColorIn, Name);
    }

    public GeneralAllignBlockOneOTwo(Material materialIn, String Name) {
        super(materialIn, Name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BBhelper.caseTwo(state);
    }
}
