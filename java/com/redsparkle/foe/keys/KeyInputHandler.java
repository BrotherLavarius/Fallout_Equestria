package com.redsparkle.foe.keys;

import com.redsparkle.foe.gui.PipBuckGui;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunReload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * Created by NENYN on 1/12/2017.
 */
public class KeyInputHandler
{
    private static PipBuckGui pipBuckGui;
    public Integer activated = 0;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
         Minecraft mc = Minecraft.getMinecraft();
         EntityPlayerSP player = mc.player;
            if (keyHandler.reload.isPressed()){
                main.simpleNetworkWrapper.sendToServer(new MessageGunReload());
            }
            if (keyHandler.pipbuck.isPressed()){

                player.openGui(main.instance, 0, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);;


            }
    }




}