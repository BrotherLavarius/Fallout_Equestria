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
public class GeneralAllignBlockTwoOTwo extends GeneralAllignBlockOneOone {
    public static final AxisAlignedBB FULL_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    public GeneralAllignBlockTwoOTwo(Material blockMaterialIn, MapColor blockMapColorIn,String name) {
        super(blockMaterialIn, blockMapColorIn,name);
    }
    public GeneralAllignBlockTwoOTwo(Material materialIn,String name) {
        super(materialIn,name);
    }
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BBhelper.caseFour(state);
    }
}
