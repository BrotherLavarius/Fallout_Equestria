package com.redsparkle.foe.capa.spechial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 01.03.17.
 */
public interface ISpechialInterface {

    public Integer addStreinght(Integer addStreinght);
    public Integer addPerception(Integer addPerception);
    public Integer addEndurance(Integer addEndurance);
    public Integer addCharisma(Integer addCharisma);
    public Integer addIntelligence(Integer addIntelligence);
    public Integer addAgility(Integer addAgility);
    public Integer addLuck(Integer addLuck);

    public Integer removeStreinght(Integer removeStreinght);
    public Integer removePerception(Integer removePerception);
    public Integer removeEndurance(Integer removeEndurance);
    public Integer removeCharisma(Integer removeCharisma);
    public Integer removeIntelligence(Integer removeIntelligence);
    public Integer removeAgility(Integer removeAgility);
    public Integer removeLuck(Integer removeLuck);

    public Integer setStreinght(Integer setStreinght);
    public Integer setPerception(Integer setPerception);
    public Integer setEndurance(Integer setEndurance);
    public Integer setCharisma(Integer setCharisma);
    public Integer setIntelligence(Integer setIntelligence);
    public Integer setAgility(Integer setAgility);
    public Integer setLuck(Integer setLuck);

    public Integer getStreinght();
    public Integer getPerception();
    public Integer getEndurance();
    public Integer getCharisma();
    public Integer getIntelligence();
    public Integer getAgility();
    public Integer getLuck();

    public void update(EntityPlayer player, World world, TickEvent.Phase phase);

    public void updateClient(EntityPlayer player);

}
