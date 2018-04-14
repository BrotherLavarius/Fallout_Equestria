package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hoijima on 14.12.16.
 */

@Mod.EventBusSubscriber(modid = main.MODID)
public class BlockInit {
    public static final Map<Block,String> BLOCKS = new HashMap<>();
    public static final Map<Block, String> BLOCKS_CSTUDIO = new HashMap<>();
    public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();


        for (ModBlocks block : ModBlocks.values()) {
            registry.register((Block) block.getBLOCK());
            registerTileEntity(block.getTILEENTITY(), block.getNAME());
            BLOCKS.put((Block) block.getBLOCK(), block.getNAME());
        }

        for (ModBlocks_CStudio block : ModBlocks_CStudio.values()) {
            registry.register((Block) block.getBLOCK());
            registerTileEntity(block.getTE_CLASS(), block.getNAME());
            BLOCKS.put((Block) block.getBLOCK(), block.getNAME());
        }
        System.out.println("Finished initializing blocks");

    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        for(Map.Entry<Block,String> entry : BLOCKS.entrySet()) {
            registerItemBlocks(entry, registry);
        }

        for (Map.Entry<Block, String> entry : BLOCKS_CSTUDIO.entrySet()) {
            registerItemBlocks(entry, registry);
        }

        System.out.println("Finished initializing Items for blocks");

    }

    public static void registerItemBlocks(Map.Entry<Block, String> entry, IForgeRegistry<Item> registry) {
        Block block = entry.getKey();
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName().getResourcePath());
        ITEM_BLOCKS.add(item);
        registry.register(item);
    }

    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
        GameRegistry.registerTileEntity(tileEntityClass, GlobalNames.Domain + name);
        RenderingInit_CraftStudio.bindTESR();
    }
}

