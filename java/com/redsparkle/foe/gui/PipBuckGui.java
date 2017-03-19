package com.redsparkle.foe.gui;

import com.redsparkle.foe.Init.ItemInit;
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
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by hoijima on 3/4/2017.
 */
public class PipBuckGui extends GuiScreen{

    public static Item pipbuck = new ItemInit().pbdoi;
    public static ItemStack pipbuckIS = new ItemStack(pipbuck);
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {

        this.drawDefaultBackground();
        this.zLevel = 0.0F;
        this.itemRender.zLevel = 1.0F;

        this.itemRender.renderItemIntoGUI(pipbuckIS,50,50);

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



    }
}
