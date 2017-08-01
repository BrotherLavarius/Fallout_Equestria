package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.block.effectDispenser.TileEntitys.RadiationBlockTileEntity;
import com.redsparkle.foe.block.interractable.*;
import com.redsparkle.foe.block.interractable.TileEntitys.*;
import com.redsparkle.foe.main;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hoijima on 14.12.16.
 */

@Mod.EventBusSubscriber(modid = main.MODID)
public class BlockInit {
    public static final SparkleColaMachineBlock SparkleColaMachineBlock = new SparkleColaMachineBlock(GlobalNames.SPCmachine);
    public static final workbench workbench = new workbench(GlobalNames.Workbench);
    public static final workbench_handmade workbench_handmade = new workbench_handmade(GlobalNames.Workbench_handmade);
    public static final armor_bench_tier_one armor_bench_tier_one = new armor_bench_tier_one(GlobalNames.ArmorBench_tier_one);
    public static final DesktopTerminal DesktopTerminal = new DesktopTerminal(GlobalNames.Terminal);
    public static final locker locker = new locker(GlobalNames.Locker);
    public static final RadiationBlock RadiationBlock = new RadiationBlock(GlobalNames.RadBlock);




    public static final String[] blocksNames = new String[]{
            GlobalNames.SPCmachine,
            GlobalNames.Workbench,
            GlobalNames.Workbench_handmade,
            GlobalNames.ArmorBench_tier_one,
            GlobalNames.Terminal,
            GlobalNames.Locker,
            GlobalNames.RadBlock
    };

    public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        final Block[] blocks = {
                SparkleColaMachineBlock,
                workbench,
                workbench_handmade,
                armor_bench_tier_one,
                DesktopTerminal,
                locker,
                RadiationBlock
        };

//        for (int i = 0; i < (blocks.length - 1); i++) {
//            Block tempB = blocks[i];
//            //tempB.setRegistryName(blocksNames[i]);
//            //tempB.setUnlocalizedName(blocksNames[i]);
//
//
//        }
        registry.registerAll(blocks);



    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        final ItemBlock[] items = {
                new ItemBlock(SparkleColaMachineBlock),
                new ItemBlock(workbench),
                new ItemBlock(workbench_handmade),
                new ItemBlock(armor_bench_tier_one),
                new ItemBlock(DesktopTerminal),
                new ItemBlock(locker),
                new ItemBlock(RadiationBlock)

        };

        final IForgeRegistry<Item> registry = event.getRegistry();
        for (int i = 0; i < (items.length - 1); i++) {
            items[i].setUnlocalizedName(blocksNames[i]);
            items[i].setRegistryName(blocksNames[i]);
            registry.register(items[i]);
            ITEM_BLOCKS.add(items[i]);
        }
        //registry.registerAll(items);
        registerTileEntity(SparkleColaMachineTileEntity.class, GlobalNames.SPCmachine);
        registerTileEntity(DesktopTerminalTileEntity.class, GlobalNames.Terminal);
        registerTileEntity(TileEntity_ArmorBench_tier_one.class, GlobalNames.ArmorBench_tier_one);
        registerTileEntity(TileEntity_locker.class, GlobalNames.Locker);
        registerTileEntity(TileEntity_workbench.class, GlobalNames.Workbench);
        registerTileEntity(TileEntity_workbench_handmade.class, GlobalNames.Workbench_handmade);
        registerTileEntity(RadiationBlockTileEntity.class, GlobalNames.RadBlock);
    }




    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
        GameRegistry.registerTileEntity(tileEntityClass, GlobalNames.Domain + name);
    }
}

