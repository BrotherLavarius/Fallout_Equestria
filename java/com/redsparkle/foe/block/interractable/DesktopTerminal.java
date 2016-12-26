package com.redsparkle.foe.block.interractable;

import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 04.07.16.
 */
public class DesktopTerminal extends Block {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final DesktopTerminal instance = new DesktopTerminal();
    public static final AxisAlignedBB FULL_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    public AxisAlignedBB Fixed;
    private ExtendedBlockState state = new ExtendedBlockState(this, new IProperty[]{FACING}, new IUnlistedProperty[]{OBJModel.OBJProperty.INSTANCE});


    public EnumFacing current ;
     public DesktopTerminal()
    {
        super(Material.IRON);
        setLightLevel(0);
        setSoundType(SoundType.METAL);
        setCreativeTab(InitCreativeTabs.Fallout_blocks);
        setSoundType(SoundType.METAL);
        setUnlocalizedName(MODID + ":" + GlobalNames.Terminal);
        setRegistryName(new ResourceLocation(MODID, GlobalNames.Terminal));
    }

    public static EnumFacing getFacingFromEntity(World worldIn, BlockPos clickedBlock, EntityLivingBase entityIn)
    {
        if (MathHelper.abs((float)entityIn.posX - (float)clickedBlock.getX()) < 2.0F && MathHelper.abs((float)entityIn.posZ - (float)clickedBlock.getZ()) < 2.0F)
        {
            double d0 = entityIn.posY + (double)entityIn.getEyeHeight();

            if (d0 - (double)clickedBlock.getY() > 2.0D)
            {
                return EnumFacing.UP;
            }

            if ((double)clickedBlock.getY() - d0 > 0.0D)
            {
                return EnumFacing.DOWN;
            }
        }

        return entityIn.getHorizontalFacing().getOpposite();
    }

    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new SparkleColaMachineTileEntity();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) { return false; }

    @Override
    public boolean isFullCube(IBlockState state) { return false; }

    @Override
    public boolean isVisuallyOpaque() { return false; }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof SparkleColaMachineTileEntity)
        {
            SparkleColaMachineTileEntity te = (SparkleColaMachineTileEntity) world.getTileEntity(pos);
            return ((IExtendedBlockState) state).withProperty(Properties.AnimationProperty, te.state);
        }
        return state;
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new ExtendedBlockState(this, new IProperty[]{FACING}, new IUnlistedProperty[] {Properties.AnimationProperty});
    }

    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, getFacingFromEntity(world, pos, placer));
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getIndex();
    }

    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

        @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);
    }

    @Deprecated
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if(state.getValue(FACING).toString() == "south"){
            Fixed = FULL_BLOCK_AABB;
        }else if (state.getValue(FACING).toString() == "north"){
            Fixed = FULL_BLOCK_AABB;
        }else if (state.getValue(FACING).toString() == "east"){
            Fixed = FULL_BLOCK_AABB;
        }else if (state.getValue(FACING).toString() == "west"){
            Fixed = FULL_BLOCK_AABB;
        }else {
            Fixed = FULL_BLOCK_AABB;
        }


        return Fixed;

    }



    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof SparkleColaMachineTileEntity)
            {
                playerIn.displayGUIChest((SparkleColaMachineTileEntity)tileentity);
                playerIn.addStat(StatList.CHEST_OPENED);
            }
            return true;
        }
    }
}



