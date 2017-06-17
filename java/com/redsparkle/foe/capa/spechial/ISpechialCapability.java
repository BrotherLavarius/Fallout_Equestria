package com.redsparkle.foe.capa.spechial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 01.03.17.
 */
public interface ISpechialCapability {

    Integer setStreinght(Integer newStreinght);

    Integer getStreinght();

    Integer setPerception(Integer newPerception);

    Integer getPerception();


    Integer setEndurance(Integer newEndurance);

    Integer getEndurance();


    Integer setCharisma(Integer newCharisma);

    Integer getCharisma();


    Integer setIntelligence(Integer newtIntelligence);

    Integer getIntelligence();


    Integer setAgility(Integer newAgility);

    Integer getAgility();

    Integer setLuck(Integer newLuck);

    Integer getLuck();

    void setAll(Integer all);

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);

}
