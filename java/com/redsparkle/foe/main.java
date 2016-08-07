package com.redsparkle.foe;


import com.redsparkle.foe.Init.ModBlocks;
import com.redsparkle.foe.Init.ModItems;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.sounds.ModSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = main.MODID, version = main.VERSION, useMetadata = true,acceptedMinecraftVersions = "[1.9.4,)")
public class main
{
    public static final String MODID = "fallout_equestria";
    public static final String VERSION = "0.0000000-VERY ALPHA";
    @CapabilityInject(IRadiation.class)
    public static final Capability<IRadiation> RADIATION_CAPABILITY = null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        System.out.println("FOE PACK LOADING");
        System.out.println("                                          ,▄▄▄                              ");
        System.out.println("                                       ▄▓█▀▀▀▀▀█▄                           ");
        System.out.println("               ▄▄▓█`       ,▄▄▓▓▄▄▄▄▄@██▀!√√√√√└▀█▄                         ");
        System.out.println("            .▓█▀██       #█▀▀└:.!╙▀▀██▀:√√√√√√√√√!▀▀█▓▓▄▄                   ");
        System.out.println("           ╓█▀..▀█▓▄▄▄▄▓▀▀:√√√√√√√√√√√√√√√√√√√√√√√√√░░▀▀██▄                 ");
        System.out.println("           ██.√√√!▀▀▀▀▀:√√√√√√√√√√√√√√√√√√√√√√√√√√√√╠░░░░▀█▄                ");
        System.out.println("           █▌√√√√√√√√√√√√√▄▄▄▄▄.√√√√√√√╓▄▄▄.√√√√√√√√╠░░░░░╙█▄               ");
        System.out.println("           ██.√√√√√√√√√▄#█▀╙`╙▀█▓▄▄▄@▓██████▄.√√√√√╠░░░░░░░╙█▓▄             ");
        System.out.println("         ┌████:√√√√√(▄█▀╙       └▀▀▀▀└   └▀▀██,√√╓╢░░░░░░░░░░▀██▄           ");
        System.out.println("         ██:√╙▀▓▄▄▓▓▀▀                      └██▄░░░░░░░░░░░░░░░██▄          ");
        System.out.println("         █▌√√╓██▀  ▄▄@╕                       ▀▀█▓▀▀▀▀▀▀███▄░░░░██▄         ");
        System.out.println("         ██▄▓█▀  ╙▀▀▀▀▀                 ,▄               ▀███░░░░██▄        ");
        System.out.println("          ███`                         ▓███,     .        ███░░░░║██        ");
        System.out.println("         ▓█▀     ,▄                     └▀██▄            ▄██▀░░░░░██`       ");
        System.out.println("        ██▀     ███¼        ,              ▀▀        ╓@██▀▀░░░░░░░██        ");
        System.out.println("       ██▀     ▐███       ╓█▀        ▄▄,          .  ▄╙▀█░░░░░░░░╟██        ");
        System.out.println("      ▐█▌       ▀▀└     .▓█└        #███          .  ╙█▓,▀█░░░░░░██▌        ");
        System.out.println("      ██              ▄▓█▀          ███▌          . .▄,▀█▄╙█░░░░███         ");
        System.out.println("     ╟█▌            #██▀            ╙▀▀           .  ▀█▓,█▄╙█░░███          ");
        System.out.println("     ██─            ███                             ▓▄,▀█▄█,█░███`          ");
        System.out.println("     ██             ╙███                         .   ▀█▄╙█Ö█████            ");
        System.out.println("     ██    ,#         ╙╙                         . ╙█▄ ▀ ╙████▀             ");
        System.out.println("     ██  ╒███▄▄                  ▐█▄            .   ╙▀  .@███┘              ");
        System.out.println("     ██▌  ██▄ └╙▀▀#╦▄▄▄▄▄▄▄▄▄▄▄▄#████▄         .         ╙███               ");
        System.out.println("     ▐██   ▀ ▀▓▄,     `└╙└└ .      ███▌        .          ╟██               ");
        System.out.println("      ██▌      ╙▀█▓▄▄▄,   .,▄▄▄▓▓▀▀╙██        .          .███               ");
        System.out.println("      └██▄        └▀▀▀███▀▀▀▀╙      ▀       ..          ▄███                ");
        System.out.println("       ╙██▄       Ñ▓▓▓▓µ                   ..    ▄▓▓▓▓███▀`                 ");
        System.out.println("        └██▄        `└└                  ..    ▄███▀└└                      ");
        System.out.println("          ▀██▄                          .   ▄▓██▀└                          ");
        System.out.println("            ▀█▓▄                     ..  ▄▓██▀╙                             ");
        System.out.println("              ╙▀█▄,                .╓▄▓██████                               ");
        System.out.println("                 ╙██▓▄         ...   '' ▄██▀                                ");
        System.out.println("                  ╙█████▓▓▄▄▄▄      .▄▄██▀'                                 ");
        System.out.println("                    ▀█████▄▄▄▄▄▄▄▄▓████▀                                    ");
        System.out.println("                       ╙▀▀▀██████▀▀▀╙       								");
        System.out.println("WAR");
        System.out.println("WAR NEVER CHANGES");

        final CreativeTabs Fallout_ammo = InitCreativeTabs.Fallout_ammo;
        final CreativeTabs Fallout_blocks = InitCreativeTabs.Fallout_blocks;
        final CreativeTabs Fallout_guns = InitCreativeTabs.Fallout_guns;
        final CreativeTabs Fallout_meds = InitCreativeTabs.Fallout_meds;
        final CreativeTabs Fallout_stats_blocks = InitCreativeTabs.Fallout_stats_blocks;

        ModSoundEvents.registerSounds();
        OBJLoader.INSTANCE.addDomain(MODID.toLowerCase());



        if (event.getSide() == Side.CLIENT)
            clientPreInit();
    }

    private void clientPreInit() {
        ModBlocks.registerBlocks();
        ModBlocks.registerTileEntities();
        ModItems.registerItems();
        CapabilityManager.INSTANCE.register(IRadiation.class, new Storage(),Factory.class );
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        //ModCapabilities.registerCapabilities();

    }

    private void init(FMLInitializationEvent event){
        if (event.getSide() == Side.CLIENT) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                    .register(Item.getItemFromBlock(RadiationBlock.instance), 0, new ModelResourceLocation(MODID.toLowerCase() + ":" + RadiationBlock.name, "inventory"));

        }
    }

}
