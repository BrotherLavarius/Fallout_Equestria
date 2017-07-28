package com.redsparkle.foe.Init;

import com.google.common.base.Preconditions;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.block.effectDispenser.TileEntitys.RadiationBlockTileEntity;
import com.redsparkle.foe.block.interractable.TileEntitys.*;
import com.redsparkle.foe.main;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import static com.redsparkle.api.utils.GlobalBlockArray.*;

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


    }


    @Mod.EventBusSubscriber(modid = main.MODID)
    public static class RegistrationHandler {
        public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

        /**
         * Register this mod's {@link Block}s.
         *
         * @param event The event
         */
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

            registry.registerAll(blocks);

        }

        /**
         * Register this mod's {@link ItemBlock}s.
         *
         * @param event The event
         */
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

            for (final ItemBlock item : items) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                registry.register(item.setRegistryName(registryName));
                ITEM_BLOCKS.add(item);
            }

            registerTileEntities();
        }
    }

    private static void registerTileEntities() {
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
