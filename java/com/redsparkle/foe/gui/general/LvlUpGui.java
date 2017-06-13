package com.redsparkle.foe.gui.general;

import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSkills;
import com.redsparkle.foe.utils.Lvlutil;
import com.redsparkle.foe.utils.ScreenGrid;
import com.redsparkle.foe.utils.gui.GuiButtonExtFallout;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Created by hoijima on 13.06.17.
 */
public class LvlUpGui extends GuiScreen {

    public Integer counter = 0;
    public Boolean CommitShow = false;
    public int startY = 15;
    GuiButtonExtFallout BGplus = new GuiButtonExtFallout(0,0,0,0,0, "+");
    GuiButtonExtFallout SGPlus = new GuiButtonExtFallout(1,0,0,0,0, "+");
    GuiButtonExtFallout EWPlus = new GuiButtonExtFallout(2,0,0,0,0, "+");
    GuiButtonExtFallout EXPLus = new GuiButtonExtFallout(3,0,0,0,0, "+");
    GuiButtonExtFallout MWPLus = new GuiButtonExtFallout(4,0,0,0,0, "+");
    GuiButtonExtFallout UNPlus = new GuiButtonExtFallout(5,0,0,0,0, "+");
    GuiButtonExtFallout MEDPLus = new GuiButtonExtFallout(6,0,0,0,0, "+");
    GuiButtonExtFallout LOCKPLus = new GuiButtonExtFallout(7,0,0,0,0, "+");
    GuiButtonExtFallout REPPLus = new GuiButtonExtFallout(8,0,0,0,0, "+");
    GuiButtonExtFallout SCIPlus = new GuiButtonExtFallout(9,0,0,0,0, "+");
    GuiButtonExtFallout SNIKPLus = new GuiButtonExtFallout(10,0,0,0,0, "+");
    GuiButtonExtFallout BARTPLus = new GuiButtonExtFallout(11,0,0,0,0, "+");
    GuiButtonExtFallout BGminus = new GuiButtonExtFallout(12,0,0,0,0, "-");
    GuiButtonExtFallout SGminus = new GuiButtonExtFallout(13,0,0,0,0, "-");
    GuiButtonExtFallout EWminus = new GuiButtonExtFallout(14,0,0,0,0, "-");
    GuiButtonExtFallout EXminus = new GuiButtonExtFallout(15,0,0,0,0, "-");
    GuiButtonExtFallout MWminus = new GuiButtonExtFallout(16,0,0,0,0, "-");
    GuiButtonExtFallout UNminus = new GuiButtonExtFallout(17,0,0,0,0, "-");
    GuiButtonExtFallout MEDminus = new GuiButtonExtFallout(18,0,0,0,0, "-");
    GuiButtonExtFallout LOCKminus = new GuiButtonExtFallout(19,0,0,0,0, "-");
    GuiButtonExtFallout REPminus = new GuiButtonExtFallout(20,0,0,0,0, "-");
    GuiButtonExtFallout SCIminus = new GuiButtonExtFallout(21,0,0,0,0, "-");
    GuiButtonExtFallout SNIKminus = new GuiButtonExtFallout(22,0,0,0,0, "-");
    GuiButtonExtFallout BARTminus = new GuiButtonExtFallout(23,0,0,0,0, "-");
    GuiButtonExtFallout Commit = new GuiButtonExtFallout(24,0,0,0,0, "Level up!");
    Integer[] buttonsIdsPlus = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    Integer[] buttonsIdsMinus = {12, 13, 14, 15, 16, 17, 18,19,20 , 21, 22, 23};
    GuiButtonExtFallout[] buttonsPLus = {
            BGplus,SGPlus,EWPlus,
            EXPLus,MWPLus,UNPlus,
            MEDPLus,LOCKPLus,REPPLus,
            SCIPlus,SNIKPLus,BARTPLus
    };
    GuiButtonExtFallout[] buttonsMinus = {
            BGminus,SGminus,EWminus,
            EXminus,MWminus,UNminus,
            MEDminus,LOCKminus,REPminus,
            SCIminus,SNIKminus,BARTminus
    };
    int[] temp = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    Integer[] finished = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    Integer pointsAvailable =0 ;

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
                                2)+ (i*15)+ 30,
                        8453920, true
                );
                this.fontRendererObj.drawString(Integer.toString(
                        params[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2)+215,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2)+(i*15)+ 30,
                        8453920, true
                );
                this.fontRendererObj.drawString("+ " +Integer.toString(
                        temp[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2)+225,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2)+(i*15)+ 30,
                        8453920, true
                );

                this.fontRendererObj.drawString(Integer.toString(
                        finished[i] =temp[i]+params[i] ),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2)+275,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2)+(i*15)+ 30,
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
                    this.buttonList.get(buttonsIdsPlus[o]).yPosition = ScreenGrid.XCoordStart(this.height,2) + (o*15)+ 30;
                    this.buttonList.get(buttonsIdsPlus[o]).height = 12;
                    this.buttonList.get(buttonsIdsPlus[o]).width = 10;

                    this.buttonList.get(buttonsIdsMinus[o]).xPosition = ScreenGrid.XCoordStart(this.width,2) + 258;
                    this.buttonList.get(buttonsIdsMinus[o]).yPosition = ScreenGrid.XCoordStart(this.height,2) + (o*15)+ 30;
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
