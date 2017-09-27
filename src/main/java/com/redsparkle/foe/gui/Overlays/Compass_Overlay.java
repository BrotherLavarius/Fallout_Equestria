package com.redsparkle.foe.gui.Overlays;

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
public class Compass_Overlay extends Gui {

    private final static ResourceLocation empty = new ResourceLocation(GlobalNames.Domain, "textures/gui/fallout_widgets.png");
    private Minecraft mc;

    public Compass_Overlay(Minecraft mc) {
        FontRenderer fr = mc.fontRenderer;
        int colorText = Color_and_Etc.rawColorFromRGB(ConfigInit.colorR, ConfigInit.colorG, ConfigInit.colorB);
        mc.renderEngine.bindTexture(empty);


        ScaledResolution scaled = new ScaledResolution(mc);
        int screenWidth = scaled.getScaledWidth();

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
        int k = Math.round(mc.player.rotationYaw);
        if (k < 0)              //due to the yaw running a -360 to positive 360
            k += 360;
        k %= 360;

        int correction = 0;
        if (k < 100) {
            correction = 2;
        } else if (k < 90) {
            correction = 2;
        } else {
            correction = -1;
        }
        //fr.drawString(String.valueOf(k),screenWidth/2-5+correction,25,colorText);

        GlStateManager.scale(2, 2, 2);
        GlStateManager.translate(-(screenWidth / 4), 2, 0);

        for (int i = 12; i > 0; i--) {
            fr.drawString(calculatePointing(k - i), screenWidth / 2 - (i * 4), 3, colorText);
        }

        fr.drawString(calculatePointing(k), screenWidth / 2, 3, colorText);
        for (int i = 0; i < 13; i++) {
            fr.drawString(calculatePointing(k + i), screenWidth / 2 + (i * 4), 3, colorText);
        }


        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);

        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    public String calculatePointing(int degree) {
        int correct_degree = 0;
        if (degree < 0) {
            correct_degree = 360 + degree;
        } else if (degree == 360) {
            correct_degree = 0;
        } else {
            correct_degree = degree;
        }

        if (correct_degree == 0) {
            return "S.";
        } else if (correct_degree == 90) {
            return "W.";

        } else if (correct_degree == 180) {
            return "N.";

        } else if (correct_degree == 270) {
            return "E.";

        }

        return "...";
    }
}