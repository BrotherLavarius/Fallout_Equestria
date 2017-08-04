package com.redsparkle.foe.Init;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.main;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by hoijima on 03.08.17.
 */
public class FluidsInit {
    /**
     * The fluids registered by this mod. Includes fluids that were already registered by another mod.
     */
    public static final Set<Fluid> FLUIDS = new HashSet<>();

    /**
     * The fluid blocks from this mod only. Doesn't include blocks for fluids that were already registered by another mod.
     */
    public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();


    public static final Fluid PINKCLOUD = createFluid("pink_cloud", true,
            fluid -> fluid.setLuminosity(10).setDensity(1800).setViscosity(10).setGaseous(true),
            fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.PINK)));


    public static final Fluid TOXICFUMES = createFluid("toxic_fumes", true,
            fluid -> fluid.setLuminosity(10).setDensity(800).setViscosity(10).setGaseous(true),
            fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.GREEN)));


    /**
     * Create a {@link Fluid} and its {@link IFluidBlock}, or use the existing ones if a fluid has already been registered with the same name.
     *
     * @param name                 The name of the fluid
     * @param hasFlowIcon          Does the fluid have a flow icon?
     * @param fluidPropertyApplier A function that sets the properties of the {@link Fluid}
     * @param blockFactory         A function that creates the {@link IFluidBlock}
     * @return The fluid and block
     */
    private static <T extends Block & IFluidBlock> Fluid createFluid(final String name, final boolean hasFlowIcon, final Consumer<Fluid> fluidPropertyApplier, final Function<Fluid, T> blockFactory) {
        final String texturePrefix = GlobalNames.Domain + ":" + "blocks/fluid_";

        final ResourceLocation still = new ResourceLocation(texturePrefix + name + "_still");
        final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + name + "_flow") : still;

        Fluid fluid = new Fluid(name, still, flowing);
        final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

        if (useOwnFluid) {
            fluidPropertyApplier.accept(fluid);
            MOD_FLUID_BLOCKS.add(blockFactory.apply(fluid));
        } else {
            fluid = FluidRegistry.getFluid(name);
        }

        FLUIDS.add(fluid);

        return fluid;
    }

    /**
     * Register this mod's tanks and buckets.
     */
    private static void registerFluidContainers() {
//        registerTank(FluidRegistry.WATER);
//        registerTank(FluidRegistry.LAVA);
//
//        for (final Fluid fluid : FLUIDS) {
//            registerBucket(fluid);
//            registerTank(fluid);
//        }
    }

    private static void registerBucket(final Fluid fluid) {

        FluidRegistry.addBucketForFluid(fluid);
    }

    private static void registerTank(final Fluid fluid) {

    }

    @Mod.EventBusSubscriber(modid = main.MODID)
    public static class RegistrationHandler {

        /**
         * Register this mod's fluid {@link Block}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
                final Block block = (Block) fluidBlock;
                block.setRegistryName(main.MODID, "fluid." + fluidBlock.getFluid().getName());
                block.setUnlocalizedName(GlobalNames.Domain + fluidBlock.getFluid().getUnlocalizedName());
                block.setCreativeTab(InitCreativeTabs.Fallout_blocks);
                registry.register(block);
            }
        }

        /**
         * Register this mod's fluid {@link ItemBlock}s.
         *
         * @param event The event
         */
        // Use EventPriority.LOWEST so this is called after the RegistryEvent.Register<Item> handler in ModBlocks where
        // the ItemBlock for ModBlocks.FLUID_TANK is registered.
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
                final Block block = (Block) fluidBlock;
                final ItemBlock itemBlock = new ItemBlock(block);
                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName());
                itemBlock.setRegistryName(registryName);
                registry.register(itemBlock);
            }

            registerFluidContainers();
        }
    }
}
