package com.redsparkle.foe.capa.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 15.05.17.
 */
public interface ISkillsCapability {

    Integer addBigGuns(Integer addBigGuns);
    Integer removeBigGuns(Integer removeBigGuns);
    Integer setBigGuns(Integer newBigGuns);
    Integer getBigGuns();

    Integer addSmallGuns(Integer addSmallGuns);
    Integer removeSmallGuns(Integer removeSmallGuns);
    Integer setSmallGuns(Integer newSmallGuns);
    Integer getSmallGuns();

    Integer addEnergyWeapons(Integer addEnergyWeapons);
    Integer removeEnergyWeapons(Integer removeEnergyWeapons);
    Integer setEnergyWeapons(Integer newEnergyWeapons);
    Integer getEnergyWeapons();

    Integer addExplosives(Integer addExplosives);
    Integer removeExplosives(Integer removeExplosives);
    Integer setExplosives(Integer newExplosives);
    Integer getExplosives();

    Integer addMeleeWeapons(Integer addMeleeWeapons);
    Integer removeMeleeWeapons(Integer removeMeleeWeapons);
    Integer setMeleeWeapons(Integer newMeleeWeapons);
    Integer getMeleeWeapons();

    Integer addUnarmed(Integer addUnarmed);
    Integer removeUnarmed(Integer removeUnarmed);
    Integer setUnarmed(Integer newUnarmed);
    Integer getUnarmed();

    Integer addMedicine(Integer addMedicine);
    Integer removeMedicine(Integer removeMedicine);
    Integer setMedicine(Integer newMedicine);
    Integer getMedicine();

    Integer addLockpick(Integer addLockpick);
    Integer removeLockpick(Integer removeLockpick);
    Integer setLockpick(Integer newLockpick);
    Integer getLockpick();

    Integer addRepair(Integer addRepair);
    Integer removeRepair(Integer removeRepair);
    Integer setRepair(Integer newRepair);
    Integer getRepair();

    Integer addScience(Integer addScience);
    Integer removeScience(Integer removeScience);
    Integer setScience(Integer newScience);
    Integer getScience();

    Integer addSneak(Integer addSneak);
    Integer removeSneak(Integer removeSneak);
    Integer setSneak(Integer newSneak);
    Integer getSneak();

    Integer addBarter(Integer addBarter);
    Integer removeBarter(Integer removeBarter);
    Integer setBarter(Integer newBarter);
    Integer getBarter();


    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);
}
