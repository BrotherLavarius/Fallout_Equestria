package com.redsparkle.foe.block;


import com.redsparkle.api.block.BBhelper;
import com.redsparkle.foe.main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;

/**
 * Created by hoijima on 28.07.17.
 */
public abstract class FoeBlock extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static String box;

    public FoeBlock(final Material material, final MapColor mapColor, final String blockName, String bb) {
        super(material, mapColor);
        setBlockName(this, blockName);
        box = bb;
    }

    public FoeBlock(final Material materialIn, final String blockName, String bb) {
        this(materialIn, materialIn.getMaterialMapColor(), blockName, bb);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        box = bb;
    }


    /**
     * Set the registry name of {@code block} to {@code blockName} and the unlocalised name to the full registry name.
     *  @param block     The block
     * @param blockName The block's name
     */
    /**
     * Set the registry name of {@code block} to {@code blockName} and the unlocalised name to the full registry name.
     *
     * @param block     The block
     * @param blockName The block's name
     */
    public static void setBlockName(final Block block, final String blockName) {
        block.setRegistryName(main.MODID, blockName).setUnlocalizedName(block.getRegistryName().toString());
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);
            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }
            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);
        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, enumfacing);
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

        if (box.equalsIgnoreCase("1x1")) {
            return BBhelper.caseOne(state);
        } else if (box.equalsIgnoreCase("1x2")) {
            return BBhelper.caseTwo(state);
        } else if (box.equalsIgnoreCase("2x1")) {
            return BBhelper.caseTree(state);
        } else if (box.equalsIgnoreCase("2x2")) {
            return BBhelper.caseFour(state);
        } else {
            return BBhelper.caseOne(state);
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new ExtendedBlockState(this, new IProperty[]{FACING}, new IUnlistedProperty[]{Properties.AnimationProperty});
    }
}
