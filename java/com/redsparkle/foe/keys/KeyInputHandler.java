package com.redsparkle.foe.keys;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunReload;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * Created by NENYN on 1/12/2017.
 */
public class KeyInputHandler
{
    public EntityPlayer player = Minecraft.getMinecraft().player;
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
            if (keyHandler.reload.isPressed()){
                main.simpleNetworkWrapper.sendToServer(new MessageGunReload());
            }
            if (keyHandler.pipbuck.isPressed()){

            }
    }




}