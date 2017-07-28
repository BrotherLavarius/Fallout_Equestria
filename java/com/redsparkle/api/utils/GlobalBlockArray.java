package com.redsparkle.api.utils;
import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.block.interractable.*;
/**
 * Created by hoijima on 03.06.17.
 */
public class GlobalBlockArray {


    // Blocks -- Inventory -- World
    public static final SparkleColaMachineBlock         SparkleColaMachineBlock      = new SparkleColaMachineBlock(GlobalNames.SPCmachine);
    public static final workbench                       workbench                    = new workbench(GlobalNames.Workbench);
    public static final workbench_handmade              workbench_handmade           = new workbench_handmade(GlobalNames.Workbench_handmade);
    public static final armor_bench_tier_one            armor_bench_tier_one         = new armor_bench_tier_one(GlobalNames.ArmorBench_tier_one);
    public static final DesktopTerminal                 DesktopTerminal              = new DesktopTerminal(GlobalNames.Terminal);
    public static final locker                          locker                       = new locker(GlobalNames.Locker);
    public static final RadiationBlock                  RadiationBlock               = new RadiationBlock(GlobalNames.RadBlock);
}
