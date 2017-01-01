package com.redsparkle.foe.gui;



import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.main;
import com.redsparkle.foe.utils.GlobalNames;
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
    private final static ResourceLocation overlayBarRad = new ResourceLocation(GlobalNames.Domain,"textures/gui/rads_hud_overlay.png");


    private final static int BAR_WIDTH = 103;
    private final static int BAR_HEIGHT = 26;

    private final static int RadBAR_WIDTH = 100;
    private final static int RadBAR_HEIGHT = 8;



    private Minecraft mc;

    public RadsOverlay(Minecraft mc){

        EntityPlayer player = mc.thePlayer;
        World world = mc.theWorld;
        int playerRadlevel = player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation();


        ScaledResolution scaled = new ScaledResolution(mc);
        int screenWidth = scaled.getScaledWidth();
        int screenHeight = scaled.getScaledHeight();

/* Saving the current state of OpenGL so that I can restore it when I'm done */
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

      /* I like to indent the code whenever I push. It helps me visualize what is
       * happening better. This is a personal preference though.
       */

      /* Set the rendering color to white */
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

      /* This method tells OpenGL to draw with the custom texture */
        mc.renderEngine.bindTexture(overlayBarRad);

        final int PositionX = screenWidth -75; // leftmost edge of the experience bar
        final int PositionY = screenHeight / 19+ BAR_HEIGHT;  // top of the experience bar

        GL11.glTranslatef(PositionX, PositionY - BAR_HEIGHT, 0);
        GL11.glScalef(0.76F,0.76F,0.76F);
        drawTexturedModalRect(0, 0, 0, 0, BAR_WIDTH, BAR_HEIGHT);

        GL11.glPushMatrix();
        if (playerRadlevel <= 1){

        } else if (playerRadlevel >= 1){
            drawTexturedModalRect(2,6,2,29,Math.round(0.1F*playerRadlevel),RadBAR_HEIGHT);
            GL11.glScalef(0.76F,0.76F,0.76F);
        }
        GL11.glPushMatrix();

        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}

