package com.redsparkle.foe.block.interractable;

import com.redsparkle.foe.block.GeneralAllignBlockTwoOone;
import com.redsparkle.foe.block.interractable.TileEntitys.DesktopTerminalTileEntity;
import com.redsparkle.foe.block.interractable.TileEntitys.TileEntity_ArmorBench_tier_one;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.utils.GlobalNames;
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
public class armor_bench_tier_one extends GeneralAllignBlockTwoOone {
    public static final armor_bench_tier_one instance = new armor_bench_tier_one();

    public armor_bench_tier_one() {
        super(Material.IRON);
        setLightLevel(0);
        setSoundType(SoundType.METAL);
        setCreativeTab(InitCreativeTabs.Fallout_blocks);
        setSoundType(SoundType.METAL);
        setUnlocalizedName(MODID + ":" + GlobalNames.ArmorBench_tier_one);
    }


    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof DesktopTerminalTileEntity) {
                ((TileEntity_ArmorBench_tier_one) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }


    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntity_ArmorBench_tier_one) {
            TileEntity_ArmorBench_tier_one te = (TileEntity_ArmorBench_tier_one) world.getTileEntity(pos);
            return ((IExtendedBlockState) state).withProperty(Properties.AnimationProperty, te.state);
        }
        return state;
    }


}


