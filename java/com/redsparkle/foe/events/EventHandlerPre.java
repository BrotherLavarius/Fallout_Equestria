package com.redsparkle.foe.events;


import com.redsparkle.foe.capa.rad.IRadiationCapability;
import com.redsparkle.foe.capa.rad.RadsFactoryProvider;
import com.redsparkle.foe.capa.skills.ISkillsCapability;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.capa.spechial.ISpechialCapability;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.redsparkle.foe.capa.rad.RadsFactoryProvider.RADIATION_CAPABILITY;
import static com.redsparkle.foe.capa.skills.SkillsFactoryProvider.SKILLS_CAPABILITY;
import static com.redsparkle.foe.capa.spechial.SpechialFactoryProvider.SPECHIAL_CAPABILITY;


/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandlerPre {


    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent.Entity event) {
        //Attach it! The resource location MUST be unique it's recommended that you tag it with your modid and what the cap is.
        if (!event.getEntity().hasCapability(RADIATION_CAPABILITY, null)) {
            event.addCapability(new ResourceLocation(main.MODID + ":Radiation_CAPABILITY"), new RadsFactoryProvider());
        }
        if (!event.getEntity().hasCapability(SPECHIAL_CAPABILITY, null)) {
            event.addCapability(new ResourceLocation(main.MODID + ":Spechial_CAPABILITY"), new SpechialFactoryProvider());
        }

        if (!event.getEntity().hasCapability(SKILLS_CAPABILITY, null)) {
            event.addCapability(new ResourceLocation(main.MODID + ":SKILLS_CAPABILITY"), new SkillsFactoryProvider());
        }

    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        //if (e.phase != TickEvent.Phase.END) return;
        updatePlayerRads(e.player);
        updatePlayerSpechial(e.player);
        updatePlayerSkills(e.player);
    }

    private void updatePlayerSpechial(EntityPlayer player) {
        if (!player.world.isRemote) {
            ISpechialCapability spe = player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY,null);
            spe.setAgility(spe.getAgility());
            spe.setCharisma(spe.getCharisma());
            spe.setEndurance(spe.getEndurance());
            spe.setIntelligence(spe.getIntelligence());
            spe.setLuck(spe.getLuck());
            spe.setPerception(spe.getPerception());
            spe.setStreinght(spe.getStreinght());
            spe.updateClient(player);
        }
    }
    private void updatePlayerSkills(EntityPlayer player) {
        if (!player.world.isRemote) {
            ISkillsCapability skill = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null);
            skill.setBigGuns(skill.getBigGuns());
            skill.setSmallGuns(skill.getSmallGuns());
            skill.setEnergyWeapons(skill.getEnergyWeapons());
            skill.setExplosives(skill.getExplosives());
            skill.setMeleeWeapons(skill.getMeleeWeapons());
            skill.setUnarmed(skill.getUnarmed());
            skill.setMedicine(skill.getMedicine());
            skill.setLockpick(skill.getLockpick());
            skill.setRepair(skill.getRepair());
            skill.setScience(skill.getScience());
            skill.setSneak(skill.getSneak());
            skill.setBarter(skill.getBarter());

            skill.updateClient(player);
        }
    }

    private void updatePlayerRads(EntityPlayer player) {
        if (!player.world.isRemote) {
            IRadiationCapability rad = player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null);
            rad.setRadiation(rad.getRadiation());
            rad.updateClient(player);
        }
    }

    private void onUpdate(TickEvent.WorldTickEvent event) {

    }
}
    
    