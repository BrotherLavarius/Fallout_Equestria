package com.redsparkle.foe.capa.spechial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 01.03.17.
 */
public interface ISpechialCapability {

    public Integer addStreinght(Integer addStreinght);
    public Integer removeStreinght(Integer removeStreinght);
    public Integer setStreinght(Integer newStreinght);
    public Integer getStreinght();

    public Integer addPerception(Integer addPerception);
    public Integer removePerception(Integer removePerception);
    public Integer setPerception(Integer newPerception);
    public Integer getPerception();


    public Integer addEndurance(Integer addEndurance);
    public Integer removeEndurance(Integer removeEndurance);
    public Integer setEndurance(Integer newEndurance);
    public Integer getEndurance();

    public Integer addCharisma(Integer addCharisma);
    public Integer removeCharisma(Integer removeCharisma);
    public Integer setCharisma(Integer newCharisma);
    public Integer getCharisma();

    public Integer addIntelligence(Integer addIntelligence);
    public Integer removeIntelligence(Integer removeIntelligence);
    public Integer setIntelligence(Integer newtIntelligence);
    public Integer getIntelligence();

    public Integer addAgility(Integer addAgility);
    public Integer removeAgility(Integer removeAgility);
    public Integer setAgility(Integer newAgility);
    public Integer getAgility();

    public Integer addLuck(Integer addLuck);
    public Integer removeLuck(Integer removeLuck);
    public Integer setLuck(Integer newLuck);
    public Integer getLuck();

    public void update(EntityPlayer player, World world, TickEvent.Phase phase);

    public void updateClient(EntityPlayer player);

}
