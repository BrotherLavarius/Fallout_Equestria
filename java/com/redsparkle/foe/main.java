package com.redsparkle.foe;


import com.redsparkle.foe.Init.ClientOnlyStartup;
import com.redsparkle.foe.Init.ModBlocks;
import com.redsparkle.foe.Init.ModItems;
import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsDefaultImpl;
import com.redsparkle.foe.capa.RadsFactoryStorage;
import com.redsparkle.foe.events.EventHandlerInit;
import com.redsparkle.foe.events.EventHandlerPost;
import com.redsparkle.foe.events.EventHandlerPre;
import com.redsparkle.foe.network.*;
import com.redsparkle.foe.sounds.ModSoundEvents;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = main.MODID, version = main.VERSION, useMetadata = true,acceptedMinecraftVersions = "[1.9.4,)")
public class main
{
    public static final String MODID = "fallout_equestria";
    public static final String VERSION = "0.0000000-VERY ALPHA";
    public static Configuration config;

    @CapabilityInject(IRadiationCapability.class)
    private static void capRegistered(Capability<IRadiationCapability> cap) {
        System.out.println("I-----------------------------------I");
        System.out.println(" RadiationCapability was initialized ");
        System.out.println("        YAY FOR THOSE ATOMS!         ");
        System.out.println("        You will die, enjoy          ");
        System.out.println("I-----------------------------------I");
    }

    public static EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }

    public static IThreadListener getThreadFromContext(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity.getServer();
    }

    public void syncConfig() {
        if (config.hasChanged())
            config.save();
    }

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        System.out.println("FOE PACK LOADING");
        System.out.println("WAR...");
        System.out.println("WAR NEVER CHANGES...");

        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        syncConfig();


        ModBlocks.registerBlocks();
        ModBlocks.registerTileEntities();
        ModItems.registerItems();
        ModSoundEvents.registerSounds();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        network.registerMessage(MyMessage.MyMessageHandler.class, MyMessage.class, 0, Side.SERVER);
        //network.registerMessage(RadiatonRefreshHandler.class, RadiationMessage.class, 1, Side.CLIENT);
        network.registerMessage(TutorialMessageHandler.class, TutorialMessage.class, 2, Side.SERVER);

        CapabilityManager.INSTANCE.register(IRadiationCapability.class, new RadsFactoryStorage(), RadsDefaultImpl.class);
        MinecraftForge.EVENT_BUS.register(new EventHandlerPre());

        if (event.getSide() == Side.CLIENT){
            ClientOnlyStartup.preInitClientOnly();}
            }

      private void init(FMLInitializationEvent event){
        if (event.getSide() == Side.CLIENT) {
            ClientOnlyStartup.initClientOnly();
        }
        MinecraftForge.EVENT_BUS.register(new EventHandlerInit());
    }

    private void postInit(FMLPostInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ClientOnlyStartup.postInitClientOnly();
        }
        MinecraftForge.EVENT_BUS.register(new EventHandlerPost());



        System.out.println("I-----------------------------------I");
        System.out.println("   Fallout pack fully initialized    ");
        System.out.println("I-----------------------------------I");
    }
}
