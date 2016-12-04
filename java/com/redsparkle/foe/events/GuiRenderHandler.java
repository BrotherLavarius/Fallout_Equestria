package com.redsparkle.foe.events;

import com.redsparkle.foe.gui.RadsOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.MinecraftDummyContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by NENYN on 04.12.2016.
 */
public class GuiRenderHandler {

    public EntityPlayer player;

    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event) {
            Minecraft mc = Minecraft.getMinecraft();
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL){
            new RadsOverlay(Minecraft.getMinecraft(), player);

        }
    }

}
