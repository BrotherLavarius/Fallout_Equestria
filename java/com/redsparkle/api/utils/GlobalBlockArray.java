package com.redsparkle.api.utils;
import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.block.interractable.*;
import net.minecraft.block.Block;
/**
 * Created by hoijima on 03.06.17.
 */
public class GlobalBlockArray {
    public static final Block[] blocks = new Block[]{
            // Blocks -- Inventory -- World
            SparkleColaMachineBlock.instance,
            // Blocks -- interract -- World
            workbench.instance,
            workbench_handmade.instance,
            armor_bench_tier_one.instance,
            // Blocks -- interract -- Stable
            DesktopTerminal.instance,
            // Blocks -- Inventory -- Stable
            locker.instance,
            // Blocks - Stat dispencers
            RadiationBlock.instance
    };
    public static final String[] blocksNames = new String[]{
            GlobalNames.SPCmachine,
            GlobalNames.Workbench,
            GlobalNames.Workbench_handmade,
            GlobalNames.ArmorBench_tier_one,
            GlobalNames.Terminal,
            GlobalNames.Locker,
            GlobalNames.RadBlock
    };
}
