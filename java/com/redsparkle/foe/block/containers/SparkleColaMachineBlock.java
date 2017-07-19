package com.redsparkle.foe.block.containers;

import com.redsparkle.api.block.GeneralAllignBlockTwoOTwo;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.Properties;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 04.07.16.
 */
public class SparkleColaMachineBlock extends GeneralAllignBlockTwoOTwo {
    public static final SparkleColaMachineBlock instance = new SparkleColaMachineBlock();

    public SparkleColaMachineBlock() {
        super(Material.IRON);
        this.setLightLevel(1);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(InitCreativeTabs.Fallout_blocks);
        this.setSoundType(SoundType.METAL);
        this.setUnlocalizedName(MODID + ":" + GlobalNames.SPCmachine);
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


}



