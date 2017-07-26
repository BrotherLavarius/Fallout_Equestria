package com.redsparkle.foe.Init;
import com.redsparkle.api.utils.GlobalBlockArray;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.block.effectDispenser.TileEntitys.RadiationBlockTileEntity;
import com.redsparkle.foe.block.interractable.TileEntitys.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
/**
 * Created by hoijima on 14.12.16.
 */
public class BlockInit {
    public static void preInitCommon() {
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
        for (int i = 0; i < (GlobalBlockArray.blocks.length - 1); i++) {
            Block tempB = GlobalBlockArray.blocks[i];
            tempB.setRegistryName(GlobalBlockArray.blocksNames[i]);
            tempB.setUnlocalizedName(GlobalBlockArray.blocksNames[i]);
            GameRegistry.register(tempB);
            Item tempI = new ItemBlock(GlobalBlockArray.blocks[i]);
            tempI.setUnlocalizedName(GlobalBlockArray.blocksNames[i]);
            tempI.setRegistryName(GlobalBlockArray.blocksNames[i]);
            GameRegistry.register(tempI);
        }
        GameRegistry.registerTileEntity(SparkleColaMachineTileEntity.class, GlobalNames.SPCmachine);
        GameRegistry.registerTileEntity(DesktopTerminalTileEntity.class, GlobalNames.Terminal);
        GameRegistry.registerTileEntity(TileEntity_ArmorBench_tier_one.class, GlobalNames.ArmorBench_tier_one);
        GameRegistry.registerTileEntity(TileEntity_locker.class, GlobalNames.Locker);
        GameRegistry.registerTileEntity(TileEntity_workbench.class, GlobalNames.Workbench);
        GameRegistry.registerTileEntity(TileEntity_workbench_handmade.class, GlobalNames.Workbench_handmade);
        GameRegistry.registerTileEntity(RadiationBlockTileEntity.class, GlobalNames.RadBlock);
    }
    public static void InitCommon() {
    }
    public static void postInitCommon() {
    }
}
