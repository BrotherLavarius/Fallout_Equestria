package com.redsparkle.foe.gui;

import com.redsparkle.foe.Init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by hoijima on 3/4/2017.
 */
public class PipBuckGui extends GuiScreen{


    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {

    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void initGui() {

        Item pipbuck = new ItemInit().pbdoi;
        ItemStack pipbuckIS = new ItemStack(pipbuck);

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

        mc.getItemRenderer().renderItemInFirstPerson(mc.player, 0, 0, EnumHand.MAIN_HAND, 0, pipbuckIS, 0);

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}
