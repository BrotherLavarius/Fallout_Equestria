package com.redsparkle.foe.capa.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 15.05.17.
 */
public interface ISkillsCapability {

    public Integer addBigGuns(Integer addBigGuns);
    public Integer removeBigGuns(Integer removeBigGuns);
    public Integer setBigGuns(Integer newBigGuns);
    public Integer getBigGuns();

    public Integer addSmallGuns(Integer addSmallGuns);
    public Integer removeSmallGuns(Integer removeSmallGuns);
    public Integer setSmallGuns(Integer newSmallGuns);
    public Integer getSmallGuns();

    public Integer addEnergyWeapons(Integer addEnergyWeapons);
    public Integer removeEnergyWeapons(Integer removeEnergyWeapons);
    public Integer setEnergyWeapons(Integer newEnergyWeapons);
    public Integer getEnergyWeapons();

    public Integer addExplosives(Integer addExplosives);
    public Integer removeExplosives(Integer removeExplosives);
    public Integer setExplosives(Integer newExplosives);
    public Integer getExplosives();

    public Integer addMeleeWeapons(Integer addMeleeWeapons);
    public Integer removeMeleeWeapons(Integer removeMeleeWeapons);
    public Integer setMeleeWeapons(Integer newMeleeWeapons);
    public Integer getMeleeWeapons();

    public Integer addUnarmed(Integer addUnarmed);
    public Integer removeUnarmed(Integer removeUnarmed);
    public Integer setUnarmed(Integer newUnarmed);
    public Integer getUnarmed();

    public Integer addMedicine(Integer addMedicine);
    public Integer removeMedicine(Integer removeMedicine);
    public Integer setMedicine(Integer newMedicine);
    public Integer getMedicine();

    public Integer addLockpick(Integer addLockpick);
    public Integer removeLockpick(Integer removeLockpick);
    public Integer setLockpick(Integer newLockpick);
    public Integer getLockpick();

    public Integer addRepair(Integer addRepair);
    public Integer removeRepair(Integer removeRepair);
    public Integer setRepair(Integer newRepair);
    public Integer getRepair();

    public Integer addScience(Integer addScience);
    public Integer removeScience(Integer removeScience);
    public Integer setScience(Integer newScience);
    public Integer getScience();

    public Integer addSneak(Integer addSneak);
    public Integer removeSneak(Integer removeSneak);
    public Integer setSneak(Integer newSneak);
    public Integer getSneak();

    public Integer addBarter(Integer addBarter);
    public Integer removeBarter(Integer removeBarter);
    public Integer setBarter(Integer newBarter);
    public Integer getBarter();


    public void update(EntityPlayer player, World world, TickEvent.Phase phase);

    public void updateClient(EntityPlayer player);
}
