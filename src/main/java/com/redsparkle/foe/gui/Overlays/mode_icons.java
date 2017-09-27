package com.redsparkle.foe.gui.Overlays;

import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.api.utils.gui.Color_and_Etc;
import com.redsparkle.foe.Init.ConfigInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

/**
 * Created by hoijima on 19.09.17.
 */
public class mode_icons extends Gui {

    private final static ResourceLocation modes = new ResourceLocation(GlobalNames.Domain, "textures/gui_icons/modes.png");
    private Minecraft mc;


    public mode_icons(Minecraft mc) {
        FontRenderer fr = mc.fontRenderer;
        int colorText = Color_and_Etc.rawColorFromRGB(ConfigInit.colorR, ConfigInit.colorG, ConfigInit.colorB);


        ScaledResolution scaled = new ScaledResolution(mc);
        int screenWidth = scaled.getScaledWidth();
        int screenHight = scaled.getScaledHeight();

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
        Color color = new Color(ConfigInit.colorR, ConfigInit.colorG, ConfigInit.colorB); // I want to draw the texture to solid red color

        GL11.glColor4f((float) color.getRed() / 255f,
                (float) color.getGreen() / 255f,
                (float) color.getBlue() / 255f,
                (float) color.getAlpha() / 255f);


        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);
        GL11.glScalef(0.1F, 0.2F, 0);

        mc.renderEngine.bindTexture(modes);
        if (!mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getInteraction()) {
            drawTexturedModalRect(0, 15, 0, 9, 256, 75);
        }
        if (mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getInteraction()) {
            drawTexturedModalRect(0, 15, 0, 95, 256, 75);
        }
        if (mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()) {
            drawTexturedModalRect(0, 100, 0, 175, 256, 75);
        }
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);

        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}
