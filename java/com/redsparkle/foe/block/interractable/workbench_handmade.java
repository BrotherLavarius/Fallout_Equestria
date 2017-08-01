package com.redsparkle.foe.block.interractable;
import com.redsparkle.api.block.GeneralAllignBlockTwoOTwo;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.interractable.TileEntitys.DesktopTerminalTileEntity;
import com.redsparkle.foe.block.interractable.TileEntitys.TileEntity_workbench_handmade;
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
/**
 * Created by hoijima on 04.07.16.
 */
public class workbench_handmade extends GeneralAllignBlockTwoOTwo {
    public static final workbench_handmade instance = new workbench_handmade(GlobalNames.Workbench_handmade);
    public workbench_handmade(String workbench_handmade) {
        super(Material.IRON,workbench_handmade);
        setLightLevel(0);
        setSoundType(SoundType.METAL);
        setCreativeTab(InitCreativeTabs.Fallout_blocks);
        setSoundType(SoundType.METAL);
    }
    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof DesktopTerminalTileEntity) {
                ((TileEntity_workbench_handmade) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
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
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntity_workbench_handmade) {
            TileEntity_workbench_handmade te = (TileEntity_workbench_handmade) world.getTileEntity(pos);
            return state;
        }
        return state;
    }
}
