package com.redsparkle.foe.gui.Overlays;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.Init.ConfigInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

/**
 * Created by NENYN on 12/25/2016.
 */
public class PipBuckOverlay extends Gui {
    private final static ResourceLocation overlayBar = new ResourceLocation(GlobalNames.Domain,
            "textures/gui/health_hud_overlay.png");
    /* These two variables describe the size of the bar */
    private final static int BAR_WIDTH = 152;
    private final static int BAR_HEIGHT = 42;
    private final static int ACTUAL_BAR_WIDTH = 135;
    private final static int ACTUAL_BAR_HEIGHT = 9;
    public Color barColor = new Color(ConfigInit.colorR, ConfigInit.colorG, ConfigInit.colorB); // I want to draw the texture to solid red color



    public PipBuckOverlay(Minecraft mc, int screenWidht, int screenHeight) {
    /* These are the variables that contain world and player information */
        EntityPlayer player = mc.player;
        Integer PLayerArmor = player.getTotalArmorValue();
        float maxHp = player.getMaxHealth();
        float absorptionAmount = player.getAbsorptionAmount();
        float effectiveHp = player.getHealth() + absorptionAmount;
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
        mc.renderEngine.bindTexture(overlayBar);

        final int vanillaExpLeftX = 1 + 2; // leftmost edge of the experience bar
        final int vanillaExpTopY = screenHeight - 40;  // top of the experience bar

        GL11.glTranslatef(vanillaExpLeftX, vanillaExpTopY, 0);
        GL11.glScalef(0.76F, 0.76F, 0.76F);

        drawTexturedModalRect(0, 0, 0, 0, BAR_WIDTH, BAR_HEIGHT);


        int health = Math.round((ACTUAL_BAR_WIDTH - 2) * Math.min(1, effectiveHp / maxHp));

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
        final int NORMAL_TEXTURE_U = 47;     // red texels  -

        final int ARMOR_TEXTURE_U = 97;  // brown texels


        if (player.isPotionActive(MobEffects.WITHER)) {
            barColor = (Color) Color.BLACK;
        } else if (player.isPotionActive(MobEffects.POISON)) {
            barColor = (Color) Color.CYAN;
        } else if (player.isPotionActive(MobEffects.REGENERATION)) {
            barColor = (Color) Color.PURPLE;
        }


        GL11.glColor4f((float) barColor.getRed() / 255f,
                (float) barColor.getGreen() / 255f,
                (float) barColor.getBlue() / 255f,
                (float) barColor.getAlpha() / 255f);
        drawTexturedModalRect(4, 11, 7, NORMAL_TEXTURE_U, health, ACTUAL_BAR_HEIGHT);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);




        if (PLayerArmor == 0) {
            int armor = 0;
            drawTexturedModalRect(4, 10, 7, ARMOR_TEXTURE_U, armor, 3);
            GL11.glScalef(0.76F, 0.76F, 0.76F);
        } else {
            int armor = Math.round(100 - (ACTUAL_BAR_WIDTH / PLayerArmor));
            drawTexturedModalRect(4, 10, 7, ARMOR_TEXTURE_U, armor, 3);
            GL11.glScalef(0.76F, 0.76F, 0.76F);
        }

        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPushMatrix();
        GL11.glTranslatef(BAR_WIDTH + 25, 1, 0);
        GL11.glScalef(0.76F, 0.76F, 0.76F);



        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}