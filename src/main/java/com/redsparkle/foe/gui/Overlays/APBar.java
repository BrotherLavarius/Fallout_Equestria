package com.redsparkle.foe.gui.Overlays;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.Init.ConfigInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

/**
 * Created by NENYN on 1/1/2017.
 */
public class APBar extends Gui {
    private final static ResourceLocation overlayBarRad = new ResourceLocation(GlobalNames.Domain, "textures/gui/food_hud_overlay.png");
    private final static int BAR_WIDTH = 154;
    private final static int BAR_HEIGHT = 46;
    private final static int RadBAR_WIDTH = 135;
    private final static int RadBAR_HEIGHT = 9;
    private Minecraft mc;
    public APBar(Minecraft mc) {
        FontRenderer fr = mc.fontRenderer;
        EntityPlayer player = mc.player;
        World world = mc.world;
        int playerFood = player.getFoodStats().getFoodLevel();
        boolean show = false;
        int bullets = 0;
        int maxBullets = 0;
        String bullets_left = "";
        fr.FONT_HEIGHT = 15;
        if (player.inventory.getCurrentItem().getItem() instanceof Item_Firearm) {
            if (player.inventory.getCurrentItem().hasCapability(GunFactoryProvider.GUN, null)) {
                show = true;
                bullets = player.inventory.getCurrentItem().getCapability(GunFactoryProvider.GUN, null).getAmmo();
                maxBullets = player.inventory.getCurrentItem().getCapability(GunFactoryProvider.GUN, null).getMaxAmmo();
                bullets_left = "AMMO:     " + bullets + ":" + maxBullets;
            }
        } else if (player.inventory.getCurrentItem().getItem() instanceof Item_AmmoHolder) {
            if (player.inventory.getCurrentItem().hasCapability(AmmoFactoryProvider.AMMO_STORAGE, null)) {
                show = true;
                bullets = player.inventory.getCurrentItem().getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getAmmo();
                maxBullets = player.inventory.getCurrentItem().getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).getMaxAmmo();
                bullets_left = "Capacity:     " + bullets + ":" + maxBullets;
            }
        } else {
            show = false;
        }
        ScaledResolution scaled = new ScaledResolution(mc);
        int screenWidth = scaled.getScaledWidth();
        int screenHeight = scaled.getScaledHeight();

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

        mc.renderEngine.bindTexture(overlayBarRad);
        final int PositionX = screenWidth - 120; // leftmost edge of the experience bar
        final int PositionY = screenHeight - 40;  // top of the experience bar
        GL11.glTranslatef(PositionX, PositionY, 0);
        GL11.glScalef(0.76F, 0.76F, 0.76F);
        drawTexturedModalRect(0, 0, 0, 0, BAR_WIDTH, BAR_HEIGHT);

        {
            GL11.glPushMatrix();

            if (playerFood <= 1) {
            } else if (playerFood >= 1) {
                drawTexturedModalRect(18, 15, 17, 48, Math.round(6.75F * playerFood), RadBAR_HEIGHT);
                GL11.glScalef(0.76F, 0.76F, 0.76F);
            }

        }

        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        if (show) {
            fr.drawString(bullets_left, PositionX + 25, PositionY + 25, 900000);
        }

        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}
