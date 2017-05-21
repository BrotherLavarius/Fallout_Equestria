package com.redsparkle.foe.gui.Menus;

import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.rad.RadsFactoryProvider;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.main;
import com.redsparkle.foe.utils.GlobalNames;
import com.redsparkle.foe.utils.Lvlutil;
import com.redsparkle.foe.utils.ScreenGrid;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
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

    public boolean StatsShow = true;
    public boolean LvlUpShow = true;

    public boolean StatsShowButton = false;
    public boolean InvShowButton = true;
    public boolean DataShowButton = true;


    GuiButtonExt Stats = new GuiButtonExt(0,
            0,
            0,
            0,
            0, "STATS");

    GuiButtonExt Inventory = new GuiButtonExt(1,
            0,
            0,
            0,
            0, "ITEMS");

    GuiButtonExt Data = new GuiButtonExt(2,
            0,
            0,
            0,
            0, "DATA");


    GuiButtonExt LvlUp = new GuiButtonExt(3,
            0,
            0,
            0,
            0,"Level Up");

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        String[] skills = {
                "STR", "PER", "END", "CHA", "INT", "AGI", "LUC"
        };
        Integer[] spechials = {
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getPerception(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getEndurance(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getCharisma(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getIntelligence(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getAgility(),
                mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getLuck()
        };
        Integer[] playerParams ={
                mc.player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY,null).getRadiation(),
                mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel(),
                mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress()

        };
        String[] playerParamsString ={
                "RADS:","LVL:","XP Total:"
        };



        this.zLevel=0;
        this.drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(pipbuck);

        GL11.glPushMatrix();
        {
            GL11.glScalef((float) 1.5, (float) 1.5, 1.0f);
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




            {
                if(StatsShow) {
                    int startY=2;
                    //GL11.glPushMatrix();
                    //GL11.glScaled(0.5f, 0.5f, 0);
                    for (int i = 0; i <= (skills.length - 1); i++) {
                        this.fontRendererObj.drawString(skills[i],
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2)+55,
                                ScreenGrid.YCoordStart(
                                        this.height,
                                        startY)+27,
                                8453920, true
                        );
                        this.fontRendererObj.drawString(Integer.toString(spechials[i]),
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2)+75,
                                ScreenGrid.YCoordStart(
                                        this.height,
                                        startY)+27,
                                8453920, true
                        );
                        startY=startY+3;

                    }

                    int paramY =2;
                    for (int o=0;o<=(playerParams.length-1);o++){

                        this.fontRendererObj.drawString(playerParamsString[o],
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2)+105,
                                ScreenGrid.YCoordStart(
                                        this.height,
                                        paramY)+27,
                                8453920, false
                        );

                        this.fontRendererObj.drawString( Integer.toString(playerParams[o]),
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2)+155,
                                ScreenGrid.YCoordStart(
                                        this.height,
                                        paramY)+27,
                                8453920, false
                        );
                        paramY=paramY+4;
                    }


                    this.fontRendererObj.drawString(Lvlutil.progress(
                            playerParams[1],playerParams[2]
                            ),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2)+135,
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    2)+60,
                            8453920, false


                    );

                    //LVL UP BUTTON

                    //GL11.glPopMatrix();
                }
            }









            //STATS BUTTON
            {
                this.buttonList.get(0).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 85;
                this.buttonList.get(0).yPosition = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 163;

                this.buttonList.get(0).height = 28;
                this.buttonList.get(0).width = 52;
                this.buttonList.get(0).enabled = StatsShowButton;
            }
            //INVENTORY BUTTON
            {
                this.buttonList.get(1).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 164;
                this.buttonList.get(1).yPosition = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 163;

                this.buttonList.get(1).height = 28;
                this.buttonList.get(1).width = 52;
                this.buttonList.get(1).enabled = InvShowButton;

            }
            //DATA BUTTON
            {
                this.buttonList.get(2).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 241;
                this.buttonList.get(2).yPosition = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 163;

                this.buttonList.get(2).height = 28;
                this.buttonList.get(2).width = 52;
                this.buttonList.get(2).enabled = DataShowButton;

            }
            //LVLUp BUTTON
            {
                this.buttonList.get(3).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 215;
                this.buttonList.get(3).yPosition = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 105;

                this.buttonList.get(3).height = 15;
                this.buttonList.get(3).width = 66;
                this.buttonList.get(3).visible = StatsShow;
                this.buttonList.get(3).enabled = Lvlutil.canLvlup(playerParams[1],playerParams[2]);
            }

        }
        GL11.glPopMatrix();


//        GL11.glPushMatrix();
//        {
//            this.buttonList.get(0).width = 0;
//            this.buttonList.get(0).height = 0;
//            GL11.glScalef(1.9F, 2f, 1.0f);
//            GL11.glTranslatef(-82f, -99f, 0f);
//            this.buttonList.get(0).xPosition = (this.width / 6 + 50);
//            this.buttonList.get(0).yPosition = (this.height / 2 + 70);
//            //this.buttonList.get(0).drawTexturedModalRect(this.width / 3 , this.height / 2 + 70, 38, 396,33, 18);
//        }
//        GL11.glPopMatrix();


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

        this.buttonList.add(this.Stats);
        this.buttonList.add(this.Inventory);
        this.buttonList.add(this.Data);
        this.buttonList.add(this.LvlUp);

        super.initGui();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        if (button == this.Stats) {
            StatsShowButton = false;
            InvShowButton = true;
            DataShowButton = true;
            LvlUpShow = true;
            StatsShow = true;

        }
        if (button == this.LvlUp){
            this.mc.player.openGui(main.instance, 1, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);

        }
        if (button == this.Inventory) {
            StatsShowButton = true;
            InvShowButton = false;
            DataShowButton = true;




            LvlUpShow = false;
            StatsShow = false;
            //Main.packetHandler.sendToServer(...);
//            this.mc.displayGuiScreen(null);
//            if (this.mc.currentScreen == null)
//                this.mc.setIngameFocus();
        }
        if (button == this.Data) {
            StatsShowButton = true;
            InvShowButton = true;
            DataShowButton = false;




            LvlUpShow = false;
            StatsShow = false;
            //Main.packetHandler.sendToServer(...);
//            this.mc.displayGuiScreen(null);
//            if (this.mc.currentScreen == null)
//                this.mc.setIngameFocus();
        }
        super.actionPerformed(button);
    }
}
