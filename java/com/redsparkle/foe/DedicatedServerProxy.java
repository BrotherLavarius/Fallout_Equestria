package com.redsparkle.foe;

import com.redsparkle.foe.capa.skills.ISkillsCapability;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.capa.spechial.ISpechialCapability;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.network.MessageFireToClientServer;
import com.redsparkle.foe.network.MessageUpdateClientServerSPECHIAL;
import com.redsparkle.foe.network.MessageUpdateClientServerSkills;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * DedicatedServerProxy is used to set up the mod and start it running on dedicated servers.  It contains all the code that should run on the
 * dedicated servers.  This is almost never required.
 * For more background information see here http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
 */
public class DedicatedServerProxy extends CommonProxy {

    public static void handleSpechialMessage(MessageUpdateClientServerSPECHIAL message, EntityPlayerMP playerEntity) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            ISpechialCapability spechial = SpechialFactoryProvider.instanceFor(playerEntity);
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
    public static void handleSkillsMessage(MessageUpdateClientServerSkills message, EntityPlayerMP playerEntity) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            ISkillsCapability skills = SkillsFactoryProvider.instanceFor(playerEntity);
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


    /**
     * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
     */
    public void preInit() {
        super.preInit();
    }

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes,
     * send FMLInterModComms messages to other mods.
     */
    public void init() {
        super.init();
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    public void postInit() {
        super.postInit();
    }

    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            return entityPlayerMP.interactionManager.isCreative();
        }
        return false;
    }

    @Override
    public boolean isDedicatedServer() {
        return true;
    }


    public static void handleFireMessage(MessageFireToClientServer message) {
    }


}
