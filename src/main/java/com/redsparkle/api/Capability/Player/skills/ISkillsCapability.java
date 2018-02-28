package com.redsparkle.api.Capability.Player.skills;

import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;

/**
 * Created by hoijima on 15.05.17.
 */
public interface ISkillsCapability {

    Integer getAttribute(String name);

    void setAttribute(String name, Integer amount);

    void setAttribute(HashMap<String, Integer> new_map);

    HashMap<String, Integer> getFullMap();

    Boolean hasChanged();

    void updateClient(EntityPlayer player);
}
