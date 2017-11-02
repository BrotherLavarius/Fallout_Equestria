package com.redsparkle.foe.block.interractable;

import com.redsparkle.api.block.GeneralAllignBlockOneOone;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.Init.InitCreativeTabs;
import com.redsparkle.foe.block.interractable.TileEntitys.DesktopTerminalTileEntity;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by hoijima on 04.07.16.
 */
public class DesktopTerminal extends GeneralAllignBlockOneOone {
    public static final DesktopTerminal instance = new DesktopTerminal(GlobalNames.Terminal);

    public DesktopTerminal(String terminal) {
        super(Material.IRON, terminal);
        setLightLevel(0);
        setSoundType(SoundType.METAL);
        setCreativeTab(InitCreativeTabs.Fallout_blocks);
        setSoundType(SoundType.METAL);
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
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof DesktopTerminalTileEntity) {
            DesktopTerminalTileEntity te = (DesktopTerminalTileEntity) world.getTileEntity(pos);
            return state;
        }
        return state;
    }
}
