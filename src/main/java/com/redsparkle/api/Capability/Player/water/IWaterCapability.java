package com.redsparkle.api.Capability.Player.water;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 08.09.16.
 */
public interface IWaterCapability {
    void addWater(Integer addWaterLevel);

    void removeWater(Integer removeWaterLevel);

    Integer maxWater(Integer maxWaterLevel);

    Integer getWater();

    void setWater(Integer newWaterLevel);

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);

    void timedRemoveWater(EntityPlayer player, TickEvent.Phase phase, Integer startCycle, Integer Cycles, Integer FortificationValue);

    void timedRemoveWater(Integer startCycle, Integer Cycles, Integer FortificationValue);
}
