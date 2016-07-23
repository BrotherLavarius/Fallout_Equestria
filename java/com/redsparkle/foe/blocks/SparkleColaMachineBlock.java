package com.redsparkle.foe.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 04.07.16.
 */
public class SparkleColaMachineBlock extends Block {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final SparkleColaMachineBlock instance = new SparkleColaMachineBlock();
    public static final String name = "SparkleColaMachine";
    private int counter = 1;

    private SparkleColaMachineBlock()
    {
        super(Material.IRON);
        setLightLevel(1);
        setSoundType(SoundType.METAL);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setUnlocalizedName(MODID + ":" + name);
        setRegistryName(new ResourceLocation(MODID, name));

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
        return new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[] {Properties.AnimationProperty});
    }
}



