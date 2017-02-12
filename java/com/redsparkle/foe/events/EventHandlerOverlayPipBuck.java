package com.redsparkle.foe.events;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.gui.APBar;
import com.redsparkle.foe.gui.PipBuckGui;
import com.redsparkle.foe.gui.RadsOverlay;
import com.redsparkle.foe.playerrenderers.GunRender;
import com.redsparkle.foe.playerrenderers.LayerGunsRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by NENYN on 12/25/2016.
 */
public class EventHandlerOverlayPipBuck {


    /*
    ##############################################################################
            GUI EVENTS SECTION
    ##############################################################################
    */

    private PipBuckGui statusBarRenderer;

    public EventHandlerOverlayPipBuck(PipBuckGui i_HUDrenderer) {
        statusBarRenderer = i_HUDrenderer;
    }

    /* The RenderGameOverlayEvent.Pre event is called before each game overlay element is
   * rendered. It is called multiple times. A list of existing overlay elements can be found
   * in net.minecraftforge.client.event.RenderGameOverlayEvent.
   *
   * If you want something to be rendered under an existing vanilla element, you would render
   * it here.
   *
   * Note that you can entirely remove the vanilla rendering by cancelling the event here.
   */

    @SubscribeEvent(receiveCanceled = true)
    public void onEvent(RenderGameOverlayEvent.Pre event) {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
        if (entityPlayerSP == null) return;  // just in case
        if (!entityPlayerSP.isCreative()) {
            // look for the ItemHUDactivator in the hotbar.  If not present, return without changing the HUD.
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
        }


        switch (event.getType()) {
            case HEALTH:
                statusBarRenderer.renderStatusBar(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());        /* Call a helper method so that this method stays organized */
                new RadsOverlay(Minecraft.getMinecraft());
        /* Don't render the vanilla heart bar */
                event.setCanceled(true);
                break;

            case ARMOR:
        /* Don't render the vanilla armor bar, it's part of the status bar in the HEALTH event */
                event.setCanceled(true);
                break;
            case FOOD:
                // Don't render the vanilla FOOD bar
                new APBar(Minecraft.getMinecraft());
                event.setCanceled(true);
                break;
            case EXPERIENCE:
                // Don't render the vanilla EXPERIENCE bar
                event.setCanceled(true);
                break;

            default: // If it's not one of the above cases, do nothing
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
