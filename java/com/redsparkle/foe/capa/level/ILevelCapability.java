package com.redsparkle.foe.capa.level;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by hoijima on 08.09.16.
 */
public interface ILevelCapability {


    Integer addLevel(Integer addLevelLevel);

    Integer removeLevel(Integer removeLevelLevel);

    Integer getLevel();

    Integer setLevel(Integer newLevelLevel);

    Integer addProgress(Integer addProgressProgress);

    Integer removeProgress(Integer removeProgressProgress);

    Integer getProgress();

    Integer setProgress(Integer newProgressProgress);

    void updateClient(EntityPlayer player);


}
