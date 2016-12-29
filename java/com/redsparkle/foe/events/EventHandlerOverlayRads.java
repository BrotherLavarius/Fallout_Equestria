package com.redsparkle.foe.events;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.gui.PipBuckGui;
import com.redsparkle.foe.gui.RadsOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by NENYN on 12/25/2016.
 */
public class EventHandlerOverlayRads {
    /*
    ##############################################################################
            GUI EVENTS SECTION
    ##############################################################################
    */

    public EventHandlerOverlayRads(RadsOverlay i_HUDrenderer)
    {
        radsStatusBarRenderer = i_HUDrenderer;
    }
    private RadsOverlay radsStatusBarRenderer;

    public void onEvent(RenderGameOverlayEvent.Post event) {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().thePlayer;
        if (entityPlayerSP == null) return;  // just in case
        if (!entityPlayerSP.isCreative()) {
            boolean foundInHotbar = false;
            final int FIRST_HOTBAR_SLOT = 0;
            final int LAST_HOTBAR_SLOT_PLUS_ONE = FIRST_HOTBAR_SLOT + entityPlayerSP.inventory.getHotbarSize();
            for (int i = FIRST_HOTBAR_SLOT; i < LAST_HOTBAR_SLOT_PLUS_ONE; ++i) {
                ItemStack slotItemStack = entityPlayerSP.inventory.getStackInSlot(i);
                if (slotItemStack != null && slotItemStack.getItem() == ItemInit.pipbuck) {
                    foundInHotbar = true;
                    break;
                }
            }
            if (!foundInHotbar) return;
            new RadsOverlay(Minecraft.getMinecraft());
            //radsStatusBarRenderer.renderStatusBar(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());        /* Call a helper method so that this method stays organized */


        }

    }
}


