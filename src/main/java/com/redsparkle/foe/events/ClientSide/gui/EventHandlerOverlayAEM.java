package com.redsparkle.foe.events.ClientSide.gui;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.gui.Overlays.Rads_Overlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/**
 * Created by NENYN on 12/25/2016.
 */
public class EventHandlerOverlayAEM {
    @SubscribeEvent(receiveCanceled = true)
    public void onEvent(RenderGameOverlayEvent.Pre event) {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
        if (entityPlayerSP == null) return;  // just in case
        if (!entityPlayerSP.isCreative()) {
            boolean foundInHotbar = false;
            for (int i = 1; i < 5; i++) {
                if (entityPlayerSP.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(i).getItem() == ItemInit.aem &&
                        entityPlayerSP.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(0).getItem() != ItemInit.pipbuck) {
                    foundInHotbar = true;
                }
            }
            if (!foundInHotbar) return;
        }
        switch (event.getType()) {
            case HEALTH:
                new Rads_Overlay(Minecraft.getMinecraft());
                event.setCanceled(true);
                break;
            default: // If it's not one of the above cases, do nothing
                break;
        }
    }
}
