package com.redsparkle.foe.keys;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.gui.Menus.PipBuckGui;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunReload;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerLevel;
import com.redsparkle.foe.network.MessageUpdateSLSClientOnDemand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * Created by NENYN on 1/12/2017.
 */
public class KeyInputHandler {
    private static PipBuckGui pipBuckGui;
    public boolean activated = false;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        if (keyHandler.reload.isPressed()) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunReload());
        }
        if (keyHandler.pipbuck.isPressed()) {
            if(mc.player.inventory.hasItemStack(new ItemStack(ItemInit.pipbuck))) {
                main.simpleNetworkWrapper.sendToServer(new MessageUpdateSLSClientOnDemand());
                player.openGui(main.instance, 0, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);

            }


        }
    }


}