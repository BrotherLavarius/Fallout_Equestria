package com.redsparkle.foe.capa.rads;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 08.09.16.
 */
public interface IRadiationCapability {


    public Integer addRadiation(Integer addRadiationLevel);

    public Integer removeRadiation(Integer removeRadiationLevel);

    public Integer getRadiation();

    public Integer setRadiation(Integer newRadiationLevel);

    public void update(EntityPlayer player, World world, TickEvent.Phase phase);

    public void updateClient(EntityPlayer player);

    public void timedRemoveRad(EntityPlayer player, TickEvent.Phase phase, Integer startCycle, Integer Cycles, Integer FortificationValue);

}
