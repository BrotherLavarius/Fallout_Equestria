package com.redsparkle.foe.gui.Menus;

import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.STATS.StatsGui;
import com.redsparkle.foe.utils.GlobalNames;
import com.redsparkle.foe.utils.Lvlutil;
import com.redsparkle.foe.utils.RadioPLayer;
import com.redsparkle.foe.utils.ScreenGrid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.lwjgl.opengl.GL11;

import javax.sound.sampled.FloatControl;
import java.io.IOException;

/**
 * Created by hoijima on 3/4/2017.
 */
public class PipBuckGui extends GuiScreen {
    final ResourceLocation pipbuck = new ResourceLocation(GlobalNames.Domain,
            "textures/gui/pipbuck_bg.png");
    public FloatControl gain;
    public RadioPLayer radioPLayer;
    public int pip_buck_x = 0;
    public int pip_buck_y = 0;
    public boolean StatsShowButton = false;
    public boolean InvShowButton = false;
    public boolean DataShowButton = true;
    public boolean displayMain_Stats = true;
    public boolean displayMain_Inventory = false;
    public boolean displayMain_Data = false;
    EntityPlayer player = Minecraft.getMinecraft().player;
    GuiButtonExt Stats = new GuiButtonExt(0,
            0,
            0,
            0,
            0, "STATS");
    GuiButtonExt Inventory = new GuiButtonExt(1,
            0,
            0,
            0,
            0, "OFFLINE");
    GuiButtonExt Data = new GuiButtonExt(2,
            0,
            0,
            0,
            0, "DATA");
    GuiButtonExt LvlUp = new GuiButtonExt(3,
            0,
            0,
            0,
            0, "Level Up");

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int startX = 0;
        int startY = 0;

        this.zLevel = 0;
        this.drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(pipbuck);
        GL11.glPushMatrix();
        GL11.glScalef((float) 1.7, (float) 1.7, 1.0f);
        drawTexturedModalRect(
                ScreenGrid.XCoordStart(
                        this.width,
                        2),
                ScreenGrid.YCoordStart(
                        this.height,
                        2),
                pip_buck_x,
                pip_buck_y,
                250,
                140);
        GL11.glPopMatrix();


        GL11.glPushMatrix();
        //STATS BUTTON
        {
            this.buttonList.get(0).xPosition = ScreenGrid.XCoordStart(
                    this.width,
                    2) + 98;
            this.buttonList.get(0).yPosition = ScreenGrid.XCoordStart(
                    this.height,
                    2) + 185;

            this.buttonList.get(0).height = 29;
            this.buttonList.get(0).width = 57;
            this.buttonList.get(0).enabled = StatsShowButton;
        }
        //INVENTORY BUTTON
        {
            this.buttonList.get(1).xPosition = ScreenGrid.XCoordStart(
                    this.width,
                    2) + 188;
            this.buttonList.get(1).yPosition = ScreenGrid.XCoordStart(
                    this.height,
                    2) + 185;

            this.buttonList.get(1).height = 29;
            this.buttonList.get(1).width = 57;
            this.buttonList.get(1).enabled = InvShowButton;

        }
        //DATA BUTTON
        {
            this.buttonList.get(2).xPosition = ScreenGrid.XCoordStart(
                    this.width,
                    2) + 275;
            this.buttonList.get(2).yPosition = ScreenGrid.XCoordStart(
                    this.height,
                    2) + 185;

            this.buttonList.get(2).height = 29;
            this.buttonList.get(2).width = 57;
            this.buttonList.get(2).enabled = DataShowButton;

        }
        GL11.glPopMatrix();


        GL11.glPushMatrix();
        GL11.glScalef((float) 0.7, (float) 0.7, 1.0f);

        {// STATS BLOCK MAIN
            for (int i = 8; i < 14; i++) {
                this.buttonList.get(i).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 95;
                this.buttonList.get(i).yPosition = ScreenGrid.XCoordStart(
                        this.height,
                        2) + (i * 15) - 60;

                this.buttonList.get(i).height = 15;
                this.buttonList.get(i).width = 25;
                this.buttonList.get(i).enabled = displayMain_Stats;
                this.buttonList.get(i).visible = displayMain_Stats;
            }
            //LVLUp BUTTON
            {
                this.buttonList.get(3).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 275;
                this.buttonList.get(3).yPosition = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 125;

                this.buttonList.get(3).height = 14;
                this.buttonList.get(3).width = 49;
                this.buttonList.get(3).visible = displayMain_Stats;
                this.buttonList.get(3).enabled = Lvlutil.canLvlup(StatsGui.playerParams[0], StatsGui.playerParams[1]);
            }
            if (displayMain_Stats) {
                // TOP INFO DISPLAY
                this.fontRendererObj.drawString(
                        "|LVL  " + Integer.toString(StatsGui.playerParams[0]) + "| " +
                                "HP  :" + Float.toString(player.getHealth()) + "/" + Float.toString(player.getMaxHealth()) + "| " +
                                "AP  :" + player.getFoodStats().getFoodLevel() + "| " +
                                "XP  :" + Integer.toString(StatsGui.playerParams[1]) + "/" +
                                Lvlutil.lvls[StatsGui.playerParams[0]] + "|",
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 235,
                        ScreenGrid.XCoordStart(
                                this.height,
                                2) + 70,
                        8453920, true
                );


            }
        }


        GL11.glPopMatrix();

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

        this.buttonList.add(this.Stats);// 0
        this.buttonList.add(this.Inventory);// 1
        this.buttonList.add(this.Data);// 2
        this.buttonList.add(this.LvlUp); // 3
        for (int i = 0; i < StatsGui.buttonsSTATNavigation.length; i++) {
            this.buttonList.add(StatsGui.buttonsSTATNavigation[i]);//5-8
        }
        for (int i = 0; i < StatsGui.buttonsSTATmain.length; i++) {
            this.buttonList.add(StatsGui.buttonsSTATmain[i]);//9-14
        }
        for (int i = 0; i < StatsGui.buttonsINVmain.length; i++) {
            this.buttonList.add(StatsGui.buttonsINVmain[i]);//15-21
        }
        for (int i = 0; i < StatsGui.buttonsDATAmain.length; i++) {
            this.buttonList.add(StatsGui.buttonsDATAmain[i]);//22-24
        }


        super.initGui();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        if (button == this.Stats) {
            StatsShowButton = false;
            InvShowButton = false;
            DataShowButton = true;

            displayMain_Stats = true;
            displayMain_Inventory = false;
            displayMain_Data = false;

        }

        if (button == this.Inventory) {

            StatsShowButton = true;
            InvShowButton = false;
            DataShowButton = true;

            displayMain_Stats = false;
            displayMain_Inventory = true;
            displayMain_Data = false;

        }
        if (button == this.Data) {
            StatsShowButton = true;
            InvShowButton = false;
            DataShowButton = false;

            displayMain_Stats = false;
            displayMain_Inventory = false;
            displayMain_Data = true;

        }

        super.actionPerformed(button);
    }
}

