package com.redsparkle.foe.gui.Overlays;


import com.redsparkle.api.utils.GlobalNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

/**
 * Created by NENYN on 12/25/2016.
 */
public class PipBuckOverlay extends Gui {

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
    private final static int BAR_WIDTH = 152;
    private final static int BAR_HEIGHT = 42;
    private final static int BAR_SPACING_ABOVE_EXP_BAR = 3;  // pixels between the BAR and the Experience Bar below it
    private final static int ACTUAL_BAR_WIDTH = 135;
    private final static int ACTUAL_BAR_HEIGHT = 9;

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

    public PipBuckOverlay(Minecraft mc, int screenWidht, int screenHeight) {

    /* These are the variables that contain world and player information */
        FontRenderer fr = mc.fontRendererObj;

        World world = mc.world;
        EntityPlayer player = mc.player;
        Integer PLayerArmor = player.getTotalArmorValue();
        float maxHp = player.getMaxHealth();
        float absorptionAmount = player.getAbsorptionAmount();
        float effectiveHp = player.getHealth() + absorptionAmount;

    /* Saving the current state of OpenGL so that I can restore it when I'm done */
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

      /* I like to indent the code whenever I push. It helps me visualize what is
       * happening better. This is a personal preference though.
       */

      /* Set the rendering color to white */
        GL11.glColor4f(0.0F, 90.0F, 1.0F, 90.0F);
        GlStateManager.disableLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();

      /* This method tells OpenGL to draw with the custom texture */
        mc.renderEngine.bindTexture(overlayBar);

        // we will draw the status bar just above the hotbar.
        //  obtained by inspecting the vanilla hotbar rendering code
        final int vanillaExpLeftX = 1 + 2; // leftmost edge of the experience bar
        final int vanillaExpTopY = screenHeight - 40;  // top of the experience bar

      /* Shift our rendering origin to just above the experience bar
       * The top left corner of the screen is x=0, y=0
       */
        GL11.glTranslatef(vanillaExpLeftX, vanillaExpTopY, 0);
        GL11.glScalef(0.76F, 0.76F, 0.76F);
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
        GL11.glPushMatrix();

        final int SPACER = 1;
        final int NORMAL_TEXTURE_U = 47;     // red texels  -
        final int REGEN_TEXTURE_U = 57;  //  green texels
        final int POISON_TEXTURE_U = 67;  // black texels
        final int WITHER_TEXTURE_U = 77;  // brown texels
        final int ENERVATION_TEXTURE_U = 87;  // brown texels
        final int ARMOR_TEXTURE_U = 97;  // brown texels


        if (player.isPotionActive(MobEffects.WITHER)) {
            drawTexturedModalRect(4, 11, 7, WITHER_TEXTURE_U, health, ACTUAL_BAR_HEIGHT);
            GL11.glScalef(0.76F, 0.76F, 0.76F);
        } else if (player.isPotionActive(MobEffects.POISON)) {
            drawTexturedModalRect(4, 11, 7, POISON_TEXTURE_U, health, ACTUAL_BAR_HEIGHT);
            GL11.glScalef(0.76F, 0.76F, 0.76F);
        } else if (player.isPotionActive(MobEffects.REGENERATION)) {
            drawTexturedModalRect(4, 11, 7, REGEN_TEXTURE_U, health, ACTUAL_BAR_HEIGHT);
            GL11.glScalef(0.76F, 0.76F, 0.76F);
        } else {
            drawTexturedModalRect(4, 11, 7, NORMAL_TEXTURE_U, health, ACTUAL_BAR_HEIGHT);
            GL11.glScalef(0.76F, 0.76F, 0.76F);
        }

        if (PLayerArmor == 0) {
            int armor = 0;
            drawTexturedModalRect(4, 10, 7, ARMOR_TEXTURE_U, armor, 3);
            GL11.glScalef(0.76F, 0.76F, 0.76F);

        } else {
            int armor = Math.round(100 - (ACTUAL_BAR_WIDTH / PLayerArmor));
            drawTexturedModalRect(4, 10, 7, ARMOR_TEXTURE_U, armor, 3);
            GL11.glScalef(0.76F, 0.76F, 0.76F);

        }

        GL11.glPushMatrix();
        GL11.glTranslatef(BAR_WIDTH + 25, 1, 0);
        GL11.glScalef(0.76F, 0.76F, 0.76F);
        int k = 0;
        k = mc.player.getAdjustedHorizontalFacing().getHorizontalIndex();

        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopAttrib();

    }
}