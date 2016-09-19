package com.redsparkle.foe;


import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsDefaultImpl;
import com.redsparkle.foe.capa.RadsFactoryProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandlerPre {


    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent.Entity event)
    {
        //Attach it! The resource location MUST be unique it's recommended that you tag it with your modid and what the cap is.
        event.addCapability(new ResourceLocation(MODID + ":Radiation_CAPABILITY"), new RadsFactoryProvider(new RadsDefaultImpl()));
        
    }
    @SubscribeEvent
    public void onRenderExperienceBar(RenderGameOverlayEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        ResourceLocation texturepath = new ResourceLocation(main.MODID.toLowerCase(), "textures/gui/bar.png");

        // We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
        // will return true from isCancelable.  If you call event.setCanceled(true) in
        // that case, the portion of rendering which this event represents will be canceled.
        // We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
        // false and that the eventType represents the ExperienceBar event.
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.HEALTH)
        {

        IRadiationCapability Radiation = mc.thePlayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null);


        int xPos = 2;
        int yPos = 2;
        // setting all color values to 1.0F will render the texture as it appears in your texture file
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        // Somewhere in Minecraft vanilla code it says to do this because of a lighting bug
        GL11.glDisable(GL11.GL_LIGHTING);

        // Bind your texture to the render engine
        mc.getTextureManager().bindTexture(texturepath);

        mc.ingameGUI.drawTexturedModalRect(xPos, yPos, 0,0, 83, 24);
        // Then draw the foreground; it's located just below the background in my
        // texture file, so it starts at x=0, y=4, is only 2 pixels thick and 50 length
        // Why y=4 and not y=5? Y starts at 0, so 0,1,2,3 = 4 pixels for the background

        // However, we want the length to be based on current mana, so we need a new variable:
        int manabarwidth = (int)(((float) Radiation.getRadiation() / 90 * 50));
        System.out.println("[GUI RADS] Current RADS bar width: " + manabarwidth);
        // Now we can draw our mana bar at yPos+1 so it centers in the background:
        mc.ingameGUI.drawTexturedModalRect(xPos +3, yPos, 0,25, manabarwidth, 9);
        }
    }
    
}
    
    