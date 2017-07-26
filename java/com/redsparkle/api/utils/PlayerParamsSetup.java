package com.redsparkle.api.utils;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
/**
 * Created by hoijima on 16.06.17.
 */
public class PlayerParamsSetup {
    public static void normalizer(EntityPlayer playerEntity) {
        ILevelCapability level = LevelFactoryProvider.instanceFor(playerEntity);
        ISpechialCapability spechial = SpechialFactoryProvider.instanceFor(playerEntity);
        Double HP_value = 20D + (15 * spechial.getStreinght() + (2 * spechial.getEndurance()) + level.getLevel() * (Math.round(spechial.getEndurance() / 2) + 2));
        playerEntity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(HP_value);
        playerEntity.heal(playerEntity.getMaxHealth());
    }
}
