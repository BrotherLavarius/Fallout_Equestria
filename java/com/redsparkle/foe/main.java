package com.redsparkle.foe;


import com.redsparkle.foe.commands.rpSkillCheck;
import com.redsparkle.api.handlers.GuiHandler;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.*;
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
    public static final byte RAIATION_CAPABILITY_MESSAGE_ID_CLIENT = 96;
    public static final byte RELOAD_MESSAGE_ID_SERVER = 97;
    public static final byte RELOAD_MESSAGE_ID_CLIENT = 98;
    public static final byte SPECHIAL_MESSAGE_ID_CLIENT = 99;
    public static final byte SPECHIAL_MESSAGE_ID_SERVER = 100;
    public static final byte SKILLS_MESSAGE_ID_CLIENT = 101;
    public static final byte SKILLS_MESSAGE_ID_SERVER = 102;
    public static final byte LEVEL_MESSAGE_ID_CLIENT = 103;
    public static final byte LEVEL_MESSAGE_ID_SERVER = 104;
    public static final byte LEVEL_ONDEMAND_MESSAGE_ID_CLIENT = 105;
    public static final byte LEVEL_ONDEMAND_MESSAGE_ID_SERVER = 106;
    public static final byte LVLUP_MESSAGE_ID_CLIENT = 107;
    public static final byte FIRTSTIME_MESSAGE_ID_CLIENT = 108;
    public static final byte FIRTSTIME_MESSAGE_ID_SERVER = 109;
    public static final byte WATER_CAPABILITY_MESSAGE_ID_CLIENT = 110;

    public static final byte FIRE_MESSAGE_ID_CLIENT = 111;
    public static final byte FIRE_MESSAGE_ID_SERVER = 112;

    public static final byte SYNC_INV_CLIENTS = 113;
    public static final byte SYNC_INV_SERVER = 114;


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

    public static String prependModID(String name) {
        return MODID + ":" + name;
    }

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
        NetworkRegistry.INSTANCE.registerGuiHandler(main.instance, new GuiHandler());

        System.out.println("STARTING BOOTING NETWORK MESSAGES");
        simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("FOE Network Channel");
        simpleNetworkWrapper.registerMessage(MessageUpdateClientRads.Handler.class, MessageUpdateClientRads.class, RAIATION_CAPABILITY_MESSAGE_ID_CLIENT, Side.CLIENT);
        System.out.println("RADS------CHECK");

        simpleNetworkWrapper.registerMessage(MessageUpdateClientWater.HandlerClient.class, MessageUpdateClientWater.class, WATER_CAPABILITY_MESSAGE_ID_CLIENT, Side.CLIENT);
        System.out.println("WATER------CHECK");


        simpleNetworkWrapper.registerMessage(MessageGunReload.HandlerServer.class, MessageGunReload.class, RELOAD_MESSAGE_ID_SERVER, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageGunReloadReply.HandlerClient.class, MessageGunReloadReply.class, RELOAD_MESSAGE_ID_CLIENT, Side.CLIENT);
        System.out.println("WEAPON RELOADS------CHECK");
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSPECHIAL.HandlerClient.class, MessageUpdateClientServerSPECHIAL.class, SPECHIAL_MESSAGE_ID_CLIENT, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSPECHIAL.HandlerServer.class, MessageUpdateClientServerSPECHIAL.class, SPECHIAL_MESSAGE_ID_SERVER, Side.SERVER);


        System.out.println("S.P.E.C.H.I.A.L------CHECK");

//
//          simpleNetworkWrapper.registerMessage(MessageFireToClientServer.HandlerClient.class,MessageFireToClientServer.class,FIRE_MESSAGE_ID_CLIENT,Side.CLIENT);
//          simpleNetworkWrapper.registerMessage(MessageFireToClientServer.HandlerServer.class,MessageFireToClientServer.class,FIRE_MESSAGE_ID_SERVER,Side.SERVER);

        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.HandlerClient.class, MessageUpdateClientServerSkills.class, SKILLS_MESSAGE_ID_CLIENT, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.HandlerServer.class, MessageUpdateClientServerSkills.class, SKILLS_MESSAGE_ID_SERVER, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerSkills.ServerOnLVLUP.class, MessageUpdateClientServerSkills.class, LVLUP_MESSAGE_ID_CLIENT, Side.SERVER);

        System.out.println("SKILLS-----CHECK");

        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerLevel.HandlerClient.class, MessageUpdateClientServerLevel.class, LEVEL_MESSAGE_ID_CLIENT, Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageUpdateClientServerLevel.HandlerServer.class, MessageUpdateClientServerLevel.class, LEVEL_MESSAGE_ID_SERVER, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageUpdateSLSClientOnDemand.serverSideHandler.class, MessageUpdateSLSClientOnDemand.class, LEVEL_ONDEMAND_MESSAGE_ID_CLIENT, Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageUpdateSLSServerReplyOnDemand.HandlerClient.class, MessageUpdateSLSServerReplyOnDemand.class, LEVEL_ONDEMAND_MESSAGE_ID_SERVER, Side.CLIENT);


        System.out.println("LEVELS------CHECK");

        simpleNetworkWrapper.registerMessage(MessageOpenGuiClient.HandlerClient.class,MessageOpenGuiClient.class,FIRTSTIME_MESSAGE_ID_CLIENT,Side.SERVER);
        simpleNetworkWrapper.registerMessage(MessageOpenGuiClient.HandleServer.class, MessageOpenGuiClient.class, FIRTSTIME_MESSAGE_ID_SERVER, Side.CLIENT);
        System.out.println("GUI TRIGGERS ------CHECK");

        simpleNetworkWrapper.registerMessage(MessageAdvInvSync.HandlerClient.class,MessageAdvInvSync.class,SYNC_INV_CLIENTS,Side.CLIENT);
        simpleNetworkWrapper.registerMessage(MessageAdvInvSync.HandlerServer.class,MessageAdvInvSync.class,SYNC_INV_SERVER,Side.SERVER);

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
