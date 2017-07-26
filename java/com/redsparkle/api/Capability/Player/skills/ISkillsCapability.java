package com.redsparkle.api.Capability.Player.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 15.05.17.
 */
public interface ISkillsCapability {
        /**
                Magic
                Melee_Weapons
                Firearms
                EneryWeapons
                Saddlebag_Guns
                Explosives
                Repair
                Medicine
                Lockpicking
                Science
                Sneak
                Barter
                Survival
        */

        Integer setMagic(Integer newMagic);
        Integer getMagic();

        Integer setMelee(Integer newMelee);
        Integer getMelee();

        Integer setFirearms(Integer newFirearms);
        Integer getFirearms();

        Integer setEnergyWeapons(Integer newEnergyWeapons);
        Integer getEnergyWeapons();


        Integer setSaddlebag_guns(Integer newSaddlebag_guns);
        Integer getSaddlebag_guns();


        Integer setExplosives(Integer newExplosives);
        Integer getExplosives();

        Integer setRepair(Integer newRepair);
        Integer getRepair();

        Integer setMedicine(Integer newMedicine);
        Integer getMedicine();

        Integer setLockpick(Integer newLockpick);
        Integer getLockpick();


        Integer setScience(Integer newScience);
        Integer getScience();

        Integer setSneak(Integer newSneak);
        Integer getSneak();

        Integer setBarter(Integer newBarter);
        Integer getBarter();

        Integer setSurvival(Integer newSurvival);
        Integer getSurvival();










    void setAll(Integer All);

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);
}
