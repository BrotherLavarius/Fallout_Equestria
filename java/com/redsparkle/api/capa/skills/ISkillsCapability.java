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


    Integer setExplosives(Integer newExplosives);

    Integer getExplosives();


    Integer setMeleeWeapons(Integer newMeleeWeapons);

    Integer getMeleeWeapons();


    Integer setUnarmed(Integer newUnarmed);

    Integer getUnarmed();


    Integer setMedicine(Integer newMedicine);

    Integer getMedicine();


    Integer setLockpick(Integer newLockpick);

    Integer getLockpick();


    Integer setRepair(Integer newRepair);

    Integer getRepair();


    Integer setScience(Integer newScience);

    Integer getScience();


    Integer setSneak(Integer newSneak);

    Integer getSneak();


    Integer setBarter(Integer newBarter);

    Integer getBarter();


    void setAll(Integer All);

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);
}
