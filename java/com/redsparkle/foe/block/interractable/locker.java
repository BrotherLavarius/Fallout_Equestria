package com.redsparkle.foe.block.interractable;

import com.redsparkle.foe.block.GeneralAllignBlockOneOone;
import com.redsparkle.foe.block.interractable.TileEntitys.DesktopTerminalTileEntity;
import com.redsparkle.foe.block.interractable.TileEntitys.TileEntity_locker;
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

/**
 * Created by hoijima on 04.07.16.
 */
public class locker extends GeneralAllignBlockOneOone {
    public static final locker instance = new locker();

    public locker() {
        super(Material.IRON);
        setLightLevel(0);
        setSoundType(SoundType.METAL);
        setCreativeTab(InitCreativeTabs.Fallout_blocks);
        setSoundType(SoundType.METAL);

    }


    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof DesktopTerminalTileEntity) {
                ((TileEntity_locker) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }


    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntity_locker) {
            TileEntity_locker te = (TileEntity_locker) world.getTileEntity(pos);
            return ((IExtendedBlockState) state).withProperty(Properties.AnimationProperty, te.state);
        }
        return state;
    }


}


