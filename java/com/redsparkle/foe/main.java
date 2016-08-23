package com.redsparkle.foe;


import com.redsparkle.foe.Init.ModBlocks;
import com.redsparkle.foe.Init.ModItems;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.capabilities.BaseRadContainer;
import com.redsparkle.foe.capabilities.CapabilityRadiation.CapabilityRad;
import com.redsparkle.foe.capabilities.interfaces.IRad;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.sounds.ModSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = main.MODID, version = main.VERSION, useMetadata = true,acceptedMinecraftVersions = "[1.9.4,)")
public class main
{
    public static final String MODID = "fallout_equestria";
    public static final String VERSION = "0.0000000-VERY ALPHA";
    public static Configuration config;


    public void syncConfig() {
        if (config.hasChanged())
            config.save();
    }

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

        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        syncConfig();
        final CreativeTabs Fallout_ammo = InitCreativeTabs.Fallout_ammo;
        final CreativeTabs Fallout_blocks = InitCreativeTabs.Fallout_blocks;
        final CreativeTabs Fallout_guns = InitCreativeTabs.Fallout_guns;
        final CreativeTabs Fallout_meds = InitCreativeTabs.Fallout_meds;
        final CreativeTabs Fallout_stats_blocks = InitCreativeTabs.Fallout_stats_blocks;

        ModSoundEvents.registerSounds();
        OBJLoader.INSTANCE.addDomain(MODID.toLowerCase());


        CapabilityManager.INSTANCE.register(IRad.class, new CapabilityRad<IRad>(), BaseRadContainer.class);


        if (event.getSide() == Side.CLIENT)
            clientPreInit();
    }
    private void clientPreInit() {
        ModBlocks.registerBlocks();
        ModBlocks.registerTileEntities();
        ModItems.registerItems();

    }

    private void init(FMLInitializationEvent event){
        if (event.getSide() == Side.CLIENT) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                    .register(Item.getItemFromBlock(RadiationBlock.instance), 0, new ModelResourceLocation(MODID.toLowerCase() + ":" + RadiationBlock.name, "inventory"));

        }
    }

    private void postInit(FMLPostInitializationEvent event) {

    }

    public static EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }

    public static IThreadListener getThreadFromContext(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity.getServer();
    }
}
