package com.redsparkle.foe.events.gui;

import com.redsparkle.api.utils.GlobalItemArray_For_init;
import com.redsparkle.foe.gui.Overlays.RadsOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
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
            final int FIRST_HOTBAR_SLOT = 0;
            final int LAST_HOTBAR_SLOT_PLUS_ONE = FIRST_HOTBAR_SLOT + InventoryPlayer.getHotbarSize();
            for (int i = FIRST_HOTBAR_SLOT; i < LAST_HOTBAR_SLOT_PLUS_ONE; ++i) {
                ItemStack slotItemStack = entityPlayerSP.inventory.getStackInSlot(i);
                if (slotItemStack != null && slotItemStack.getItem() == GlobalItemArray_For_init.AllInit[2] && !(slotItemStack.getItem() == GlobalItemArray_For_init.AllInit[0])) {
                    foundInHotbar = true;
                    break;
                }
            }

            if (!foundInHotbar) return;
        }
        switch (event.getType()) {
            case HEALTH:
                new RadsOverlay(Minecraft.getMinecraft());
                event.setCanceled(true);
                break;
            default: // If it's not one of the above cases, do nothing
                break;
        }


    }
}
