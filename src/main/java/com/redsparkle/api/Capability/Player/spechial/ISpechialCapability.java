package com.redsparkle.api.Capability.Player.spechial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 01.03.17.
 */
public interface ISpechialCapability {
    Integer getStreinght();

    void setStreinght(Integer newStreinght);

    Integer getPerception();

    void setPerception(Integer newPerception);

    Integer getEndurance();

    void setEndurance(Integer newEndurance);

    Integer getCharisma();

    void setCharisma(Integer newCharisma);

    Integer getIntelligence();

    void setIntelligence(Integer newtIntelligence);

    Integer getAgility();

    void setAgility(Integer newAgility);

    Integer getLuck();

    void setLuck(Integer newLuck);

    void setAll(Integer all);

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);
}
