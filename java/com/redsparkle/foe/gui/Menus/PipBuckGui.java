package com.redsparkle.foe.gui.Menus;

import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.rad.RadsFactoryProvider;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
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

    public boolean StatsShow = false;

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

//            this.buttonList.get(0).drawTexturedModalRect(
//                    ScreenGrid.XCoordStart(
//                            this.width,
//                            2) + 54,
//                    ScreenGrid.YCoordStart(
//                            this.height,
//                            2) + 106,
//                    38,
//                    396,
//                    33,
//                    18);
//            this.buttonList.get(1).drawTexturedModalRect(
//                    ScreenGrid.XCoordStart(
//                            this.width,
//                            2) + 107,
//                    ScreenGrid.YCoordStart(
//                            this.height,
//                            2) + 106,
//                    38,
//                    414,
//                    33,
//                    18);
//            this.buttonList.get(2).drawTexturedModalRect(
//                    ScreenGrid.XCoordStart(
//                            this.width,
//                            2) + 159,
//                    ScreenGrid.YCoordStart(
//                            this.height,
//                            2) + 106,
//                    38,
//                    432,
//                    33,
//                    18);

            //STATS BUTTON
            {
                this.buttonList.get(0).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 85;
                this.buttonList.get(0).yPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 158;

                this.buttonList.get(0).height = 28;
                this.buttonList.get(0).width = 52;
            }

            {
                if(StatsShow){
                    GL11.glPushMatrix();
                    GL11.glScaled(0.5f,0.5f,0);
                    this.fontRendererObj.drawString("STR : " + Integer.toString(
                            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    27),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    29),
                            8453920,true
                    );

                    this.fontRendererObj.drawString("PER : " + Integer.toString(
                            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getPerception()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    27),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    33),
                            8453920,true
                    );

                    this.fontRendererObj.drawString("LUK : " + Integer.toString(
                            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getLuck()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    27),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    37),
                            8453920,true
                    );

                    this.fontRendererObj.drawString("INT : " + Integer.toString(
                            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getIntelligence()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    27),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    41),
                            8453920,true
                    );

                    this.fontRendererObj.drawString("END : " + Integer.toString(
                            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getEndurance()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    27),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    45),
                            8453920,true
                    );

                    this.fontRendererObj.drawString("CHR : " + Integer.toString(
                            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getCharisma()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    27),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    49),
                            8453920,true
                    );

                    this.fontRendererObj.drawString("AGI : " + Integer.toString(
                            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getAgility()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    27),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    53),
                            8453920,true
                    );


                    this.fontRendererObj.drawString("RADS: " + Integer.toString(
                            mc.player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    70),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    29),
                            8453920,false
                    );

                    this.fontRendererObj.drawString("LVL: " + Integer.toString(
                            mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getLevel()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    70),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    33),
                            8453920,false
                    );

                    this.fontRendererObj.drawString("Total XP : " + Integer.toString(
                            mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getProgress()),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    70),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    37),
                            8453920,false
                    );

                    this.fontRendererObj.drawString(Lvlutil.progress(
                            mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getLevel(),
                            mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getProgress()
                            ),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    70),
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    41),
                            8453920,false
                    );
                    GL11.glPopMatrix();
                }
            }






            //INVENTORY BUTTON
            {
                this.buttonList.get(1).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 164;
                this.buttonList.get(1).yPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 158;

                this.buttonList.get(1).height = 28;
                this.buttonList.get(1).width = 52;
            }
            //DATA BUTTON
            {
                this.buttonList.get(2).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 241;
                this.buttonList.get(2).yPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 158;

                this.buttonList.get(2).height = 28;
                this.buttonList.get(2).width = 52;
            }




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

        super.initGui();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        if (button == this.Stats) {
           // this.mc.player.openGui(main.instance, 1, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);
            StatsShow = true;

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
        super.actionPerformed(button);
    }
}
