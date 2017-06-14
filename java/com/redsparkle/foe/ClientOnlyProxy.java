package com.redsparkle.foe;

import com.redsparkle.foe.Init.ClientOnlyStartup;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.capa.level.ILevelCapability;
import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.rad.IRadiationCapability;
import com.redsparkle.foe.capa.rad.RadsFactoryProvider;
import com.redsparkle.foe.capa.skills.ISkillsCapability;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.capa.spechial.ISpechialCapability;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.capa.water.IWaterCapability;
import com.redsparkle.foe.capa.water.WaterFactoryProvider;
import com.redsparkle.foe.keys.KeyInputHandler;
import com.redsparkle.foe.keys.keyHandler;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.MessageFireToClientServer;
import com.redsparkle.foe.network.MessageGunReloadReply;
import com.redsparkle.foe.network.MessageOpenGuiClient;
import com.redsparkle.foe.network.MessageUpdateSLSServerReplyOnDemand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by hoijima on 14.12.16.
 */
public class ClientOnlyProxy extends CommonProxy {

    public static Minecraft mc = Minecraft.getMinecraft();
    public static void handleRadMessage(MessageUpdateClientRads message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IRadiationCapability rad = RadsFactoryProvider.instanceFor(player);
            rad.setRadiation(message.radiation);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */

        });
    }

    public static void handleSpechialMessage(MessageUpdateClientServerSPECHIAL message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ISpechialCapability spechial = SpechialFactoryProvider.instanceFor(player);
            spechial.setStreinght(message.Streinght);
            spechial.setPerception(message.Perception);
            spechial.setEndurance(message.Endurance);
            spechial.setCharisma(message.Charisma);
            spechial.setIntelligence(message.Intelligence);
            spechial.setAgility(message.Agility);
            spechial.setLuck(message.Luck);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */

        });
    }
    public static void handleSkillsMessage(MessageUpdateClientServerSkills message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ISkillsCapability skills = SkillsFactoryProvider.instanceFor(player);
            skills.setBigGuns(message.BigGuns);
            skills.setSmallGuns(message.SmallGuns);
            skills.setEnergyWeapons(message.EnergyWeapons);
            skills.setExplosives(message.Explosives);
            skills.setMeleeWeapons(message.MeleeWeapons);
            skills.setUnarmed(message.Unarmed);
            skills.setMedicine(message.Medicine);
            skills.setLockpick(message.Lockpick);
            skills.setRepair(message.Repair);
            skills.setScience(message.Science);
            skills.setSneak(message.Sneak);
            skills.setBarter(message.Barter);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */

        });
    }

    public static void handleLevelMessage(MessageUpdateClientServerLevel message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ILevelCapability level = LevelFactoryProvider.instanceFor(player);
            level.setLevel(message.Level);
            level.setProgress(message.Progress);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */

        });

    }

    public static void handleGundMessageReload(MessageGunReloadReply message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            System.out.println("Sound int: " + message.soundname);
            EntityPlayer player = Minecraft.getMinecraft().player;
            World world = Minecraft.getMinecraft().world;
            if (message.soundname == 0) {
                world.playSound(player, player.getPosition(), SoundInit.tenMMClipOut, SoundCategory.HOSTILE, 1.0F, 1.0F);
                world.playSound(player, player.getPosition(), SoundInit.tenMMClipIn, SoundCategory.HOSTILE, 1.0F, 1.0F);
                world.playSound(player, player.getPosition(), SoundInit.tenMMReload, SoundCategory.HOSTILE, 1.0F, 1.0F);
            } else if (message.soundname == 1) {
                world.playSound(player, player.getPosition(), SoundInit.tenMMClipOut, SoundCategory.HOSTILE, 1.0F, 1.0F);
            } else if (message.soundname == 2) {
                world.playSound(player, player.getPosition(), SoundInit.laserPBoltOpen, SoundCategory.HOSTILE, 1.0F, 1.0F);
                world.playSound(player, player.getPosition(), SoundInit.laserPBoltMagIn, SoundCategory.HOSTILE, 1.0F, 1.0F);
                world.playSound(player, player.getPosition(), SoundInit.laserPBoltClose, SoundCategory.HOSTILE, 1.0F, 1.0F);
            } else if (message.soundname == 3) {
                world.playSound(player, player.getPosition(), SoundInit.laserPBoltOpen, SoundCategory.HOSTILE, 1.0F, 1.0F);

            }


        });
    }
    public static void handleLevelMessageOnDemand(MessageUpdateSLSServerReplyOnDemand message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ILevelCapability level = LevelFactoryProvider.instanceFor(player);
            player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).setLevel(message.Level);
            player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).setProgress(message.Progress);
            level.setLevel(message.Level);
            level.setProgress(message.Progress);
            System.out.println("player progress: "+player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getProgress() );
        });
    }
    public static void handleOpenGui(MessageOpenGuiClient message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            mc.player.openGui(main.instance, message.ID, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);
        });
    }

    //TODO: finish this class
    public static void handleFireMessage(MessageFireToClientServer message) {
    }

    public static void handleWaterMessage(MessageUpdateClientWater message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IWaterCapability water = WaterFactoryProvider.instanceFor(player);
            water.setWater(message.water);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */

        });
    }

    public void preInit() {
        super.preInit();
        ClientOnlyStartup.preInitClientOnly();
        keyHandler.register();
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());

    }

    public void Init() {
        super.init();
        main.simpleNetworkWrapper.registerMessage(MessageUpdateClientRads.Handler.class, MessageUpdateClientRads.class, main.RAIATION_CAPABILITY_MESSAGE_ID_CLIENT, Side.CLIENT);
        ClientOnlyStartup.initClientOnly();
    }

    public void postInit() {
        super.postInit();
        ClientOnlyStartup.postInitClientOnly();
    }

    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            return entityPlayerMP.interactionManager.isCreative();
        } else if (player instanceof EntityPlayerSP) {
            return Minecraft.getMinecraft().playerController.isInCreativeMode();
        }
        return false;
    }

    @Override
    public boolean isDedicatedServer() {
        return false;
    }



}


