package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.Locker;
import com.redsparkle.foe.block.LockerTileEntety;
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
//    public static final SparkleColaMachineBlock SparkleColaMachineBlock = new SparkleColaMachineBlock(GlobalNames.SPCmachine);
public static final Locker locker = new Locker(GlobalNames.Locker);


    public static final String[] blocksNames = new String[]{
            GlobalNames.Locker

    };

    public static final Set<Block> BLOCKS = new HashSet<>();
    public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();
        final Block[] blocks = {
                locker
        };
        System.out.println("Finished initializing blocks");
        registry.registerAll(blocks);
        registerTileEntity(LockerTileEntety.class, GlobalNames.Locker);
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        final ItemBlock[] items = {
                new ItemBlock(locker)
        };
        for (int i = 0; i < items.length; i++) {
            items[i].setRegistryName(blocksNames[i]);
            registry.register(items[i]);
        }
        System.out.println("Finished initializing Items for blocks");

    }



    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
        GameRegistry.registerTileEntity(tileEntityClass, GlobalNames.Domain + name);
    }
}

