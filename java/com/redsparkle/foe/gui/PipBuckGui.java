package com.redsparkle.foe.gui;


import com.redsparkle.foe.main;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;

/**
 * Created by NENYN on 12/25/2016.
 */
public class PipBuckGui extends Gui {

    /* This line tells Minecraft/Forge where your texture is. The first argument is your MODID,
     * and the second argument is the path to your texture starting at "resources/assets/MODID"
     *
     * In this case, the location of the texture is
     *
     *   "resources/assets/MODID/textures/gui/advanced_overlay.png"
     */
    private final static ResourceLocation overlayBar = new ResourceLocation(GlobalNames.Domain,
            "textures/gui/health_hud_overlay.png");

    /* These two variables describe the size of the bar */
    private final static int BAR_WIDTH = 86;
    private final static int BAR_HEIGHT = 21;
    private final static int BAR_SPACING_ABOVE_EXP_BAR = 3;  // pixels between the BAR and the Experience Bar below it
    private final static int ACTUAL_BAR_WIDTH = 75;
    private final static int ACTUAL_BAR_HEIGHT = 5;

    /* Sometimes you want to include extra information from the game. This instance of
     * Minecraft will let you access the World and EntityPlayer objects which is more than
     * enough for most purposes. It also contains some helper objects for OpenGL which can be
     * used for drawing things.
     *
     * To actually get the instance of Minecraft, you should pass it in through the constructor.
     * It is possible to import Minecraft and use Minecraft.getMinecraft() here, but this won't
     * always be possible if you want to include information that's part of another class.
     */
    private Minecraft mc;

    public PipBuckGui(Minecraft mc) {
        this.mc = mc;
    }

    /* This helper method will render the bar */
    public void renderStatusBar(int screenWidth, int screenHeight) {
    /* These are the variables that contain world and player information */
        World world = mc.theWorld;
        EntityPlayer player = mc.thePlayer;
        Integer PLayerArmor = player.getTotalArmorValue();
        float maxHp = player.getMaxHealth();
        float absorptionAmount = player.getAbsorptionAmount();
        float effectiveHp = player.getHealth() + absorptionAmount;
    /* This object draws text using the Minecraft font */
        FontRenderer fr = mc.fontRendererObj;

    /* This object inserts commas into number strings */
        DecimalFormat d = new DecimalFormat("#,###");

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

        // we will draw the status bar just above the hotbar.
        //  obtained by inspecting the vanilla hotbar rendering code
        final int vanillaExpLeftX = screenWidth / screenWidth + 2; // leftmost edge of the experience bar
        final int vanillaExpTopY = screenHeight - 5;  // top of the experience bar

      /* Shift our rendering origin to just above the experience bar
       * The top left corner of the screen is x=0, y=0
       */
        GL11.glTranslatef(vanillaExpLeftX, vanillaExpTopY - BAR_SPACING_ABOVE_EXP_BAR - BAR_HEIGHT, 0);
      /* Draw a part of the image file at the current position
       *
       * The first two arguments are the x,y position that you want to draw the texture at
       * (with respect to the current transformations).
       *
       * The next four arguments specify what part of the image file to draw, in the order below:
       *
       *   1. Left-most side
       *   2. Top-most side
       *   3. Right-most side
       *   4. Bottom-most side
       *
       * The units of these four arguments are pixels in the image file. These arguments will form a
       * rectangle, which is then "cut" from your image and used as the texture
       *
       * This line draws the background of the custom bar
       */
        drawTexturedModalRect(0, 0, 0, 0, BAR_WIDTH, BAR_HEIGHT);
        int health = Math.round((ACTUAL_BAR_WIDTH - 2) * Math.min(1, effectiveHp / maxHp));

        final int SPACER = 6;
        final int NORMAL_TEXTURE_U = 22;     // red texels  - see mbe40_hud_overlay.png
        final int REGEN_TEXTURE_U = NORMAL_TEXTURE_U + SPACER;  //  green texels
        final int POISON_TEXTURE_U = REGEN_TEXTURE_U + SPACER;  // black texels
        final int WITHER_TEXTURE_U = POISON_TEXTURE_U + SPACER;  // brown texels
        final int ARMOR_TEXTURE_U = WITHER_TEXTURE_U + SPACER;  // brown texels


        if (player.isPotionActive(MobEffects.WITHER)) {
            drawTexturedModalRect(6, 2, 6, WITHER_TEXTURE_U, health, 5);
        } else if (player.isPotionActive(MobEffects.POISON)) {
            drawTexturedModalRect(6, 2, 6, POISON_TEXTURE_U, health, 5);
        } else if (player.isPotionActive(MobEffects.REGENERATION)) {
            drawTexturedModalRect(6, 2, 6, REGEN_TEXTURE_U, health, 5);
        } else {
            drawTexturedModalRect(6, 2, 6, NORMAL_TEXTURE_U, health, 5);
        }

        if (PLayerArmor == 0) {
            int armor = 0;
            drawTexturedModalRect(6, 12, 6, ARMOR_TEXTURE_U, armor, 5);

        } else {
            int armor = Math.round(ACTUAL_BAR_WIDTH / PLayerArmor);
            drawTexturedModalRect(6, 12, 6, ARMOR_TEXTURE_U, armor, 5);

        }

        GL11.glPushMatrix();
        GL11.glTranslatef(BAR_WIDTH + 25, 1, 0);

        /* The default minecraft font is too big, so I scale it down a bit. */
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 1);

          /* This generates the string that I want to draw. */
        String s = d.format(effectiveHp) + "/" + d.format(maxHp);

          /* If the player has the absorption effect, draw the string in gold color, otherwise
           * draw the string in white color. For each case, I call drawString twice, once to
           * draw the shadow, and once for the actual string.
           */
        if (absorptionAmount > 0) {

            /* Draw the shadow string */
            fr.drawString(s, -fr.getStringWidth(s) + 1, 2, 0x5A2B00);

            /* Draw the actual string */
            fr.drawString(s, -fr.getStringWidth(s), 1, 0xFFD200);
        } else {
            fr.drawString(s, -fr.getStringWidth(s) + 1, 2, 0x4D0000);
            fr.drawString(s, -fr.getStringWidth(s), 1, 0xFFFFFF);
        }


        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}