package com.redsparkle.foe.gui.Menus;

import com.redsparkle.api.utils.*;
import com.redsparkle.api.utils.gui.GuiButtonExtFallout;
import com.redsparkle.api.utils.gui.GuiButtonExtFallout_pipbuck;
import com.redsparkle.foe.Init.ConfigInit;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.DATA.DataGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.ITEMS.InventoryGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.STATS.StatsGui;
import com.redsparkle.foe.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Collections;
/**
 * Created by hoijima on 3/4/2017.
 */
public class PipBuckGui extends GuiScreen {
    final ResourceLocation pipbuck = new ResourceLocation(GlobalNames.Domain,
            "textures/gui/pipbuck_bg.png");

    public int pip_buck_x = 0;
    public int pip_buck_y = 0;
    public boolean StatsShowButton = false;
    public boolean InvShowButton = true;
    public boolean DataShowButton = true;
    public boolean Stats_Nav = true;
    public boolean Inv_Nav = false;
    public boolean Data_Nav = false;
    public boolean Stats_STATUS = true;
    public boolean Stats_SPECHIAL = false;
    public boolean Stats_SKILLS = false;
    public boolean Stats_PERKS = false;
    public boolean Inventory_WEAPONS = false;
    public boolean Inventory_Apparel = false;
    public boolean Inventory_Aid = false;
    public boolean Inventory_Ammo = false;
    public boolean Inventory_MiscInv = false;
    public boolean Inventory_Mod = false;
    public boolean Inventory_Repair = false;
    public boolean Data_World_Map = false;
    public boolean Data_Misc = false;
    public boolean Data_Radio = false;
    public boolean Radio_isOff = true;
    public boolean Radio_stopVisible = false;
    public int playerStatusX = 0;
    public int playerStatusY = 0;
    EntityPlayer player = Minecraft.getMinecraft().player;
    GuiButtonExtFallout_pipbuck Stats = new GuiButtonExtFallout_pipbuck(0,
            0,
            0,
            0,
            0, "STATS");
    GuiButtonExtFallout_pipbuck Inventory = new GuiButtonExtFallout_pipbuck(1,
            0,
            0,
            0,
            0, "ITEMS");
    GuiButtonExtFallout_pipbuck Data = new GuiButtonExtFallout_pipbuck(2,
            0,
            0,
            0,
            0, "DATA");
    GuiButtonExtFallout LvlUp = new GuiButtonExtFallout(3,
            0,
            0,
            0,
            0, "Level Up");
    Minecraft mc = Minecraft.getMinecraft();
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Integer[] skills = PlayerStatsRequester.skills(mc.player);
        Integer[] spechials = PlayerStatsRequester.spechials(mc.player);
        Integer[] playerParams = PlayerStatsRequester.lvl(mc.player);
        Integer[] player_RAD_WATER = PlayerStatsRequester.additionalStats(mc.player);
        this.zLevel = 0;
        this.drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(pipbuck);
        GL11.glPushMatrix();
        GL11.glScalef((float) 2.9, (float) 2.9, (float) 2.9);
        drawTexturedModalRect(
                ScreenGrid.XCoordStart(
                        this.width,
                        2) - 3,
                ScreenGrid.YCoordStart(
                        this.height,
                        2) - 2,
                pip_buck_x,
                pip_buck_y,
                119,
                80);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        //STATS BUTTON
        {
            this.buttonList.get(0).x = ScreenGrid.XCoordStart(
                    this.width,
                    2) + 98;
            this.buttonList.get(0).y = ScreenGrid.XCoordStart(
                    this.height,
                    2) + 185;
            this.buttonList.get(0).height = 29;
            this.buttonList.get(0).width = 57;
            this.buttonList.get(0).enabled = StatsShowButton;
        }
        //INVENTORY BUTTON
        {
            this.buttonList.get(1).x = ScreenGrid.XCoordStart(
                    this.width,
                    2) + 188;
            this.buttonList.get(1).y = ScreenGrid.XCoordStart(
                    this.height,
                    2) + 185;
            this.buttonList.get(1).height = 29;
            this.buttonList.get(1).width = 57;
            this.buttonList.get(1).enabled = InvShowButton;
        }
        //DATA BUTTON
        {
            this.buttonList.get(2).x = ScreenGrid.XCoordStart(
                    this.width,
                    2) + 275;
            this.buttonList.get(2).y = ScreenGrid.XCoordStart(
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
            //---------------------NAV BLOCK STATS-------------------START
            for (int inv = 4; inv < 8; inv++) {
                this.buttonList.get(inv).x = ScreenGrid.XCoordStart(
                        this.width,
                        2) + (inv * 60) - 148;
                this.buttonList.get(inv).y = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 145;
                this.buttonList.get(inv).height = 15;
                this.buttonList.get(inv).width = 55;
                if (Stats_Nav) {
                    this.buttonList.get(inv).enabled = true;
                    this.buttonList.get(inv).visible = true;
                } else {
                    this.buttonList.get(inv).enabled = false;
                    this.buttonList.get(inv).visible = false;
                }
            }
            //---------------------NAV BLOCK STATS-------------------END
            {   //LVLUp BUTTON
                this.buttonList.get(3).x = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 275;
                this.buttonList.get(3).y = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 125;
                this.buttonList.get(3).height = 14;
                this.buttonList.get(3).width = 49;
                if (Stats_STATUS) {
                    this.buttonList.get(3).visible = true;
                    try {
                        this.buttonList.get(3).enabled = Lvlutil.canLvlup(playerParams[0], playerParams[1]);
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                    }
                } else {
                    this.buttonList.get(3).visible = false;
                    this.buttonList.get(3).enabled = false;
                }
            }
            {
                if (Stats_STATUS) {
                    // TOP INFO DISPLAY
                    this.fontRenderer.drawString(
                            "|LVL  " + Integer.toString(playerParams[0]) + "| " +
                                    "HP  :" + Integer.toString(Math.round(player.getHealth())) + "/" + Float.toString(player.getMaxHealth()) + "| " +
                                    "AP  :" + player.getFoodStats().getFoodLevel() + "| " +
                                    "XP  :" + Integer.toString(playerParams[1]) + "/" +
                                    Lvlutil.lvls[playerParams[0]] + "|",
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 195,
                            ScreenGrid.XCoordStart(
                                    this.height,
                                    2) + 70,
                            15465844, true
                    );
                    this.fontRenderer.drawString(
                            player.getDisplayNameString() + " - " + Integer.toString(playerParams[0]),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 245,
                            ScreenGrid.XCoordStart(
                                    this.height,
                                    2) + 90,
                            15465844, true
                    );
                    this.fontRenderer.drawString(
                            "Water lvl:" + Integer.toString(player_RAD_WATER[1]),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 145,
                            ScreenGrid.XCoordStart(
                                    this.height,
                                    2) + 100,
                            15465844, true
                    );
                    this.fontRenderer.drawString(
                            Lvlutil.progress(100, player_RAD_WATER[1]),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 145,
                            ScreenGrid.XCoordStart(
                                    this.height,
                                    2) + 110,
                            15465844, true
                    );
                    GL11.glScaled(1.5,1.5,1.5);
//                    if (Math.round(player.getHealth()) < (Math.round(player.getMaxHealth() / 4))) {
//
//                    } else if (Math.round(player.getHealth()) > (Math.round(player.getMaxHealth() / 4)) && player.getHealth() < (Math.round(player.getMaxHealth() / 3))) {
//
//                    } else if (Math.round(player.getHealth()) > (Math.round(player.getMaxHealth() / 3)) && player.getHealth() < (Math.round(player.getMaxHealth() / 2))) {
//                    } else if (player.getHealth() > (Math.round(player.getMaxHealth() / 2)) && Math.round(player.getHealth()) <= Math.round(player.getMaxHealth())) {
//                    }
                    if(player.getHealth() == player.getMaxHealth() || player.getHealth() >= (player.getMaxHealth() -Math.round(player.getMaxHealth() / 3))){
                        playerStatusX = 48;
                        playerStatusY = 95;
                    }
                    if(player.getHealth() <= (player.getMaxHealth() -Math.round(player.getMaxHealth() / 3))){
                        playerStatusX = 116;
                        playerStatusY = 95;
                    }
                    if(player.getHealth() <= (player.getMaxHealth() -Math.round(player.getMaxHealth() / 2))){
                        playerStatusX = 48;
                        playerStatusY = 177;
                    }
                    if(player.getHealth() <= Math.round(player.getMaxHealth() / 3)){
                        playerStatusX = 116;
                        playerStatusY = 177;
                    }
                    mc.getTextureManager().bindTexture(pipbuck);
                    GL11.glPushMatrix();
                    //GL11.glScalef((float) 2, (float) 2, 1.0f);
                    drawTexturedModalRect(
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 155,
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    2) + 65,
                            playerStatusX,
                            playerStatusY,
                            70,
                            75);
                    GL11.glPopMatrix();
                }
                if (Stats_SPECHIAL) {
                    for (int spechial = 0; spechial < (StatsGui.spechialName.length - 1); spechial++) {
                        this.fontRenderer.drawString(
                                StatsGui.spechialName[spechial] + " : " + Integer.toString(spechials[spechial]),
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2) + 165,
                                ScreenGrid.XCoordStart(
                                        this.height,
                                        2) + (spechial * 15) + 85,
                                15465844, true
                        );
                    }
                }
                if (Stats_SKILLS) {
                    for (int skillsFR = 0; skillsFR < 6; skillsFR++) {
                        this.fontRenderer.drawString(
                                StatsGui.skillsNames[skillsFR] + " : " + Integer.toString(skills[skillsFR]),
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2) + 165,
                                ScreenGrid.XCoordStart(
                                        this.height,
                                        2) + (skillsFR * 15) + 85,
                                15465844, true
                        );
                    }
                    for (int skillsSR = 6; skillsSR < 13; skillsSR++) {
                        this.fontRenderer.drawString(
                                StatsGui.skillsNames[skillsSR] + " : " + Integer.toString(skills[skillsSR]),
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2) + 315,
                                ScreenGrid.XCoordStart(
                                        this.height,
                                        2) + (skillsSR * 15) -15,
                                15465844, true
                        );
                    }
                }
                if (Stats_PERKS) {
                }
            }
            //---------------------NAV BLOCK INV-------------------START
            for (int inv = 14; inv < 19; inv++) {
                this.buttonList.get(inv).x = ScreenGrid.XCoordStart(
                        this.width,
                        2) + (inv * 46) - 545;
                this.buttonList.get(inv).y = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 145;
                this.buttonList.get(inv).height = 15;
                this.buttonList.get(inv).width = 48;
                if (Inv_Nav) {
                    this.buttonList.get(inv).enabled = true;
                    this.buttonList.get(inv).visible = true;
                } else {
                    this.buttonList.get(inv).enabled = false;
                    this.buttonList.get(inv).visible = false;
                }
            }
            //---------------------NAV BLOCK INV-------------------END
            if (InvShowButton) {
            }
            {// DATA BLOCK MAIN
                //---------------------NAV BLOCK DATA-------------------START
                for (int data = 21; data < 24; data++) {
                    this.buttonList.get(data).x = ScreenGrid.XCoordStart(
                            this.width,
                            2) + (data * 60) - 1150;
                    this.buttonList.get(data).y = ScreenGrid.XCoordStart(
                            this.height,
                            2) + 145;
                    this.buttonList.get(data).height = 15;
                    this.buttonList.get(data).width = 75;
                    if (Data_Nav) {
                        this.buttonList.get(data).enabled = true;
                        this.buttonList.get(data).visible = true;
                    } else {
                        this.buttonList.get(data).enabled = false;
                        this.buttonList.get(data).visible = false;
                    }
                }
                //---------------------NAV BLOCK DATA-------------------END
                {// MAP
                    if (Data_World_Map) {
                        //ItemMap map = new ItemMap(Items.FILLED_MAP);
                        //ItemMap.setupNewMap();
                    }
                }
                //RADIO START BUTTON
                {

                    for (int radio = 24; radio < 27; radio++) {
                        this.buttonList.get(radio).x = ScreenGrid.XCoordStart(
                                this.width,
                                2) + 120;
                        this.buttonList.get(radio).y = ScreenGrid.XCoordStart(
                                this.height,
                                2) + (radio * 15) - 290;
                        this.buttonList.get(radio).height = 15;
                        this.buttonList.get(radio).width = 85;
                        if (Data_Radio) {
                            this.buttonList.get(radio).visible = true;
                            this.buttonList.get(radio).enabled = Radio_isOff;
                        } else {
                            this.buttonList.get(radio).visible = false;
                            this.buttonList.get(radio).enabled = Radio_isOff;
                        }
                    }
                    this.buttonList.get(27).x = ScreenGrid.XCoordStart(
                            this.width,
                            2) + 125;
                    this.buttonList.get(27).y = ScreenGrid.XCoordStart(
                            this.height,
                            2) + (27 * 15) - 290;
                    this.buttonList.get(27).height = 15;
                    this.buttonList.get(27).width = 70;
                    if (Data_Radio) {
                        this.buttonList.get(27).visible = true;
                        this.buttonList.get(27).enabled = Radio_stopVisible;
                    } else {
                        this.buttonList.get(27).visible = false;
                        this.buttonList.get(27).enabled = Radio_stopVisible;
                    }
                }
                //RADIO VOLUME
                {
                    for (int vol = 28; vol <= 29; vol++) {
                        this.buttonList.get(vol).x = ScreenGrid.XCoordStart(
                                this.width,
                                2) + 245;
                        this.buttonList.get(vol).y = ScreenGrid.XCoordStart(
                                this.height,
                                2) + (vol * 15) - 310;
                        this.buttonList.get(vol).height = 15;
                        this.buttonList.get(vol).width = 60;
                        if (Data_Radio) {
                            this.buttonList.get(vol).visible = true;
                            this.buttonList.get(vol).enabled = true;
                        } else {
                            this.buttonList.get(vol).visible = false;
                            this.buttonList.get(vol).enabled = false;
                        }
                    }
                }
                if (RadioThreadManager.isTreadAlive()) {
                    Radio_isOff = false;
                    Radio_stopVisible = true;
                }
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
        Collections.addAll(this.buttonList, StatsGui.buttonsSTATNavigation);
        Collections.addAll(this.buttonList, StatsGui.buttonsSTATmain);
        Collections.addAll(this.buttonList, InventoryGui.buttonsINVmain);
        Collections.addAll(this.buttonList, DataGui.buttonsDATAmain);
        Collections.addAll(this.buttonList, DataGui.buttonsDATARadio);
        super.initGui();
    }
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == this.Stats) {
            StatsShowButton = false;
            InvShowButton = true;
            DataShowButton = true;
            Stats_Nav = true;
            Inv_Nav = false;
            Data_Nav = false;
            Stats_STATUS = true;
            Stats_SPECHIAL = false;
            Stats_SKILLS = false;
            Stats_PERKS = false;
            {
                Inventory_WEAPONS = false;
                Inventory_Apparel = false;
                Inventory_Aid = false;
                Inventory_Ammo = false;
                Inventory_MiscInv = false;
                {
                    Inventory_Mod = false;
                    Inventory_Repair = false;
                }
            }
            {
                Data_World_Map = false;
                Data_Misc = false;
                Data_Radio = false;
            }
        }
        if (button == this.Inventory) {
            StatsShowButton = true;
            InvShowButton = false;
            DataShowButton = true;
            Stats_Nav = false;
            Inv_Nav = true;
            Data_Nav = false;
            Stats_STATUS = false;
            Stats_SPECHIAL = false;
            Stats_SKILLS = false;
            Stats_PERKS = false;
            {
                Inventory_WEAPONS = true;
                Inventory_Apparel = false;
                Inventory_Aid = false;
                Inventory_Ammo = false;
                Inventory_MiscInv = false;
                {
                    Inventory_Mod = false;
                    Inventory_Repair = false;
                }
            }
            Data_World_Map = false;
            Data_Misc = false;
            Data_Radio = false;
        }
        if (button == this.Data) {
            StatsShowButton = true;
            InvShowButton = true;
            DataShowButton = false;
            Stats_Nav = false;
            Inv_Nav = false;
            Data_Nav = true;
            Stats_STATUS = false;
            Stats_SPECHIAL = false;
            Stats_SKILLS = false;
            Stats_PERKS = false;
            {
                Inventory_WEAPONS = false;
                Inventory_Apparel = false;
                Inventory_Aid = false;
                Inventory_Ammo = false;
                Inventory_MiscInv = false;
                {
                    Inventory_Mod = false;
                    Inventory_Repair = false;
                }
            }
            {
                Data_World_Map = true;
                Data_Misc = false;
                Data_Radio = false;
            }
        }
        //LvlUp button
        if (button == this.buttonList.get(3)) {
            this.mc.player.openGui(main.instance, 1, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);
        }
        //buttonsSTATNavigation 5-8
        if (button == this.buttonList.get(4)) {
            Stats_STATUS = true;
            Stats_SPECHIAL = false;
            Stats_SKILLS = false;
            Stats_PERKS = false;
        }
        if (button == this.buttonList.get(5)) {
            Stats_STATUS = false;
            Stats_SPECHIAL = true;
            Stats_SKILLS = false;
            Stats_PERKS = false;
        }
        if (button == this.buttonList.get(6)) {
            Stats_STATUS = false;
            Stats_SPECHIAL = false;
            Stats_SKILLS = true;
            Stats_PERKS = false;
        }
        if (button == this.buttonList.get(7)) {
            Stats_STATUS = false;
            Stats_SPECHIAL = false;
            Stats_SKILLS = false;
            Stats_PERKS = true;
        }
        //buttonsINVmain 15-21
        if (button == this.buttonList.get(14)) {
            Inventory_WEAPONS = true;
            Inventory_Apparel = false;
            Inventory_Aid = false;
            Inventory_Ammo = false;
            Inventory_MiscInv = false;
        }
        if (button == this.buttonList.get(15)) {
            Inventory_WEAPONS = false;
            Inventory_Apparel = true;
            Inventory_Aid = false;
            Inventory_Ammo = false;
            Inventory_MiscInv = false;
        }
        if (button == this.buttonList.get(16)) {
            Inventory_WEAPONS = false;
            Inventory_Apparel = false;
            Inventory_Aid = true;
            Inventory_Ammo = false;
            Inventory_MiscInv = false;
        }
        if (button == this.buttonList.get(17)) {
            Inventory_WEAPONS = false;
            Inventory_Apparel = false;
            Inventory_Aid = false;
            Inventory_Ammo = true;
            Inventory_MiscInv = false;
        }
        if (button == this.buttonList.get(18)) {
            Inventory_WEAPONS = false;
            Inventory_Apparel = false;
            Inventory_Aid = false;
            Inventory_Ammo = false;
            Inventory_MiscInv = true;
        }
        //MOD
        if (button == this.buttonList.get(19)) {
            Inventory_WEAPONS = false;
            Inventory_Apparel = false;
            Inventory_Aid = false;
            Inventory_Ammo = false;
            Inventory_MiscInv = false;
        }
        //Repair
        if (button == this.buttonList.get(20)) {
            Inventory_WEAPONS = false;
            Inventory_Apparel = false;
            Inventory_Aid = false;
            Inventory_Ammo = false;
            Inventory_MiscInv = false;
        }
        //buttonsDATAmain 21-23
        if (button == this.buttonList.get(21)) {
            Data_World_Map = true;
            Data_Misc = false;
            Data_Radio = false;
        }
        if (button == this.buttonList.get(22)) {
            Data_World_Map = false;
            Data_Misc = true;
            Data_Radio = false;
        }
        if (button == this.buttonList.get(23)) {
            Data_World_Map = false;
            Data_Misc = false;
            Data_Radio = true;
        }
        //RadioButtonStart 24-27
        //Radio start
        if (button == this.buttonList.get(24)) {

                Radio_isOff = false;
                Radio_stopVisible = true;
            RadioThreadManager.SpawnRadio("http://home.fallout-equestria.tk:8100/rcr.ogg");

        }
        //Radio start
        if (button == this.buttonList.get(25)) {
                Radio_isOff = false;
                Radio_stopVisible = true;
            RadioThreadManager.SpawnRadio(ConfigInit.Radio1URL);

        }
        //Radio start
        if (button == this.buttonList.get(26)) {
                Radio_isOff = false;
                Radio_stopVisible = true;
            RadioThreadManager.SpawnRadio(ConfigInit.Radio2URL);

        }
        //Radio stop
        if (button == this.buttonList.get(27)) {

            Radio_isOff = true;
            Radio_stopVisible = false;
            RadioThreadManager.StopPlayer();
        }
        //Vol up
        if (button == this.buttonList.get(28)) {
            RadioThreadManager.increaseSound();

        }
        //Vol down
        if (button == this.buttonList.get(29)) {
            RadioThreadManager.decreaseSound();
        }
        super.actionPerformed(button);
    }
}
