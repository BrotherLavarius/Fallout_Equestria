package com.redsparkle.foe.gui.general;

import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.utils.ScreenGrid;
import com.redsparkle.api.utils.gui.Color_and_Etc;
import com.redsparkle.api.utils.gui.GuiButtonExtFallout;
import com.redsparkle.foe.Init.ConfigInit;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSPECHIAL;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Created by hoijima on 09.05.17.
 */
@SuppressWarnings("Duplicates")
public class StatsGuiFirstJoin extends GuiScreen {
    public Integer counter = 0;
    public Boolean CommitShow = false;
    public int startY = 15;
    int colorText = Color_and_Etc.rawColorFromRGB(ConfigInit.colorR, ConfigInit.colorG, ConfigInit.colorB);

    GuiButtonExtFallout STR_Plus = new GuiButtonExtFallout(0, 0, 0, 0, 0, "+");
    GuiButtonExtFallout PER_Plus = new GuiButtonExtFallout(1, 0, 0, 0, 0, "+");
    GuiButtonExtFallout END_Plus = new GuiButtonExtFallout(2, 0, 0, 0, 0, "+");
    GuiButtonExtFallout CHA_Plus = new GuiButtonExtFallout(3, 0, 0, 0, 0, "+");
    GuiButtonExtFallout INT_Plus = new GuiButtonExtFallout(4, 0, 0, 0, 0, "+");
    GuiButtonExtFallout AGI_Plus = new GuiButtonExtFallout(5, 0, 0, 0, 0, "+");
    GuiButtonExtFallout LUC_Plus = new GuiButtonExtFallout(6, 0, 0, 0, 0, "+");
    GuiButtonExtFallout STR_Minus = new GuiButtonExtFallout(7, 0, 0, 0, 0, "-");
    GuiButtonExtFallout PER_Minus = new GuiButtonExtFallout(8, 0, 0, 0, 0, "-");
    GuiButtonExtFallout END_Minus = new GuiButtonExtFallout(9, 0, 0, 0, 0, "-");
    GuiButtonExtFallout CHA_Minus = new GuiButtonExtFallout(10, 0, 0, 0, 0, "-");
    GuiButtonExtFallout INT_Minus = new GuiButtonExtFallout(11, 0, 0, 0, 0, "-");
    GuiButtonExtFallout AGI_Minus = new GuiButtonExtFallout(12, 0, 0, 0, 0, "-");
    GuiButtonExtFallout LUC_Minus = new GuiButtonExtFallout(13, 0, 0, 0, 0, "-");
    GuiButtonExtFallout Commit = new GuiButtonExtFallout(14, 0, 0, 0, 0, "Start Playing!");
    Integer[] buttonsIdsPlus = {0, 1, 2, 3, 4, 5, 6};
    Integer[] buttonsIdsMinus = {7, 8, 9, 10, 11, 12, 13};
    GuiButtonExtFallout[] buttonsPLus = {
            STR_Plus,
            PER_Plus,
            END_Plus,
            CHA_Plus,
            INT_Plus,
            AGI_Plus,
            LUC_Plus
    };
    GuiButtonExtFallout[] buttonsMinus = {
            STR_Minus,
            PER_Minus,
            END_Minus,
            CHA_Minus,
            INT_Minus,
            AGI_Minus,
            LUC_Minus
    };
    int[] temp = {0, 0, 0, 0, 0, 0, 0};
    int[] finished = {0, 0, 0, 0, 0, 0, 0};
    Integer pointsAvailable = 0;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int startY = 15;
        this.drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        Integer[] params = {
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getPerception(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getEndurance(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getCharisma(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getIntelligence(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getAgility(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getLuck()
        };
        String[] names = {
                " Strength:     ",
                " Perception:   ",
                " Endurance:    ",
                " Charisma :    ",
                " Intelligence :",
                " Agility:      ",
                " Luck :        "
        };
        GL11.glPushMatrix();
        {
            GL11.glScalef((float) 1.0, (float) 1.0, 1.0f);
            for (int i = 0; i <= (params.length - 1); i++) {
                this.fontRenderer.drawString(names[i],
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 25,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2) + (i * 15) + 30,
                        colorText, true
                );
                this.fontRenderer.drawString(Integer.toString(
                        params[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 215,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2) + (i * 15) + 30,
                        colorText, true
                );
                this.fontRenderer.drawString("+ " + Integer.toString(
                        temp[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 225,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2) + (i * 15) + 30,
                        colorText, true
                );
                this.fontRenderer.drawString(Integer.toString(
                        finished[i] = temp[i] + params[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 275,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2) + (i * 15) + 30,
                        colorText, true
                );
                startY = startY + 4;
            }
            pointsAvailable = 30;
            this.fontRenderer.drawString("Points available: " + Integer.toString(pointsAvailable - IntStream.of(temp).sum()),
                    ScreenGrid.XCoordStart(
                            this.width,
                            2) + 30,
                    ScreenGrid.YCoordStart(
                            this.height,
                            2) + 10,
                    colorText, true
            );
            {
                int startYButtons = 36;
                for (int o = 0; o <= (buttonsIdsPlus.length - 1); o++) {
                    this.buttonList.get(buttonsIdsPlus[o]).x = ScreenGrid.XCoordStart(this.width, 2) + 248;
                    this.buttonList.get(buttonsIdsPlus[o]).y = ScreenGrid.XCoordStart(this.height, 2) + (o * 15) + 30;
                    this.buttonList.get(buttonsIdsPlus[o]).height = 12;
                    this.buttonList.get(buttonsIdsPlus[o]).width = 10;
                    this.buttonList.get(buttonsIdsMinus[o]).x = ScreenGrid.XCoordStart(this.width, 2) + 258;
                    this.buttonList.get(buttonsIdsMinus[o]).y = ScreenGrid.XCoordStart(this.height, 2) + (o * 15) + 30;
                    this.buttonList.get(buttonsIdsMinus[o]).height = 10;
                    this.buttonList.get(buttonsIdsMinus[o]).width = 9;
                    startYButtons = startYButtons + 13;
                }
            }
            this.buttonList.get(14).x = ScreenGrid.XCoordStart(this.width, 55);
            this.buttonList.get(14).y = ScreenGrid.XCoordStart(this.height, 75);
            this.buttonList.get(14).height = 21;
            this.buttonList.get(14).width = 80;
            this.buttonList.get(14).enabled = CommitShow;
            CommitShow = (pointsAvailable - IntStream.of(temp).sum()) == 0;
        }
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        for (int i = 0; i <= (buttonsPLus.length - 1); i++) {
            this.buttonList.add(buttonsPLus[i]);
        }
        for (int i = 0; i <= (buttonsMinus.length - 1); i++) {
            this.buttonList.add(buttonsMinus[i]);
        }
        this.buttonList.add(Commit);
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        for (int i = 0; i <= (temp.length - 1); i++) {
            counter = IntStream.of(temp).sum();
            if (pointsAvailable > 0) {
                if (pointsAvailable != counter) {
                    if (button == buttonsPLus[i]) {
                        if (temp[i] <= 9) {
                            temp[i] = temp[i] + 1;
                        }
                    }
                }
                if (button == buttonsMinus[i]) {
                    if (temp[i] > 0) {
                        temp[i] = temp[i] - 1;
                    }
                }
            }
        }
        if (button == this.Commit) {
            if (IntStream.of(finished).sum() == 30) {
                main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientServerSPECHIAL(finished));
                for (int i = 0; i <= (finished.length - 1); i++) {
                    finished[i] = 0;
                    temp[i] = 0;
                    this.mc.displayGuiScreen(null);
                }
            }
        }
    }
}