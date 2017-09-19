package com.redsparkle.foe.events.ClientSide.gui;

import com.redsparkle.foe.gui.Overlays.mode_icons;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/**
 * Created by hoijima on 02.06.17.
 */
public class EventPlayerGuiHandler {
    @SubscribeEvent(receiveCanceled = true)
    public void onEvent(RenderGameOverlayEvent.Pre event) {

        switch (event.getType()) {
            case HEALTH:
        /* Don't render the vanilla heart bar */
                new mode_icons(Minecraft.getMinecraft());

                event.setCanceled(true);
                break;
            case ARMOR:
        /* Don't render the vanilla armor bar, it's part of the status bar in the HEALTH event */
                event.setCanceled(true);
                break;
            case FOOD:
                event.setCanceled(true);
                break;
            case EXPERIENCE:
                event.setCanceled(true);
                break;
            case AIR:
                event.setCanceled(true);
                break;
            case BOSSHEALTH:
                event.setCanceled(true);
                break;
            case BOSSINFO:
                event.setCanceled(true);
                break;
            default: // If i
                // t's not one of the above cases, do nothing
                break;
        }

    }
    /* The RenderGameOverlayEvent.Post event is called after each game overlay element is rendered.
 * Similar to the RenderGameOverlayEvent.Pre event, it is called multiple times.
 *
 * If you want something to be rendered over an existing vanilla element, you would render
 * it here.
 */
    @SubscribeEvent(receiveCanceled = true)
    public void onEvent(RenderGameOverlayEvent.Post event) {
        switch (event.getType()) {
            case HEALTH:

                break;
            default: // If it's not one of the above cases, do nothing
                break;
        }
    }
}
