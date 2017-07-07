package com.redsparkle.api.capa.rad;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 08.09.16.
 */
public interface IRadiationCapability {


    Integer addRadiation(Integer addRadiationLevel);

    Integer removeRadiation(Integer removeRadiationLevel);

    Integer getRadiation();

    Integer setRadiation(Integer newRadiationLevel);

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);

    void timedRemoveRad(EntityPlayer player, TickEvent.Phase phase, Integer startCycle, Integer Cycles, Integer FortificationValue);

}
