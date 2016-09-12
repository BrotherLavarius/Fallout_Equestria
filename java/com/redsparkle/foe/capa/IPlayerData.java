package com.redsparkle.foe.capa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 12.09.16.
 */
public interface IPlayerData {
    public void update(EntityPlayer player, World world, TickEvent.Phase phase);

    public boolean hasChanged();

    public void onSendClientUpdate();

    //public IMessage createUpdateMessage();
}
