package com.redsparkle.foe.block.containers;

import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 04.07.16.
 */
public class SparkleColaMachineBlock extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final SparkleColaMachineBlock instance = new SparkleColaMachineBlock();
    public static final AxisAlignedBB FULL_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    public AxisAlignedBB Fixed;
    public EnumFacing current;
    private ExtendedBlockState state = new ExtendedBlockState(this, new IProperty[]{FACING}, new IUnlistedProperty[]{OBJModel.OBJProperty.INSTANCE});

    public SparkleColaMachineBlock() {
        super(Material.IRON);
        this.setLightLevel(1);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(InitCreativeTabs.Fallout_blocks);
        this.setSoundType(SoundType.METAL);
        this.setUnlocalizedName(MODID + ":" + GlobalNames.SPCmachine);
        this.setRegistryName(new ResourceLocation(MODID, GlobalNames.SPCmachine));
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

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

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof SparkleColaMachineTileEntity) {
                ((SparkleColaMachineTileEntity) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new SparkleColaMachineTileEntity();
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
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof SparkleColaMachineTileEntity) {
            SparkleColaMachineTileEntity te = (SparkleColaMachineTileEntity) world.getTileEntity(pos);
            return ((IExtendedBlockState) state).withProperty(Properties.AnimationProperty, te.state);
        }
        return state;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
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


    @Deprecated
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (state.getValue(FACING).toString() == "south") {
            Fixed = FULL_BLOCK_AABB;
        } else if (state.getValue(FACING).toString() == "north") {
            Fixed = FULL_BLOCK_AABB;
        } else if (state.getValue(FACING).toString() == "east") {
            Fixed = FULL_BLOCK_AABB;
        } else if (state.getValue(FACING).toString() == "west") {
            Fixed = new AxisAlignedBB(0.0D, 0.0D, 1.0D, 1.0D, 3.0D, 3.0D);
        } else {
            Fixed = FULL_BLOCK_AABB;
        }


        return Fixed;

    }


}



