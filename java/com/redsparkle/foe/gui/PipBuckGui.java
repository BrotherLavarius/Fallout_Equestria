package com.redsparkle.foe.gui;

import com.redsparkle.foe.gui.pipbuck_gui_extenders.texturedButton;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

/**
 * Created by hoijima on 3/4/2017.
 */
public class PipBuckGui extends GuiScreen {

    final ResourceLocation pipbuck = new ResourceLocation(GlobalNames.Domain,
            "textures/gui/pipbuck_bg.png");

    public int pip_buck_x = 0;
    public int pip_buck_y = 0;


    public int global_y_active = 0;
    public int global_x_active = 0;
    public int global_x_inactive = 90;
    public int stat_y = 330;
    public int int_y = 373;
    public int data_y = 414;

    public int global_button_wight = 168;
    public int global_button_hight = 39;


    texturedButton Stats = new texturedButton(0, 0, 0, "");
    texturedButton Inventory = new texturedButton(1, 0, 0, "");
    texturedButton Data = new texturedButton(2, 0, 0, "");

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(pipbuck);

        GL11.glPushMatrix();
        {
            GL11.glScalef((float) this.width / 250, (float) this.height / 140, 1.0f);
            GL11.glTranslatef(-30f, -25f, 0f);
            drawTexturedModalRect(this.width / 9, this.height / 9, pip_buck_x, pip_buck_y, 250, 140);
            this.buttonList.get(0).drawTexturedModalRect((int) this.width / 4.5f, (int) this.height / 1.95f, 38, 396, 33, 18);
            //this.buttonList.get(0).drawScaledCustomSizeModalRect((int) (this.width / 4.5f), (int) (this.height / 1.95f),1.0f,1.0f, 38, 396,33, 18,1f,1f);
            this.buttonList.get(0).width = 65;
            this.buttonList.get(0).height = 20;
            this.buttonList.get(0).xPosition = (int) (this.width / 3.24f);
            this.buttonList.get(0).yPosition = (int) (this.height / 1.26f);
            this.buttonList.get(0).visible = true;
        }
        GL11.glPopMatrix();


//        GL11.glPushMatrix();
//        {
//            this.buttonList.get(0).width = 0;
//            this.buttonList.get(0).height = 0;
//            GL11.glScalef(1.9F, 2f, 1.0f);
//            GL11.glTranslatef(-82f, -99f, 0f);
//            //this.buttonList.get(0).xPosition = (this.width / 6 + 50);
//            //this.buttonList.get(0).yPosition = (this.height / 2 + 70);
//            this.buttonList.get(0).drawTexturedModalRect(this.width / 3 , this.height / 2 + 70, 38, 396,33, 18);
//        }
//        GL11.glPopMatrix();
        this.buttonList.get(1).width = 0;
        this.buttonList.get(2).width = 0;

        super.drawScreen(mouseX, mouseY, partialTicks);
//
    }


    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        GL11.glPushMatrix();
        {
            GL11.glScalef(0, 10f, 0);
            this.buttonList.add(this.Stats);
        }
        GL11.glPopMatrix();
        this.buttonList.add(this.Inventory);
        this.buttonList.add(this.Data);

        super.initGui();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == this.Stats) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button == this.Inventory) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button == this.Data) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
    }
}
