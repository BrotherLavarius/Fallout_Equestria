package com.redsparkle.foe;


import com.redsparkle.foe.network.MessageUpdateClientRads;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = main.MODID, version = main.VERSION)
public class main {
    public static final String MODID = "fallout_equestria";
    public static final String VERSION = "0.0000000-VERY ALPHA";
    public static final byte RAIATION_CAPABILITY_MESSAGE_ID_SERVER = 95;      // a unique ID for this message type.  It helps detect errors if you don't use zero!
    public static final byte RAIATION_CAPABILITY_MESSAGE_ID_CLIENT = 96;
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
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(main.instance);

        proxy.init();

        simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("FOE Network Channel");
        simpleNetworkWrapper.registerMessage(MessageUpdateClientRads.Handler.class, MessageUpdateClientRads.class, RAIATION_CAPABILITY_MESSAGE_ID_CLIENT, Side.CLIENT);


    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();

        System.out.println("I-----------------------------------I");
        System.out.println("   Fallout pack fully initialized    ");
        System.out.println("I-----------------------------------I");
    }
}
