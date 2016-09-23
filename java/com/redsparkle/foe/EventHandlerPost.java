package com.redsparkle.foe;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

;


public class EventHandlerPost {
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event){
        if(!event.isCancelable() && event.getType() == ElementType.HEALTH){
            Minecraft mc = Minecraft.getMinecraft();
            //if(!mc.thePlayer.capabilities.isCreativeMode){
            int posX = event.getResolution().getScaledWidth() /2;
            int posY = event.getResolution().getScaledHeight() - 49;

            mc.renderEngine.bindTexture(new ResourceLocation(main.MODID.toLowerCase(), "textures/gui/bar.png"));
            mc.ingameGUI.drawTexturedModalRect(posX,posY,0,0,89,9);
            mc.ingameGUI.drawTexturedModalRect(posX+3,posY+3,0,25,59,9);
            System.out.println("GuiInitialized");
            //}
        }
    }
}



