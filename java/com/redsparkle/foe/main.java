package com.redsparkle.foe;

import com.redsparkle.foe.Init.InitCreativeTabs;
import com.redsparkle.foe.commands.rpSkillCheck;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.*;
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
@Mod(modid = main.MODID, version = main.VERSION, updateJSON = "https://fallout-equestria.tk/update/updates.json")
public class main {
    public static final String MODID = "fallout_equestria";
    public static final String VERSION = "0.1";
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
    }
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(main.instance);
        proxy.init();
        System.out.println("STARTING BOOTING NETWORK MESSAGES");
        simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("FOE Network Channel");
        simpleNetworkWrapper.registerMessage(MessageUpdateClientRads.Handler.class, MessageUpdateClientRads.class, message_start_index, Side.CLIENT);
        System.out.println("RADS------CHECK");
        simpleNetworkWrapper.registerMessage(MessageUpdateClientWater.HandlerClient.class, MessageUpdateClientWater.class, message_start_index++, Side.CLIENT);
        System.out.println("WATER------CHECK");
        simpleNetworkWrapper.registerMessage(MessageGunReload.HandlerServer.class, MessageGunReload.class, message_start_index++, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageGunReloadReply.HandlerClient.class, MessageGunReloadReply.class, message_start_index++, Side.CLIENT);
        System.out.println("WEAPON RELOADS------CHECK");
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSPECHIAL.HandlerClient.class, MessageUpdateClientServerSPECHIAL.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSPECHIAL.HandlerServer.class, MessageUpdateClientServerSPECHIAL.class, message_start_index++, Side.SERVER);
        System.out.println("S.P.E.C.H.I.A.L------CHECK");
//
          //simpleNetworkWrapper.registerMessage(MessageFireToClientServer.HandlerClient.class,MessageFireToClientServer.class,message_start_index++,Side.CLIENT);
          simpleNetworkWrapper.registerMessage(MessageGunFire.Handler.class,MessageGunFire.class,message_start_index++,Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.HandlerClient.class, MessageUpdateClientServerSkills.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.HandlerServer.class, MessageUpdateClientServerSkills.class, message_start_index++, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.ServerOnLVLUP.class, MessageUpdateClientServerSkills.class, message_start_index++, Side.SERVER);
        System.out.println("SKILLS-----CHECK");
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerLevel.HandlerClient.class, MessageUpdateClientServerLevel.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerLevel.HandlerServer.class, MessageUpdateClientServerLevel.class, message_start_index++, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageUpdateSLSServerReplyOnDemand.HandlerClient.class, MessageUpdateSLSServerReplyOnDemand.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateSLSClientOnDemand.serverSideHandler.class, MessageUpdateSLSClientOnDemand.class, message_start_index++, Side.SERVER);
        System.out.println("LEVELS------CHECK");
        simpleNetworkWrapper.registerMessage(MessageOpenGuiClient.HandlerClient.class, MessageOpenGuiClient.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageOpenGuiClient.HandleServer.class, MessageOpenGuiClient.class, message_start_index++, Side.SERVER);
        System.out.println("GUI TRIGGERS ------CHECK");
        simpleNetworkWrapper.registerMessage(MessageAdvInv_SYNC.HandlerClient.class, MessageAdvInv_SYNC.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageAdvInv_SYNC.HandlerServer.class, MessageAdvInv_SYNC.class, message_start_index++, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageAdvInv_SLOT.HandlerServer.class, MessageAdvInv_SLOT.class, message_start_index++, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageAdvInv.HandlerServer.class, MessageAdvInv.class, message_start_index++, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageUpdateAmmoHolders.HandlerClient.class,MessageUpdateAmmoHolders.class,message_start_index++,Side.CLIENT);

        simpleNetworkWrapper.registerMessage(MessageUpdateClientTrigger_Item.HandlerClient.class, MessageUpdateClientTrigger_Item.class, message_start_index++, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientTrigger_Item.HandlerServer.class, MessageUpdateClientTrigger_Item.class, message_start_index++, Side.SERVER);


        System.out.println("FINISHED BOOTING NETWORK MESSAGES");
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
        System.out.println("I-----------------------------------I");
        System.out.println("   Fallout pack fully initialized    ");
        System.out.println("I-----------------------------------I");
    }
}
