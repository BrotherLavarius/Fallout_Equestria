package com.redsparkle.api.Capability.Player.level;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by hoijima on 08.09.16.
 */
public interface ILevelCapability {
    void addLevel(Integer addLevel);

    void removeLevel(Integer removeLevel);

    void initNewplayer();

    Integer getLevel();

    void setLevel(Integer newLevel);

    void addProgress(Integer addProgress);

    void removeProgress(Integer removeProgress);

    Integer getProgress();

    void setProgress(Integer newProgress);

    void updateClient(EntityPlayer player);
}
