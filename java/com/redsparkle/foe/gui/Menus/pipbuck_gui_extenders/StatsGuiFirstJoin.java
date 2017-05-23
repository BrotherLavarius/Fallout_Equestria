package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders;

import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSkills;
import com.redsparkle.foe.utils.ScreenGrid;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Created by hoijima on 09.05.17.
 */
@SuppressWarnings("Duplicates")
public class StatsGuiFirstJoin extends GuiScreen {

    
    
    public Integer counter = 0;
    GuiButtonExt STR_Plus = new GuiButtonExt(0,0,0,0,0, "+");
    GuiButtonExt PER_Plus = new GuiButtonExt(1,0,0,0,0, "+");
    GuiButtonExt END_Plus = new GuiButtonExt(2,0,0,0,0, "+");
    GuiButtonExt CHA_Plus = new GuiButtonExt(3,0,0,0,0, "+");
    GuiButtonExt INT_Plus = new GuiButtonExt(4,0,0,0,0, "+");
    GuiButtonExt AGI_Plus = new GuiButtonExt(5,0,0,0,0, "+");
    GuiButtonExt LUC_Plus = new GuiButtonExt(6,0,0,0,0, "+");

    GuiButtonExt STR_Minus = new GuiButtonExt(12,0,0,0,0, "-");
    GuiButtonExt PER_Minus = new GuiButtonExt(13,0,0,0,0, "-");
    GuiButtonExt END_Minus = new GuiButtonExt(14,0,0,0,0, "-");
    GuiButtonExt CHA_Minus = new GuiButtonExt(15,0,0,0,0, "-");
    GuiButtonExt INT_Minus = new GuiButtonExt(16,0,0,0,0, "-");
    GuiButtonExt AGI_Minus = new GuiButtonExt(17,0,0,0,0, "-");
    GuiButtonExt LUC_Minus = new GuiButtonExt(18,0,0,0,0, "-");


    GuiButtonExt Commit = new GuiButtonExt(24,0,0,0,0, "Start Playing!");
    public Boolean CommitShow = false;

    Integer[] buttonsIdsPlus = {0, 1, 2, 3, 4, 5, 6};
    Integer[] buttonsIdsMinus = {12, 13, 14, 15, 16, 17, 18};

    GuiButtonExt[] buttonsPLus = {
            STR_Plus,
            PER_Plus,
            END_Plus,
            CHA_Plus,
            INT_Plus,
            AGI_Plus,
            LUC_Plus
    };
    GuiButtonExt[] buttonsMinus = {
            STR_Minus,
            PER_Minus,
            END_Minus,
            CHA_Minus,
            INT_Minus,
            AGI_Minus,
            LUC_Minus
    };
    int[] temp = {0, 0, 0, 0, 0, 0, 0};
    Integer[] finished = {0, 0, 0, 0, 0, 0, 0};

    Integer pointsAvailable =0 ;


    public int startY = 15;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int startY = 15;
        this.drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();


        Integer[] params = {
                (mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght()+5),
                (mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getPerception()+5),
                (mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getEndurance()+5),
                (mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getCharisma()+5),
                (mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getIntelligence()+5),
                (mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getAgility()+5),
                (mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getLuck()+5)
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
        GL11.glPushMatrix();{
                GL11.glScalef((float) 1.0, (float) 1.0, 1.0f);
            for (int i = 0; i <= (params.length - 1); i++) {
                this.fontRendererObj.drawString(names[i],
                        ScreenGrid.XCoordStart(
                                this.width,
                                2)+25,
                        ScreenGrid.YCoordStart(
                                this.height,
                                startY),
                        8453920, true
                );
                this.fontRendererObj.drawString(Integer.toString(
                        params[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2)+215,
                        ScreenGrid.YCoordStart(
                                this.height,
                                startY),
                        8453920, true
                );
                this.fontRendererObj.drawString("+ " +Integer.toString(
                        temp[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2)+225,
                        ScreenGrid.YCoordStart(
                                this.height,
                                startY),
                        8453920, true
                );

                this.fontRendererObj.drawString(Integer.toString(
                        finished[i] =temp[i]+params[i] ),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2)+275,
                        ScreenGrid.YCoordStart(
                                this.height,
                                startY),
                        8453920, true
                );




                startY = startY + 4;
            }
            pointsAvailable = 5;

            this.fontRendererObj.drawString("Points available: " +Integer.toString(pointsAvailable - IntStream.of(temp).sum()),
                    ScreenGrid.XCoordStart(
                            this.width,
                            2)+30,
                    ScreenGrid.YCoordStart(
                            this.height,
                            2)+ 10,
                    8453920, true
            );


            {
                int startYButtons =36;
                for (int o =0;o <= (buttonsIdsPlus.length-1);o++) {
                    this.buttonList.get(buttonsIdsPlus[o]).xPosition = ScreenGrid.XCoordStart(this.width,2) + 248;
                    this.buttonList.get(buttonsIdsPlus[o]).yPosition = ScreenGrid.XCoordStart(this.height,2) + startYButtons;
                    this.buttonList.get(buttonsIdsPlus[o]).height = 12;
                    this.buttonList.get(buttonsIdsPlus[o]).width = 10;
//TODO: some random bug
                    this.buttonList.get(buttonsIdsMinus[o]).xPosition = ScreenGrid.XCoordStart(this.width,2) + 258;
                    this.buttonList.get(buttonsIdsMinus[o]).yPosition = ScreenGrid.XCoordStart(this.height,2) + startYButtons;
                    this.buttonList.get(buttonsIdsMinus[o]).height = 10;
                    this.buttonList.get(buttonsIdsMinus[o]).width = 9;

                    startYButtons=startYButtons+13;
                }

            }
            this.buttonList.get(24).xPosition = ScreenGrid.XCoordStart(this.width,55);
            this.buttonList.get(24).yPosition = ScreenGrid.XCoordStart(this.height,75);
            this.buttonList.get(24).height = 21;
            this.buttonList.get(24).width = 80;
            this.buttonList.get(24).enabled = CommitShow;
            if((pointsAvailable - IntStream.of(temp).sum()) == 0) {
                CommitShow = true;
            }else CommitShow = false;
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

        for (int i =0;i <= (buttonsPLus.length-1);i++) {
            this.buttonList.add(buttonsPLus[i]);
        }
        for (int i =0;i <= (buttonsMinus.length-1);i++) {
            this.buttonList.add(buttonsMinus[i]);
        }
        this.buttonList.add(Commit);
        super.initGui();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        for(int i =0;i <= (temp.length-1);i++){
            counter = IntStream.of(temp).sum();
            if(pointsAvailable > 0 ){
                if(pointsAvailable != counter ) {
                    if (button == buttonsPLus[i]) {
                        if (temp[i] <=9) {
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
        if (button == this.Commit){
            main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientServerSkills(finished));
            for (int i=0;i<=(finished.length-1);i++){
                finished[i]=0;
                temp[i]=0;
                this.mc.displayGuiScreen(null);
            }

        }

    }
}