package com.redsparkle.foe;

import com.redsparkle.foe.Init.BlockInit;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.Init.StartUpCommon;
import com.redsparkle.foe.capa.FirtsTimeJoin.FTJFactoryProvider;
import com.redsparkle.foe.capa.FirtsTimeJoin.FTJFactoryStorage;
import com.redsparkle.foe.capa.FirtsTimeJoin.IFTJCapability;
import com.redsparkle.foe.capa.level.ILevelCapability;
import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.level.LevelFactoryStorage;
import com.redsparkle.foe.capa.rad.IRadiationCapability;
import com.redsparkle.foe.capa.rad.RadsFactoryProvider;
import com.redsparkle.foe.capa.rad.RadsFactoryStorage;
import com.redsparkle.foe.capa.skills.ISkillsCapability;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.capa.skills.SkillsFactoryStorage;
import com.redsparkle.foe.capa.spechial.ISpechialCapability;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.capa.spechial.SpechialFactoryStorage;
import com.redsparkle.foe.events.EventHandlerInit;
import com.redsparkle.foe.events.EventHandlerPre;
import com.redsparkle.foe.items.guns.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.spreadPellet_shotgun.*;
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

        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/bullet"), EntityBullet.class, "Bullet", 0, main.instance, 64, 10, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/laser"), EntityLaser.class, "laser", 1, main.instance, 64, 10, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/Flame"), EntityFlame.class, "Flame", 2, main.instance, 64, 10, true);


        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/Pellet"),      Pellet.class,  "Pellet", 3, main.instance, 64, 10, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/Pellet_one"),  Pellet_one.class,  "Pellet_one", 4, main.instance, 64, 10, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/Pellet_two"),  Pellet_two.class,  "Pellet_two", 5, main.instance, 64, 10, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/Pellet_tree"), Pellet_tree.class, "Pellet_tree", 6, main.instance, 64, 10, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/Pellet_four"), Pellet_four.class, "Pellet_four", 7, main.instance, 64, 10, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/Pellet_five"), Pellet_five.class, "Pellet_five", 8, main.instance, 64, 10, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GlobalNames.Domain + ":entity/Pellet_six"),  Pellet_six.class,  "Pellet_six", 9, main.instance, 64, 10, true);




    }

    public void init() {
        StartUpCommon.InitCommon();
        BlockInit.InitCommon();
        ItemInit.InitCommon();
        System.out.println("STARTING BOOTING CAPABILITY SYSTEM");
        CapabilityManager.INSTANCE.register(IRadiationCapability.class, new RadsFactoryStorage(), RadsFactoryProvider::new);
        System.out.println("RADS--------------CHECK!");
        CapabilityManager.INSTANCE.register(ISpechialCapability.class, new SpechialFactoryStorage(), () -> new SpechialFactoryProvider());
        System.out.println("S.P.E.C.H.I.A.L--------------CHECK!");
        CapabilityManager.INSTANCE.register(ISkillsCapability.class, new SkillsFactoryStorage(), SkillsFactoryProvider::new);
        System.out.println("SKILLS--------------CHECK!");
        CapabilityManager.INSTANCE.register(ILevelCapability.class, new LevelFactoryStorage(), LevelFactoryProvider::new);
        System.out.println("LEVELS--------------CHECK!");
        CapabilityManager.INSTANCE.register(IFTJCapability.class,new FTJFactoryStorage(), FTJFactoryProvider::new);
        System.out.println("FTJ--------------CHECK!");

        System.out.println("FINISHED BOOTING CAPABILITY SYSTEM");
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
