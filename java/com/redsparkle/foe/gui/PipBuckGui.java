package com.redsparkle.foe.gui;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

/**
 * Created by hoijima on 3/4/2017.
 */
public class PipBuckGui extends GuiScreen{

    final ResourceLocation pipbuck = new ResourceLocation(GlobalNames.Domain,
            "textures/gui/pipbuck_bg.png");

    public int pip_buck_x = 0;
    public int pip_buck_y = 0;
    public int pip_buck_H = 321;
    public int pip_buck_W = 484;
    GuiButton Inventory;
    GuiButton Stats;
    GuiButton Data;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {

        this.drawDefaultBackground();





        GlStateManager.color(1,1,1,1);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(pipbuck);

        GL11.glPushMatrix();

        GL11.glScalef((float)this.width/250, (float) this.height/140, 1.0f);
        GL11.glTranslatef(-30f, -25f, 0f);
        drawTexturedModalRect(this.width /9, this.height /9, pip_buck_x, pip_buck_y, 250, 140);
        addButton(Inventory).drawTexturedModalRect(0,0,0,9,9,9);
        GL11.glPopMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
//
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
        this.buttonList.add(this.Stats = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 24, "This is button a"));
        this.buttonList.add(this.Inventory = new GuiButton(1, this.width / 2 - 100, this.height / 2 + 4, "This is button b"));
        this.buttonList.add(this.Data = new GuiButton(1, this.width / 2 - 100, this.height / 2 + 24, "This is button c"));

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
        if (button == this.Inventory){
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button == this.Data){
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
    }
}
