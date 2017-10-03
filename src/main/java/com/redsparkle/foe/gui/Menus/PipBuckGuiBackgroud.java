package com.redsparkle.foe.gui.Menus;

import com.redsparkle.api.utils.GlobalNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * Created by hoijima on 3/4/2017.
 */
public class PipBuckGuiBackgroud extends GuiScreen {
    final ResourceLocation pipbuck = new ResourceLocation(GlobalNames.Domain,
            "textures/gui/pipbuck_bg.png");
    EntityPlayer player = Minecraft.getMinecraft().player;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
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
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }
}
