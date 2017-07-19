package com.redsparkle.api.capa.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 15.05.17.
 */
public interface ISkillsCapability {


    Integer setBigGuns(Integer newBigGuns);

    Integer getBigGuns();


    Integer setSmallGuns(Integer newSmallGuns);

    Integer getSmallGuns();


    Integer setEnergyWeapons(Integer newEnergyWeapons);

    Integer getEnergyWeapons();



    Integer setMeleeWeapons(Integer newMeleeWeapons);

    Integer getMeleeWeapons();


    Integer setUnarmed(Integer newUnarmed);

    Integer getUnarmed();


    Integer setLockpick(Integer newLockpick);

    Integer getLockpick();

//    Integer setMagic(Integer newMagic);
//    Integer getMagic();
//
//    Integer setMelee(Integer newMelee);
//    Integer getMelee();
//
//    Integer setGuns(Integer newGuns);
//    Integer getGuns();
//
//    Integer setEnergy_Weapons(Integer newEnergy_Weapons);
//    Integer getEnergy_Weapons();
//
//    Integer setSaddlebag_guns(Integer newSaddlebag_guns);
//    Integer getSaddlebag_guns();

    Integer setExplosives(Integer newExplosives);
    Integer getExplosives();

    Integer setRepair(Integer newRepair);
    Integer getRepair();

    Integer setMedicine(Integer newMedicine);
    Integer getMedicine();

    Integer setScience(Integer newScience);
    Integer getScience();

    Integer setSneak(Integer newSneak);
    Integer getSneak();

    Integer setBarter(Integer newBarter);
    Integer getBarter();

//    Integer setSurvival(Integer newSurvival);
//    Integer getSurvival();





    void setAll(Integer All);

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);
}
