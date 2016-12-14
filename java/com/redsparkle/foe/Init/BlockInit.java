package com.redsparkle.foe.Init;

import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;

import com.redsparkle.foe.block.effectDispenser.TileEntitys.RadiationBlockTileEntity;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

        OBJLoader.INSTANCE.addDomain(MODID.toLowerCase());

        SPARKLE_COLA_MACHINE = (SparkleColaMachineBlock)(new SparkleColaMachineBlock().setUnlocalizedName("SparkleCola_machine"));
        SPARKLE_COLA_MACHINE.setRegistryName("SparkleCola_machine");
        GameRegistry.register(SPARKLE_COLA_MACHINE);

        RADIATION_BLOCK = (RadiationBlock)(new RadiationBlock().setUnlocalizedName("Radiation_block"));
        RADIATION_BLOCK.setRegistryName("Radiation_block");
        GameRegistry.register(RADIATION_BLOCK);

        // We also need to create and register an ItemBlock for this block otherwise it won't appear in the inventory
        itemSparkleColaMachine = new ItemBlock(SPARKLE_COLA_MACHINE);
        itemSparkleColaMachine.setRegistryName(SPARKLE_COLA_MACHINE.getRegistryName());
        GameRegistry.register(itemSparkleColaMachine);

        itemRadiationBlock = new ItemBlock(RADIATION_BLOCK);
        itemRadiationBlock.setRegistryName(RADIATION_BLOCK.getRegistryName());
        GameRegistry.register(itemRadiationBlock);

        GameRegistry.registerTileEntity(SparkleColaMachineTileEntity.class,"SparkleCola_machine_tile_entity");
        GameRegistry.registerTileEntity(RadiationBlockTileEntity.class,"Radiation_block_tile_entity");


    }
    public static void InitCommon()
    {}
    public static void postInitCommon()
    {}
}
