package com.redsparkle.foe;

import com.redsparkle.foe.Init.*;
import com.redsparkle.foe.events.EventHandlerInit;
import com.redsparkle.foe.events.EventHandlerPost;
import com.redsparkle.foe.events.EventHandlerPre;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by hoijima on 14.12.16.
 */
public abstract class CommonProxy {
    public void preInit(){




        System.out.println("FOE Initiating");
        System.out.println("WAR...");
        System.out.println("WAR NEVER CHANGES...");
        StartUpCommon.preInitCommon();
        BlockInit.preInitCommon();
        ItemInit.preInitCommon();


        SoundInit.registerSounds();


        // INIT Handler
        MinecraftForge.EVENT_BUS.register(new EventHandlerPre());


    }
    public void init() {
        StartUpCommon.InitCommon();
        BlockInit.InitCommon();
        ItemInit.InitCommon();
        MinecraftForge.EVENT_BUS.register(new EventHandlerInit());
    }
    public void postInit(){
        StartUpCommon.postInitCommon();
        BlockInit.postInitCommon();
        ItemInit.postInitCommon();
        MinecraftForge.EVENT_BUS.register(new EventHandlerPost());
    }

    // helper to determine whether the given player is in creative mode
    //  not necessary for most examples
    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    /**
     * is this a dedicated server?
     * @return true if this is a dedicated server, false otherwise
     */
    abstract public boolean isDedicatedServer();
}
