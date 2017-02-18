package com.redsparkle.foe;

import com.redsparkle.foe.Init.BlockInit;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.Init.StartUpCommon;
import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.capa.RadsFactoryStorage;
import com.redsparkle.foe.events.EventHandlerInit;
import com.redsparkle.foe.events.EventHandlerPre;
import com.redsparkle.foe.items.guns.inits.bulletFiredGuns.EntityBullet;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by hoijima on 14.12.16.
 */
public abstract class CommonProxy {
    public void preInit() {
        System.out.println("FOE Initiating");
        System.out.println("WAR...");
        System.out.println("WAR NEVER CHANGES...");
        StartUpCommon.preInitCommon();
        BlockInit.preInitCommon();
        ItemInit.preInitCommon();
        // INIT Handler
        MinecraftForge.EVENT_BUS.register(new EventHandlerPre());
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/bullet"), EntityBullet.class, "Bullet", 13, main.instance, 20, 30, false);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/laser"), EntityBullet.class, "laser", 13, main.instance, 20, 30, false);




    }

    public void init() {
        StartUpCommon.InitCommon();
        BlockInit.InitCommon();
        ItemInit.InitCommon();
        CapabilityManager.INSTANCE.register(IRadiationCapability.class, new RadsFactoryStorage(), RadsFactoryProvider::new);
        MinecraftForge.EVENT_BUS.register(new EventHandlerInit());
    }

    public void postInit() {
        StartUpCommon.postInitCommon();
        BlockInit.postInitCommon();
        ItemInit.postInitCommon();
        //MinecraftForge.EVENT_BUS.register(new EventHandlerPost());
    }

    // helper to determine whether the given player is in creative mode
    //  not necessary for most examples
    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    /**
     * is this a dedicated server?
     *
     * @return true if this is a dedicated server, false otherwise
     */
    abstract public boolean isDedicatedServer();


}
