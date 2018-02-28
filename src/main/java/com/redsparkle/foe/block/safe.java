package com.redsparkle.foe.block;

import com.redsparkle.api.Capability.block.Locks.LockFactoryProvider;
import com.redsparkle.api.Capability.block.Locks.LockInterface;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class safe extends FoeBlock implements ITileEntityProvider {

    public safe(String blockName, String bb) {
        super(Material.IRON, blockName, bb);
        this.setLightLevel(1);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(InitCreativeTabs.Fallout_blocks);
        this.setSoundType(SoundType.METAL);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }


    private boolean isTileProvider = this instanceof ITileEntityProvider;

    public boolean hasTileEntity(IBlockState state) {
        return isTileProvider;
    }


    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        TileEntity entity = worldIn.getTileEntity(pos);
        if (entity.hasCapability(LockFactoryProvider.LOCK_CAPABILITY, null)) {
            LockInterface lock = entity.getCapability(LockFactoryProvider.LOCK_CAPABILITY, null);
            System.out.println("Lock level: " + lock.getComplex());
            System.out.println("Lock status: " + lock.getLockStatus());

        }

    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileEntity safe = new safe_TE();
        return safe;
    }
}


