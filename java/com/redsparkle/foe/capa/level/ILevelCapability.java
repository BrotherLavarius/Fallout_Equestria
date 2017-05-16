package com.redsparkle.foe.capa.level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 08.09.16.
 */
public interface ILevelCapability {


    public Integer addLevel(Integer addLevelLevel);

    public Integer removeLevel(Integer removeLevelLevel);

    public Integer getLevel();

    public Integer setLevel(Integer newLevelLevel);

    public Integer addProgress(Integer addProgressProgress);

    public Integer removeProgress(Integer removeProgressProgress);

    public Integer getProgress();

    public Integer setProgress(Integer newProgressProgress);

    public void updateClient(EntityPlayer player);


}
