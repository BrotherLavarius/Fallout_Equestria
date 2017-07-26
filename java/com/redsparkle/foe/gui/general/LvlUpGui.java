package com.redsparkle.foe.gui.general;

import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.utils.Lvlutil;
import com.redsparkle.api.utils.ScreenGrid;
import com.redsparkle.api.utils.gui.GuiButtonExtFallout;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSkills;
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
    public int index=0;
    GuiButtonExtFallout MagicPlus = new GuiButtonExtFallout(index, 0, 0, 0, 0, "+");
    GuiButtonExtFallout MagicMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout Melee_WeaponsPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout Melee_WeaponsMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout FirearmsPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout FirearmsMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout EneryWeaponsPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout EneryWeaponsMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout Saddlebag_GunsPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout Saddlebag_GunsMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout ExplosivesPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout ExplosivesMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout RepairPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout RepairMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout MedicinePlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout MedicineMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout LockpickingPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout LockpickingMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout SciencePlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout ScienceMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout SneakPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout SneakMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout BarterPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout BarterMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");

    GuiButtonExtFallout SurvivalPlus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "+");
    GuiButtonExtFallout SurvivalMinus = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "-");


    GuiButtonExtFallout Commit = new GuiButtonExtFallout(index++, 0, 0, 0, 0, "Level up!");
    Integer[] buttonsIdsPlus = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22,24};
    Integer[] buttonsIdsMinus = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23,25};
    GuiButtonExtFallout[] buttonsPLus = {
           MagicPlus,Melee_WeaponsPlus,FirearmsPlus,EneryWeaponsPlus,Saddlebag_GunsPlus,
            ExplosivesPlus,RepairPlus,MedicinePlus,LockpickingPlus,SciencePlus,
            SneakPlus,BarterPlus,SurvivalPlus
    };
    GuiButtonExtFallout[] buttonsMinus = {
            MagicMinus,Melee_WeaponsMinus,FirearmsMinus,EneryWeaponsMinus,Saddlebag_GunsMinus,
            ExplosivesMinus,RepairMinus,MedicineMinus,LockpickingMinus,ScienceMinus,
            SneakMinus,BarterMinus,SurvivalMinus
    };
    int[] temp = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0};
    Integer[] finished = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0};
    Integer pointsAvailable = 0;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int startY = 15;
        this.drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        /**
         Magic
         Melee_Weapons
         Firearms
         EneryWeapons
         Saddlebag_Guns
         Explosives
         Repair
         Medicine
         Lockpicking
         Science
         Sneak
         Barter
         Survival
         */
        Integer[] params = {
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMagic(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMelee(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getEnergyWeapons(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getSaddlebag_guns(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getExplosives(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getRepair(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMedicine(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getLockpick(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getScience(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getSneak(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getBarter(),
                mc.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getSurvival(),

        };
        String[] names = {
                "Magic: ",
                "Melee_Weapons: ",
                "Firearms: ",
                "Energy Weapons: ",
                "Saddlebag_Guns: ",
                "Explosives: ",
                "Repair: ",
                "Medicine: ",
                "Lockpicking: ",
                "Science: ",
                "Sneak: ",
                "Barter: ",
                "Survival: "

        };
        GL11.glPushMatrix();
        {
            GL11.glScalef((float) 1.0, (float) 1.0, 1.0f);
            for (int i = 0; i < params.length ; i++) {
                this.fontRendererObj.drawString(names[i],
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 25,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2) + (i * 15) + 30,
                        8453920, true
                );
                this.fontRendererObj.drawString(Integer.toString(
                        params[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 215,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2) + (i * 15) + 30,
                        8453920, true
                );
                this.fontRendererObj.drawString(" + " + Integer.toString(
                        temp[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 225,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2) + (i * 15) + 30,
                        8453920, true
                );

                this.fontRendererObj.drawString(Integer.toString(
                        finished[i] = temp[i] + params[i]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 275,
                        ScreenGrid.YCoordStart(
                                this.height,
                                2) + (i * 15) + 30,
                        8453920, true
                );


                startY = startY + 4;
            }
            pointsAvailable = Lvlutil.ponitsAvailable(
                    mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel(),
                    mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress()
            );
            this.fontRendererObj.drawString("Points available: " + Integer.toString(pointsAvailable - IntStream.of(temp).sum()),
                    ScreenGrid.XCoordStart(
                            this.width,
                            2) + 30,
                    ScreenGrid.YCoordStart(
                            this.height,
                            2) + 10,
                    8453920, true
            );


            {
                int startYButtons = 36;
                for (int o = 0; o < buttonsIdsPlus.length ; o++) {

                        this.buttonList.get(buttonsIdsPlus[o]).xPosition = ScreenGrid.XCoordStart(this.width, 2) + 248;
                        this.buttonList.get(buttonsIdsPlus[o]).yPosition = ScreenGrid.XCoordStart(this.height, 2) + (o * 15) + 30;
                        this.buttonList.get(buttonsIdsPlus[o]).height = 12;
                        this.buttonList.get(buttonsIdsPlus[o]).width = 10;

                        this.buttonList.get(buttonsIdsMinus[o]).xPosition = ScreenGrid.XCoordStart(this.width, 2) + 258;
                        this.buttonList.get(buttonsIdsMinus[o]).yPosition = ScreenGrid.XCoordStart(this.height, 2) + (o * 15) + 30;
                        this.buttonList.get(buttonsIdsMinus[o]).height = 10;
                        this.buttonList.get(buttonsIdsMinus[o]).width = 9;

                    startYButtons = startYButtons + 13;
                }

            }
            this.buttonList.get(26).xPosition = ScreenGrid.XCoordStart(this.width, 2) + 30;
            this.buttonList.get(26).yPosition = ScreenGrid.XCoordStart(this.height, 2) + 10;
            this.buttonList.get(26).height = 21;
            this.buttonList.get(26).width = 80;
            this.buttonList.get(26).enabled = CommitShow;
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

        for (int i = 0; i < buttonsPLus.length ; i++) {
            this.buttonList.add(buttonsPLus[i]);
        }
        for (int i = 0; i < buttonsMinus.length ; i++) {
            this.buttonList.add(buttonsMinus[i]);
        }
        this.buttonList.add(Commit);
        super.initGui();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        for (int i = 0; i < temp.length; i++) {
            counter = IntStream.of(temp).sum();
            if (pointsAvailable > 0) {
                if (pointsAvailable != counter) {
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
        if (button == this.Commit) {
            main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientServerSkills(finished));
            for (int i = 0; i <= (finished.length - 1); i++) {
                finished[i] = 0;
                temp[i] = 0;
                this.mc.displayGuiScreen(null);
            }

        }

    }
}
