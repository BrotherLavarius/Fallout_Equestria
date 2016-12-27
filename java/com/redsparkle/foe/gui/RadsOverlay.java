package com.redsparkle.foe.gui;



import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;


/**
 * Created by NENYN on 14.11.2016.
 */

public class RadsOverlay extends Gui {
    //private final static ResourceLocation RadiationBar = new ResourceLocation(main.MODID,            "/textures/gui/rads_hud_overlay.png");
    private Minecraft mc;

    public RadsOverlay(Minecraft mc) {
        this.mc = mc;
    }

    public void renderStatusBar(int screenWidth, int screenHeight) {
        EntityPlayer player = mc.thePlayer;
        Integer radtext = player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation();
        String text = "Rads:";
        int strw = mc.fontRendererObj.getStringWidth("Pitch: -90.00");
        int strh = mc.fontRendererObj.FONT_HEIGHT;

            /* Saving the current state of OpenGL so that I can restore it when I'm done */


      /* I like to indent the code whenever I push. It helps me visualize what is
       * happening better. This is a personal preference though.
       */

      /* Set the rendering color to white */

        mc.fontRendererObj.drawStringWithShadow(text + Integer.toString(radtext),screenWidth-20-strw, screenHeight - 20 - strh, 0x00FFFFFF);
    }
}
