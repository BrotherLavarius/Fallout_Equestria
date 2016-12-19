package com.redsparkle.foe;


import com.redsparkle.foe.Init.CapabilityInit;
import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.events.EventHandlerInit;
import com.redsparkle.foe.events.EventHandlerPost;
import com.redsparkle.foe.events.EventHandlerPre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = main.MODID, version = main.VERSION)
public class main
{
    public static final String MODID = "fallout_equestria";
    public static final String VERSION = "0.0000000-VERY ALPHA";

    //public static final String GUIFACTORY

    @Mod.Instance(main.MODID)
    public static main instance;
    @SidedProxy(clientSide="com.redsparkle.foe.ClientOnlyProxy", serverSide="com.redsparkle.foe.DedicatedServerProxy")
    public static CommonProxy proxy;


    @CapabilityInject(IRadiationCapability.class)
    private static void capRegistered(Capability<IRadiationCapability> cap) {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        CapabilityInit.radRegistered();
        proxy.preInit();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(main.instance);

        proxy.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();

        System.out.println("I-----------------------------------I");
        System.out.println("   Fallout pack fully initialized    ");
        System.out.println("I-----------------------------------I");
    }




    /**
     * Prepend the name with the mod ID, suitable for ResourceLocations such as textures.
     * @param name
     * @return eg "minecraftbyexample:myblockname"
     */
    public static String prependModID(String name) {return MODID + ":" + name;}
}
