package com.redsparkle.foe.capa.FirtsTimeJoin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 08.09.16.
 */
public interface IFTJCapability {

    Boolean setFTJ(boolean newFTJ);

    Boolean getFTJ();

}
