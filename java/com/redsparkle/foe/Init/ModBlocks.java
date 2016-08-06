package com.redsparkle.foe.Init;

import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.block.effectDispenser.TileEntitys.RadiationBlockTileEntity;
import com.redsparkle.foe.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
/**
 * Created by hoijima desu on 06.08.16 desu.
 */
public class ModBlocks {
    public static final Set<Block> BLOCKS = new HashSet<>();
    public static final SparkleColaMachineBlock SPARKLE_COLA_MACHINE;
    public static final RadiationBlock RADIATION_BLOCK;

    static {
        SPARKLE_COLA_MACHINE = registerBlock(new SparkleColaMachineBlock());
        RADIATION_BLOCK = registerBlock(new RadiationBlock());
    }



    public static void registerBlocks() {
        // Dummy method to make sure the static initialiser runs
    }

    /**
     * Register a Block with the default ItemBlock class.
     *
     * @param block   The Block instance
     * @param <BLOCK> The Block type
     * @return The Block instance
     */
    protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block) {
        return registerBlock(block, ItemBlock::new);
    }
    protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block, @Nullable Function<BLOCK, ItemBlock> itemFactory) {
        GameRegistry.register(block);

        if (itemFactory != null) {
            final ItemBlock itemBlock = itemFactory.apply(block);

            GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
        }

        BLOCKS.add(block);
        return block;
    }



    public static void registerTileEntities() {

        registerTileEntity(SparkleColaMachineTileEntity.class);
        registerTileEntity(RadiationBlockTileEntity.class);
    }

    private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {
        GameRegistry.registerTileEntity(tileEntityClass, Constants.RESOURCE_PREFIX + tileEntityClass.getSimpleName().replaceFirst("TileEntity", ""));

    }


}
