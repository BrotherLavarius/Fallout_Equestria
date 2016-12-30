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
    private final static ResourceLocation overlayBar = new ResourceLocation(GlobalNames.Domain,"/textures/gui/rads_hud_overlay.png");


    private final static int BAR_WIDTH = 51;
    private final static int BAR_HEIGHT = 25;

    private final static int ACTUAL_BAR_WIDTH = 75;
    private final static int ACTUAL_BAR_HEIGHT = 5;

    private final static int GREEN_BAR_WIDTH = 45;
    private final static int GREEN_BAR_HEIGHT = 5;

    private final static int YELLOW_BAR_WIDTH = 30;
    private final static int YELLOW_BAR_HEIGHT = 5;

    private final static int RED_BAR_WIDTH = 11;
    private final static int RED_BAR_HEIGHT = 5;

    private Minecraft mc;

    public RadsOverlay(Minecraft mc){

        EntityPlayer player = mc.thePlayer;
        World world = mc.theWorld;
        Integer playerRadlevel = player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation();


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
        mc.renderEngine.bindTexture(overlayBar);

        final int PositionX = screenWidth / screenWidth + 2; // leftmost edge of the experience bar
        final int PositionY = screenHeight / 20;  // top of the experience bar

        GL11.glTranslatef(PositionX, PositionY - BAR_HEIGHT, 0);
        drawTexturedModalRect(0, 0, 0, 0, BAR_WIDTH, BAR_HEIGHT);
            Math.max()
        if (player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY,null).getRadiation() < 350){
            drawTexturedModalRect();
        } else if (player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY,null).getRadiation() < 650) {
            drawTexturedModalRect();
            drawTexturedModalRect();
        } else if (player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY,null).getRadiation() < 1000) {
            drawTexturedModalRect(6, 2, 6, WITHER_TEXTURE_U, health, 5);
            drawTexturedModalRect(6, 2, 6, WITHER_TEXTURE_U, health, 5);
            drawTexturedModalRect(6, 2, 6, WITHER_TEXTURE_U, health, 5);
        }
    }
}
//TODO: FINISH THIS
