package com.redsparkle.foe.capa.spechial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 01.03.17.
 */
public interface ISpechialCapability {

    Integer addStreinght(Integer addStreinght);

    Integer removeStreinght(Integer removeStreinght);

    Integer setStreinght(Integer newStreinght);

    Integer getStreinght();

    Integer addPerception(Integer addPerception);

    Integer removePerception(Integer removePerception);

    Integer setPerception(Integer newPerception);

    Integer getPerception();


    Integer addEndurance(Integer addEndurance);

    Integer removeEndurance(Integer removeEndurance);

    Integer setEndurance(Integer newEndurance);

    Integer getEndurance();

    Integer addCharisma(Integer addCharisma);

    Integer removeCharisma(Integer removeCharisma);

    Integer setCharisma(Integer newCharisma);

    Integer getCharisma();

    Integer addIntelligence(Integer addIntelligence);

    Integer removeIntelligence(Integer removeIntelligence);

    Integer setIntelligence(Integer newtIntelligence);

    Integer getIntelligence();

    Integer addAgility(Integer addAgility);

    Integer removeAgility(Integer removeAgility);

    Integer setAgility(Integer newAgility);

    Integer getAgility();

    Integer addLuck(Integer addLuck);

    Integer removeLuck(Integer removeLuck);

    Integer setLuck(Integer newLuck);

    Integer getLuck();

    void update(EntityPlayer player, World world, TickEvent.Phase phase);

    void updateClient(EntityPlayer player);

}
