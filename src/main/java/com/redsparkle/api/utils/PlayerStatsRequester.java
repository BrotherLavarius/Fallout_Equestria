package com.redsparkle.api.utils;

import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.Skill_names;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoijima on 17.06.17.
 */
public class PlayerStatsRequester {
    public static List<Integer> skills(EntityPlayer player) {
        ISkillsCapability skill_cap = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null);
        List<Integer> skills = new ArrayList<>();
        for (Skill_names names : Skill_names.values()) {
            skills.add(skill_cap.getAttribute(names.getName()));
        }

        return skills;
    }

    public static Integer[] lvl(EntityPlayer player) {
        Integer[] playerParams = new Integer[]{
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel(),
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress()
        };
        return playerParams;
    }

    public static Integer[] spechials(EntityPlayer player) {
        Integer[] spechials = {
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getPerception(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getEndurance(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getCharisma(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getIntelligence(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getAgility(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getLuck()
        };
        return spechials;
    }

    public static Integer[] additionalStats(EntityPlayer player) {
        Integer[] player_RAD_WATER = new Integer[]{
                player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation(),
                player.getCapability(WaterFactoryProvider.WATER_CAPABILITY, null).getWater()
        };
        return player_RAD_WATER;
    }
}
