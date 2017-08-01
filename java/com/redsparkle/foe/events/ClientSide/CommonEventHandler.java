package com.redsparkle.foe.events.ClientSide;

import com.redsparkle.api.utils.RadioThreadManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by hoijima on 01.08.17.
 */
public class CommonEventHandler {
    @SubscribeEvent
    public void onExit(PlayerEvent.PlayerLoggedOutEvent e) {
        RadioThreadManager.StopPlayer();
    }
}
