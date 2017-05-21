package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders;

import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSkills;
import com.redsparkle.foe.utils.Lvlutil;
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
public class StatsGui extends GuiScreen {

    public Integer counter = 0;
    GuiButtonExt BGplus = new GuiButtonExt(0,0,0,0,0, "+");
    GuiButtonExt SGPlus = new GuiButtonExt(1,0,0,0,0, "+");
    GuiButtonExt EWPlus = new GuiButtonExt(2,0,0,0,0, "+");
    GuiButtonExt EXPLus = new GuiButtonExt(3,0,0,0,0, "+");
    GuiButtonExt MWPLus = new GuiButtonExt(4,0,0,0,0, "+");
    GuiButtonExt UNPlus = new GuiButtonExt(5,0,0,0,0, "+");
    GuiButtonExt MEDPLus = new GuiButtonExt(6,0,0,0,0, "+");
    GuiButtonExt LOCKPLus = new GuiButtonExt(7,0,0,0,0, "+");
    GuiButtonExt REPPLus = new GuiButtonExt(8,0,0,0,0, "+");
    GuiButtonExt SCIPlus = new GuiButtonExt(9,0,0,0,0, "+");
    GuiButtonExt SNIKPLus = new GuiButtonExt(10,0,0,0,0, "+");
    GuiButtonExt BARTPLus = new GuiButtonExt(11,0,0,0,0, "+");


    GuiButtonExt BGminus = new GuiButtonExt(12,0,0,0,0, "-");
    GuiButtonExt SGminus = new GuiButtonExt(13,0,0,0,0, "-");
    GuiButtonExt EWminus = new GuiButtonExt(14,0,0,0,0, "-");
    GuiButtonExt EXminus = new GuiButtonExt(15,0,0,0,0, "-");
    GuiButtonExt MWminus = new GuiButtonExt(16,0,0,0,0, "-");
    GuiButtonExt UNminus = new GuiButtonExt(17,0,0,0,0, "-");
    GuiButtonExt MEDminus = new GuiButtonExt(18,0,0,0,0, "-");
    GuiButtonExt LOCKminus = new GuiButtonExt(19,0,0,0,0, "-");
    GuiButtonExt REPminus = new GuiButtonExt(20,0,0,0,0, "-");
    GuiButtonExt SCIminus = new GuiButtonExt(21,0,0,0,0, "-");
    GuiButtonExt SNIKminus = new GuiButtonExt(22,0,0,0,0, "-");
    GuiButtonExt BARTminus = new GuiButtonExt(23,0,0,0,0, "-");

    GuiButtonExt Commit = new GuiButtonExt(24,0,0,0,0, "Level up!");
    public Boolean CommitShow = false;

    Integer[] buttonsIdsPlus = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    Integer[] buttonsIdsMinus = {12, 13, 14, 15, 16, 17, 18,19,20 , 21, 22, 23};

    GuiButtonExt[] buttonsPLus = {
            BGplus,SGPlus,EWPlus,
            EXPLus,MWPLus,UNPlus,
            MEDPLus,LOCKPLus,REPPLus,
            SCIPlus,SNIKPLus,BARTPLus
    };
    GuiButtonExt[] buttonsMinus = {
            BGminus,SGminus,EWminus,
            EXminus,MWminus,UNminus,
            MEDminus,LOCKminus,REPminus,
            SCIminus,SNIKminus,BARTminus
    };
    int[] temp = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    Integer[] finished = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getBigGuns(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getSmallGuns(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getEnergyWeapons(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getExplosives(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMeleeWeapons(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getUnarmed(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMedicine(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getLockpick(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getRepair(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getScience(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getSneak(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getBarter()
        };
        String[] names = {
                "Big Guns:",
                "Small Guns:",
                "Energy Weapons:",
                "Explosives:",
                "MeleeWeapons:",
                "Unarmed:",
                "Medicine:",
                "Lockpick:",
                "Repair:",
                "Science:",
                "Sneak:",
                "Barter:"

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
            pointsAvailable = Lvlutil.ponitsAvailable(
                    mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getLevel(),
                    mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getProgress()
            );
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
                if(pointsAvailable != counter) {
                    if (button == buttonsPLus[i]) {
                        temp[i] = temp[i] + 1;
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