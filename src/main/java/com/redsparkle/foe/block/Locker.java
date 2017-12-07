package com.redsparkle.foe.block;

import com.redsparkle.api.block.GeneralAllignBlockOneOone;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import static com.redsparkle.foe.main.MODID;

public class Locker extends GeneralAllignBlockOneOone {
    public static final Locker instance = new Locker(GlobalNames.Locker);

    public Locker(String blockName) {
        super(Material.IRON, blockName);
        this.setLightLevel(1);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(InitCreativeTabs.Fallout_blocks);
        this.setSoundType(SoundType.METAL);
        this.setUnlocalizedName(MODID + ":" + GlobalNames.Locker);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof LockerTileEntety) {
                ((LockerTileEntety) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new LockerTileEntety();
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
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof LockerTileEntety) {
            LockerTileEntety te = (LockerTileEntety) world.getTileEntity(pos);
            return state;
        }
        return state;
    }
}


