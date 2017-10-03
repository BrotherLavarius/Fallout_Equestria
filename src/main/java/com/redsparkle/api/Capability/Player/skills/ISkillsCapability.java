package com.redsparkle.api.Capability.Player.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 15.05.17.
 */
public interface ISkillsCapability {
    Integer getMagic();

    /**
     * Magic
     * Melee_Weapons
     * Firearms
     * EneryWeapons
     * Saddlebag_Guns
     * Explosives
     * Repair
     * Medicine
     * Lockpicking
     * Science
     * Sneak
     * Barter
     * Survival
     */
    void setMagic(Integer newMagic);

    Integer getMelee();

    void setMelee(Integer newMelee);

    Integer getFirearms();

    void setFirearms(Integer newFirearms);

    Integer getEnergyWeapons();

    void setEnergyWeapons(Integer newEnergyWeapons);

    Integer getSaddlebag_guns();

    void setSaddlebag_guns(Integer newSaddlebag_guns);

    Integer getExplosives();

    void setExplosives(Integer newExplosives);

    Integer getRepair();

    void setRepair(Integer newRepair);

    Integer getMedicine();

    void setMedicine(Integer newMedicine);

    Integer getLockpick();

    void setLockpick(Integer newLockpick);

    Integer getScience();

    void setScience(Integer newScience);

    Integer getSneak();

    void setSneak(Integer newSneak);

    Integer getBarter();

    void setBarter(Integer newBarter);

    Integer getSurvival();

    void setSurvival(Integer newSurvival);

    void setAll(Integer All);

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);
}
