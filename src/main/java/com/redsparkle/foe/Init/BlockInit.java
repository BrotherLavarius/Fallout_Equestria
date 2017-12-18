package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.*;
import com.redsparkle.foe.main;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;

import static com.redsparkle.foe.Init.ItemInit.ITEMS;

/**
 * Created by hoijima on 14.12.16.
 */

@Mod.EventBusSubscriber(modid = main.MODID)
public class BlockInit {

    //    public static final SparkleColaMachineBlock SparkleColaMachineBlock = new SparkleColaMachineBlock(GlobalNames.SPCmachine);
    public static final desktop_terminal desktop_terminal = new desktop_terminal(GlobalNames.desktop_terminal);
    public static final sparkle_cola_machine sparkle_cola_machine = new sparkle_cola_machine(GlobalNames.sparkle_cola_machine);
    public static final workbench workbench = new workbench(GlobalNames.workbench);
    public static final workbench_handmade wb_hm =new workbench_handmade(GlobalNames.workbench_handmade);
    public static final workbench_sawing wb_sw = new workbench_sawing(GlobalNames.workbench_sawing);
    public static final generator generator = new generator(GlobalNames.generator);
    public static final locker locker = new locker(GlobalNames.locker);


    public static final Map<Block,String> BLOCKS = new HashMap<>();
    public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();



    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();
        final Block[] blocks = {
                desktop_terminal
                ,sparkle_cola_machine
                ,workbench
                ,generator
                ,locker
                ,wb_hm
                ,wb_sw

        };
        final Class[] entitys = new Class[]{
                desktop_terminal_TE.class,
                sparkle_cola_machine_TE.class,
                workbench_TE.class,
                workbench_handmade_TE.class,
                workbench_sawing_TE.class,
                locker_TE.class,
                generator_TE.class


        };
        System.out.println("Finished initializing blocks");
        registry.registerAll(blocks);

        for (Block block: blocks) {
            BLOCKS.put(block,block.getRegistryName().getResourcePath());
        }

        for (Class entety: entitys) {
            registerTileEntity(entety,"Default name");
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        ItemBlock[] items;

        for(Map.Entry<Block,String> entry : BLOCKS.entrySet()) {
            Block block = entry.getKey();
            ItemBlock item = new ItemBlock(block);
            item.setRegistryName(block.getRegistryName().getResourcePath());
            ITEM_BLOCKS.add(item);
            registry.register(item);
        }

        System.out.println("Finished initializing Items for blocks");

    }



    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
        GameRegistry.registerTileEntity(tileEntityClass, GlobalNames.Domain + name);
    }
}

