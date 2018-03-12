package com.redsparkle.foe;

import com.redsparkle.foe.Init.InitCreativeTabs;
import com.redsparkle.foe.commands.ammo_fill;
import com.redsparkle.foe.commands.rpSkillCheck;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSPECHIAL;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSkills;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientTrigger_Item;
import com.redsparkle.foe.network.UnifiedMessage;
import com.sun.media.jfxmedia.logging.Logger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "fallout_equestria", name = "Fallout Equestria", version = "0.9.3.4", updateJSON = "https://fallout-equestria.tk/update/updates.json")
public class main {
    public static final String MODID = "fallout_equestria";
    public static final String VERSION = "0.9.3.4";
    public static SimpleNetworkWrapper simpleNetworkWrapper;    // used to transmit your network messages
    @Mod.Instance(main.MODID)
    public static main instance;
    @SidedProxy(clientSide = "com.redsparkle.foe.ClientOnlyProxy", serverSide = "com.redsparkle.foe.DedicatedServerProxy")
    public static CommonProxy proxy;
    /**
     * Prepend the name with the mod ID, suitable for ResourceLocations such as textures.
     *
     * @param name
     * @return eg "minecraftbyexample:myblockname"
     */

    final CreativeTabs Fallout_ammo = InitCreativeTabs.Fallout_ammo;
    final CreativeTabs Fallout_blocks = InitCreativeTabs.Fallout_blocks;
    final CreativeTabs Fallout_guns = InitCreativeTabs.Fallout_guns;
    final CreativeTabs Fallout_food = InitCreativeTabs.Fallout_Food;
    final CreativeTabs Fallout_meds = InitCreativeTabs.Fallout_meds;
    final CreativeTabs Fallout_stats_blocks = InitCreativeTabs.Fallout_stats_blocks;
    final CreativeTabs Fallout_stats_armor = InitCreativeTabs.Fallout_armor;
    public byte message_start_index = 100;

    @Mod.EventHandler
    public static void init(FMLServerStartingEvent event) {
        event.registerServerCommand(new rpSkillCheck());
        event.registerServerCommand(new ammo_fill());

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(main.instance);
        proxy.init();
        simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("FOE Network Channel");
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSPECHIAL.HandlerClient.class, MessageUpdateClientServerSPECHIAL.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSPECHIAL.HandlerServer.class, MessageUpdateClientServerSPECHIAL.class, message_start_index++, Side.SERVER);

        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.HandlerClient.class, MessageUpdateClientServerSkills.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.HandlerServer.class, MessageUpdateClientServerSkills.class, message_start_index++, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.ServerOnLVLUP.class, MessageUpdateClientServerSkills.class, message_start_index++, Side.SERVER);

        simpleNetworkWrapper.registerMessage(MessageUpdateClientTrigger_Item.HandlerClient.class, MessageUpdateClientTrigger_Item.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientTrigger_Item.HandlerServer.class, MessageUpdateClientTrigger_Item.class, message_start_index++, Side.SERVER);

        simpleNetworkWrapper.registerMessage(UnifiedMessage.HandlerClient.class, UnifiedMessage.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(UnifiedMessage.HandlerServer.class, UnifiedMessage.class, message_start_index++, Side.SERVER);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
        Logger.logMsg(Logger.INFO, "I-----------------------------------I");
        Logger.logMsg(Logger.INFO, "   Fallout pack fully initialized    ");
        Logger.logMsg(Logger.INFO, "I-----------------------------------I");
    }
}
