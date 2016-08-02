package com.redsparkle.foe.block.effectDispenser;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class RadiationBlock extends Block {
    public static final RadiationBlock instance = new RadiationBlock();
    public static final String name = "RadiationBlock";
    private final boolean isOn = true;
    int collide_rad = 0;
    int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0;

    boolean red = true;

    public RadiationBlock() {
        super(Material.IRON);
        setLightLevel(1);
        setSoundType(SoundType.METAL);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(MODID, name));
        setSoundType(SoundType.GROUND);

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

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(-2.0D, -2.0D, -2.0D, 2.0D, 2.0D, 2.0D);
    }


    @Override
    public int tickRate(World world) {
        return 10;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return null;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return true;
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
    }

    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
        EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
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
                par1World.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            }

    }
    public void onEntityCollidedWithBlock( World world, int x, int y, int z, Entity entity ) {
        if (entity instanceof EntityLiving) {
            collide_rad++;
            System.out.println("+"+collide_rad+"rads");
            entity.attackEntityFrom(null, 1);
        }
    }
}
