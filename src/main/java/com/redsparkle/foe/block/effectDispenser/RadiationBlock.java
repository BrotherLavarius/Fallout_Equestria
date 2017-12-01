package com.redsparkle.foe.block.effectDispenser;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.Init.InitCreativeTabs;
import com.redsparkle.foe.block.effectDispenser.TileEntitys.RadiationBlockTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class RadiationBlock extends BlockContainer {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final RadiationBlock instance = new RadiationBlock(GlobalNames.RadBlock);
    public static final String name = "RadiationBlock";
    boolean red = true;

    public RadiationBlock(String radBlock) {
        super(Material.AIR);
        setLightLevel(0.1F);
        setSoundType(SoundType.METAL);
        setCreativeTab(InitCreativeTabs.Fallout_stats_blocks);
        setSoundType(SoundType.GROUND);
        setRegistryName("rad_block");
        setUnlocalizedName("rad_block");
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new RadiationBlockTileEntity();
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        world.scheduleUpdate(new BlockPos(i, j, k), this, this.tickRate(world));
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return red ? 15 : 0;
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return red ? 15 : 0;
    }

    // ###########################################################3
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        AxisAlignedBB bb = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        return bb;
    }

    // ###########################################################3
    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        AxisAlignedBB bb = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        return bb;
    }

    // ###########################################################
    @Override
    public int tickRate(World world) {
        return 10;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Nullable
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    //displays the bonding box - true for debug
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return false;
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
    }

    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
        World par1World = world;
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        Random par5Random = random;
        for (int la = 0; la < 1; ++la) {
            double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 1.9D * 20;
            double d1 = ((double) ((float) par3 + 0.7F) + (double) (par5Random.nextFloat() - 0.5F) * 1.9D) + 0.5D;
            double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 1.9D * 20;
            double d3 = 0.2199999988079071D;
            double d4 = 0.27000001072883606D;
            par1World.spawnParticle(EnumParticleTypes.CRIT_MAGIC, d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    }
}
