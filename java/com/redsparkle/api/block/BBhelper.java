package com.redsparkle.api.block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
/**
 * Created by hoijima on 04.06.17.
 */
public class BBhelper {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final AxisAlignedBB normal = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    private static AxisAlignedBB bb;
    // 1x1x1
    public static AxisAlignedBB caseOne(IBlockState state) {
        if (state.getValue(FACING).toString() == "south") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "north") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "east") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "west") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        } else {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
        return bb;
    }
    // 1x2x1
    public static AxisAlignedBB caseTwo(IBlockState state) {
        if (state.getValue(FACING).toString() == "south") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "north") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "east") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "west") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
        } else {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
        }
        return bb;
    }
    // 2x1x1
    public static AxisAlignedBB caseTree(IBlockState state) {
        if (state.getValue(FACING).toString() == "south") {
            bb = new AxisAlignedBB(-1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "north") {
            bb = new AxisAlignedBB(2.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "east") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 2.0D);
        } else if (state.getValue(FACING).toString() == "west") {
            bb = new AxisAlignedBB(0.0D, 0.0D, -1.0D, 1.0D, 1.0D, 1.0D);
        } else {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
        return bb;
    }
    // 2x2x1
    public static AxisAlignedBB caseFour(IBlockState state) {
        if (state.getValue(FACING).toString() == "south") {
            bb = new AxisAlignedBB(-1.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "north") {
            bb = new AxisAlignedBB(2.0D, 0.0D, 0.0D, 0.0D, 2.0D, 1.0D);
        } else if (state.getValue(FACING).toString() == "east") {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 2.0D);
        } else if (state.getValue(FACING).toString() == "west") {
            bb = new AxisAlignedBB(0.0D, 0.0D, -1.0D, 1.0D, 2.0D, 1.0D);
        } else {
            bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
        }
        return bb;
    }
}
