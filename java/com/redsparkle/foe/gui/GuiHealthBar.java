package com.redsparkle.foe.gui;

import com.redsparkle.foe.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;

@SideOnly(Side.CLIENT)
public class GuiHealthBar extends Gui {
	private static final ResourceLocation texturepath = new ResourceLocation(main.MODID.toLowerCase(), "textures/gui/bar.png");
	private static String name = "HealthBar";
	private int HEALTH_CAP = 20;
	private Minecraft mc;

	public GuiHealthBar(Minecraft mc) {
		this.mc = mc;
	}

	public static String setRegistryName(String healthBar) {
		return name;
	}

	public void renderStatusBar(int screenWidth, int screenHeight) {
    /* These are the variables that contain world and player information */
		World world = mc.theWorld;
		EntityPlayer player = mc.thePlayer;

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

      /* This method tells OpenGL to draw with the custom texture */
		mc.renderEngine.bindTexture(texturepath);

		// we will draw the status bar just above the hotbar.
		//  obtained by inspecting the vanilla hotbar rendering code
		final int vanillaExpLeftX = screenWidth / 2; // leftmost edge of the experience bar
		final int vanillaExpTopY = screenHeight / 2;  // top of the experience bar

      /* Shift our rendering origin to just above the experience bar
       * The top left corner of the screen is x=0, y=0
       */
		GL11.glTranslatef(vanillaExpLeftX, vanillaExpTopY, 0);

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
		drawTexturedModalRect(0, 0, 0, 0, 83, 8);

      /* This line draws the outline effect that corresponds to how much armor the player has.
       * I slide the right-most side of the rectangle using the player's armor value.
       */
		//drawTexturedModalRect(0, 0, 0, BAR_HEIGHT, (int)(BAR_WIDTH*(player.getTotalArmorValue()/20f)), BAR_HEIGHT);

      /* This part draws the inside of the bar, which starts 1 pixel right and down */
		GL11.glPushMatrix();

        /* Shift 1 pixel right and down */
		GL11.glTranslatef(1, 1, 0);

        /* These few numbers will store the HP values of the player. This includes the Health Boost
         * and Absorption potion effects
         */
		float maxHp = player.getMaxHealth();
		float absorptionAmount = player.getAbsorptionAmount();
		float effectiveHp = player.getHealth() + absorptionAmount;

        /* The part of the bar that fills up will be a rectangle that stretches based on how much hp the player
         * has. To do this, I need to use a scaling transform, which is why I push again.
         */
		GL11.glPushMatrix();

          /* I scale horizontally based on the fraction effectiveHp/maxHp. However, I don't want the scaled
           * value to exceed the actual width of the bar's interior, so I cap it at 1 using Math.min.
           *
           * The width of the bar's interior is BAR_WIDTH - 2
           */
		//GL11.glScalef((BAR_WIDTH - 2)*Math.min(1, effectiveHp/maxHp), 1, 1);


          /* This chain of if-else blocks checks if the player has any status effects. I check if a potion effect
           * is active, then draw the filling bar based on what potion effect is active. Unfortunately, it is
           * not possible to use a switch statement here since multiple potion effects may be active at once.
           *
           * The effects I check for are
           *   20: Wither
           *   19: Poison
           *   10: Regeneration
           *
           * For a more comprehensive list of status effects, see http://minecraft.gamepedia.com/Status_effect
           */
//    final int WITHER_EFFECT_ID = 20;  is now MobEffects.WITHER
//    final int POISON_EFFECT_ID = 19;  is now MobEffects.POISON
//    final int REGEN_EFFECT_ID = 10; is now MobEffects.REGENERATION
		final int NORMAL_TEXTURE_U = BAR_WIDTH;     // red texels  - see mbe40_hud_overlay.png
		final int REGEN_TEXTURE_U = BAR_WIDTH + 1;  //  green texels
		final int POISON_TEXTURE_U = BAR_WIDTH + 2;  // black texels
		final int WITHER_TEXTURE_U = BAR_WIDTH + 3;  // brown texels

		if (player.isPotionActive(MobEffects.WITHER)) {
			drawTexturedModalRect(0, 0, WITHER_TEXTURE_U, 0, 1, BAR_HEIGHT - 2);
		}
		else if (player.isPotionActive(MobEffects.POISON)) {
			drawTexturedModalRect(0, 0, POISON_TEXTURE_U, 0, 1, BAR_HEIGHT - 2);
		}
		else if (player.isPotionActive(MobEffects.REGENERATION)) {
			drawTexturedModalRect(0, 0, REGEN_TEXTURE_U, 0, 1, BAR_HEIGHT - 2);
		}
		else {
			drawTexturedModalRect(0, 0, NORMAL_TEXTURE_U, 0, 1, BAR_HEIGHT - 2);
		}

		GL11.glPopMatrix();

        /* Move to the right end of the bar, minus a few pixels. */
		GL11.glTranslatef(BAR_WIDTH - 3, 1, 0);

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
		}
		else {
			fr.drawString(s, -fr.getStringWidth(s) + 1, 2, 0x4D0000);
			fr.drawString(s, -fr.getStringWidth(s), 1, 0xFFFFFF);
		}
		GL11.glPopMatrix();

		GL11.glPopMatrix();

		GL11.glPopMatrix();
		GL11.glPopAttrib();
	}
}



//TODO: Rewrite according to https://github.com/TVoidS/MANA-Mod/blob/master/java/com/tvoids/firstmod/init/GuiBuffBar.java
//TODO: NO REWRITE LIKE THIS GUY https://minecraft.curseforge.com/projects/health-bar

//TODO : OR THIS ONE https://github.com/TheGreyGhost/MinecraftByExample/blob/master/src/main/java/minecraftbyexample/mbe40_hud_overlay/Notes.txt

