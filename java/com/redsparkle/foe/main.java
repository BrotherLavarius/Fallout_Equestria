package com.redsparkle.foe;


import com.google.common.collect.Lists;
import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.containers.TileEntitys.SparkleColaMachineTileEntity;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

@Mod(modid = main.MODID, version = main.VERSION, useMetadata = true)
public class main
{
    public static final String MODID = "fallout_equestria";
    public static final String VERSION = "0.0000000-VERY ALPHA";

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
        OBJLoader.INSTANCE.addDomain(MODID.toLowerCase());

        List<Block> blocks = Lists.newArrayList();
        blocks.add(SparkleColaMachineBlock.instance);
        blocks.add(RadiationBlock.instance);

        for(Block block : blocks)
        {
            GameRegistry.register(block);
            GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
        GameRegistry.registerTileEntity(SparkleColaMachineTileEntity.class, SparkleColaMachineBlock.name);


        if (event.getSide() == Side.CLIENT)
            clientPreInit();
    }

    private void clientPreInit() {
        Item SparkleColaMachineI = Item.getItemFromBlock(SparkleColaMachineBlock.instance);
        ModelLoader.setCustomModelResourceLocation(SparkleColaMachineI, 0, new ModelResourceLocation(MODID.toLowerCase() + ":" + SparkleColaMachineBlock.name, "inventory"));
    }

}
