package com.redsparkle.api.capa.level;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by hoijima on 08.09.16.
 */
public interface ILevelCapability {


    Integer addLevel(Integer addLevel);

    Integer removeLevel(Integer removeLevel);

    void initNewplayer();

    Integer getLevel();

    Integer setLevel(Integer newLevel);

    Integer addProgress(Integer addProgress);

    Integer removeProgress(Integer removeProgress);

    Integer getProgress();

    Integer setProgress(Integer newProgress);

    void updateClient(EntityPlayer player);


}
