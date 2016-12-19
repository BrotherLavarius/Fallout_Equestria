package com.redsparkle.foe.Init;

import com.google.common.collect.Lists;
import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;

import com.redsparkle.foe.block.effectDispenser.TileEntitys.RadiationBlockTileEntity;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 14.12.16.
 */
public class BlockInit {
    public static SparkleColaMachineBlock SPARKLE_COLA_MACHINE;
    public static RadiationBlock RADIATION_BLOCK;
    public static ItemBlock itemSparkleColaMachine;  // this holds the unique instance of the itemSparkleColaMachine corresponding to your block
    public static ItemBlock itemRadiationBlock;  // this holds the unique instance of the itemRadiationBlock corresponding to your block
    public static void preInitCommon()
    {
        // each instance of your block should have two names:
        // 1) a registry name that is used to uniquely identify this block.  Should be unique within your mod.  use lower case.
        // 2) an 'unlocalised name' that is used to retrieve the text name of your block in the player's language.  For example-
        //    the unlocalised name might be "water", which is printed on the user's screen as "Wasser" in German or
        //    "aqua" in Italian.
        //
        //    Multiple blocks can have the same unlocalised name - for example
        //  +----RegistryName----+---UnlocalisedName----+
        //  |  flowing_water     +       water          |
        //  |  stationary_water  +       water          |
        //  +--------------------+----------------------+
        //




        Block SPARKLE_COLA_MACHINE = SparkleColaMachineBlock.instance;
        GameRegistry.register(SPARKLE_COLA_MACHINE);

        Block RADIATION_BLOCK = RadiationBlock.instance;
        RADIATION_BLOCK.setRegistryName(GlobalNames.RadBlock);
        GameRegistry.register(RADIATION_BLOCK);

        // We also need to create and register an ItemBlock for this block otherwise it won't appear in the inventory
        Item itemSparkleColaMachine = new ItemBlock(SPARKLE_COLA_MACHINE);
        itemSparkleColaMachine.setRegistryName(SPARKLE_COLA_MACHINE.getRegistryName());
        GameRegistry.register(itemSparkleColaMachine);

        Item itemRadiationBlock = new ItemBlock(RADIATION_BLOCK);
        itemRadiationBlock.setRegistryName(RADIATION_BLOCK.getRegistryName());
        GameRegistry.register(itemRadiationBlock);

        GameRegistry.registerTileEntity(SparkleColaMachineTileEntity.class,GlobalNames.SPCmachine);
        GameRegistry.registerTileEntity(RadiationBlockTileEntity.class,GlobalNames.RadBlock);
    }
    public static void InitCommon()
    {}
    public static void postInitCommon()
    {}
}
