package com.redsparkle.foe.events.ClientSide.gui;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.gui.Overlays.Compass_Overlay;
import com.redsparkle.foe.gui.Overlays.Food_overlay;
import com.redsparkle.foe.gui.Overlays.Health_Overlay;
import com.redsparkle.foe.gui.Overlays.Rads_Overlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by NENYN on 12/25/2016.
 */
public class EventHandlerOverlayPipBuck {
    @SubscribeEvent(receiveCanceled = true)
    public void onEvent(RenderGameOverlayEvent.Pre event) {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
        if (entityPlayerSP == null) return;  // just in case
        if (!entityPlayerSP.isCreative()) {
            // look for the ItemHUDactivator in the hotbar.  If not present, return without changing the HUD.
            boolean foundInHotbar = false;
            if (entityPlayerSP.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(0).getItem() == ItemInit.pipbuck) {
                foundInHotbar = true;
            }
            if (!foundInHotbar) return;
        }
        switch (event.getType()) {
            case HEALTH:
                new Health_Overlay(Minecraft.getMinecraft(), event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());
                //statusBarRenderer.renderStatusBar(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());        /* Call a helper method so that this method stays organized */
                new Rads_Overlay(Minecraft.getMinecraft());
                new Food_overlay(Minecraft.getMinecraft());
                new Compass_Overlay(Minecraft.getMinecraft());
                event.setCanceled(true);
                break;
            default: // If it's not one of the above cases, do nothing
                break;
        }
    }
}
