package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders;

import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.utils.ScreenGrid;
import net.minecraft.client.gui.GuiScreen;

/**
 * Created by hoijima on 09.05.17.
 */
public class StatsGui extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {


        this.fontRendererObj.drawString("STR: " + Integer.toString(
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght()),
                ScreenGrid.XCoordStart(
                        this.width,
                        2),
                ScreenGrid.XCoordStart(
                        this.width,
                        2),
                8453920,true
        );


        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {

        super.initGui();

    }

    public GuiScreen drawScreen() {

        this.fontRendererObj.drawString("STR: " + Integer.toString(
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght()),
                ScreenGrid.XCoordStart(
                        this.width,
                        2),
                ScreenGrid.XCoordStart(
                        this.width,
                        2),
                8453920,true
        );

        return null;
    }
}
