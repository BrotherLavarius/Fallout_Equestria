package com.redsparkle.foe.gui.Menus;

import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.DATA.DataGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.ITEMS.InventoryGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.STATS.StatsGui;
import com.redsparkle.foe.main;
import com.redsparkle.foe.utils.*;
import com.redsparkle.foe.utils.gui.GuiButtonExtFallout;
import com.redsparkle.foe.utils.gui.GuiButtonExtFallout_pipbuck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.util.Collections;

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

    public boolean Radio_isOff= true;
    public boolean Radio_stopVisible= false;
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



        int startX = 0;
        int startY = 0;
        Item pipbuckI = GlobalItemArray_For_init.AllInit[0];
        ItemStack pipbuckStack = new ItemStack(pipbuckI);
        mc.getItemRenderer().renderItem(player, pipbuckStack, ItemCameraTransforms.TransformType.GUI);
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

            //---------------------NAV BLOCK STATS-------------------START
            for (int inv = 4; inv < 8; inv++) {
                this.buttonList.get(inv).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + (inv * 60) - 148;
                this.buttonList.get(inv).yPosition = ScreenGrid.XCoordStart(
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
                this.buttonList.get(3).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + 275;
                this.buttonList.get(3).yPosition = ScreenGrid.XCoordStart(
                        this.height,
                        2) + 125;

                this.buttonList.get(3).height = 14;
                this.buttonList.get(3).width = 49;
                if (Stats_STATUS) {
                    this.buttonList.get(3).visible = true;
                    try{
                        this.buttonList.get(3).enabled = Lvlutil.canLvlup(playerParams[0], playerParams[1]);
                    }catch(NullPointerException npe){
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
                    this.fontRendererObj.drawString(
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

                    this.fontRendererObj.drawString(
                            player.getDisplayNameString() + " - " + Integer.toString(playerParams[0]),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 245,
                            ScreenGrid.XCoordStart(
                                    this.height,
                                    2) + 90,
                            15465844, true
                    );

                    this.fontRendererObj.drawString(
                            "Water lvl:" + Integer.toString(player_RAD_WATER[1]),
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 145,
                            ScreenGrid.XCoordStart(
                                    this.height,
                                    2) + 100,
                            15465844, true
                    );
                    this.fontRendererObj.drawString(
                            Lvlutil.progress(100,player_RAD_WATER[1]) ,
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 145,
                            ScreenGrid.XCoordStart(
                                    this.height,
                                    2) + 110,
                            15465844, true
                    );


                    if(Math.round(player.getHealth()) < (Math.round(player.getMaxHealth()/4))){
                        playerStatusX= 137;
                        playerStatusY= 200;

                    }else if(Math.round(player.getHealth()) > (Math.round(player.getMaxHealth()/4)) && player.getHealth() < (Math.round(player.getMaxHealth()/3))){
                        playerStatusX= 91;
                        playerStatusY= 201;

                    }else if(Math.round(player.getHealth()) > (Math.round(player.getMaxHealth()/3)) && player.getHealth() < (Math.round(player.getMaxHealth()/2))){
                        playerStatusX= 135;
                        playerStatusY= 145;
                    }else if(player.getHealth() > (Math.round(player.getMaxHealth()/2)) && Math.round(player.getHealth()) <= Math.round(player.getMaxHealth())){
                    playerStatusX= 88;
                    playerStatusY= 145;
                    }

                    mc.getTextureManager().bindTexture(pipbuck);
                    GL11.glPushMatrix();
                    GL11.glScalef((float) 2, (float) 2, 1.0f);
                    drawTexturedModalRect(
                            ScreenGrid.XCoordStart(
                                    this.width,
                                    2) + 125,
                            ScreenGrid.YCoordStart(
                                    this.height,
                                    2) + 45,
                            playerStatusX,
                            playerStatusY,

                            40,
                            55);
                    GL11.glPopMatrix();
                }
                if(Stats_SPECHIAL){
                    for(int spechial=0; spechial < (StatsGui.spechialName.length-1);spechial++){
                        this.fontRendererObj.drawString(
                                StatsGui.spechialName[spechial] + " : " + Integer.toString(spechials[spechial]),
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2) + 165,
                                ScreenGrid.XCoordStart(
                                        this.height,
                                        2) + (spechial* 15)+ 85,
                                15465844, true
                        );
                    }
                }
                if(Stats_SKILLS){
                    for(int skillsFR=0; skillsFR < 6;skillsFR++){
                        this.fontRendererObj.drawString(
                                StatsGui.skillsNames[skillsFR] + " : " + Integer.toString(skills[skillsFR]),
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2) + 165,
                                ScreenGrid.XCoordStart(
                                        this.height,
                                        2) + (skillsFR* 15)+ 85,
                                15465844, true
                        );
                    }
                    for(int skillsSR=0; skillsSR < 5;skillsSR++){
                        this.fontRendererObj.drawString(
                                StatsGui.skillsNames[skillsSR+6] + " : " + Integer.toString(skills[skillsSR+6]),
                                ScreenGrid.XCoordStart(
                                        this.width,
                                        2) + 315,
                                ScreenGrid.XCoordStart(
                                        this.height,
                                        2) + (skillsSR* 15)+ 85,
                                15465844, true
                        );
                    }
                }
                if(Stats_PERKS){}

            }

            //---------------------NAV BLOCK INV-------------------START
            for (int inv = 14; inv < 19; inv++) {
                this.buttonList.get(inv).xPosition = ScreenGrid.XCoordStart(
                        this.width,
                        2) + (inv * 46) - 545;
                this.buttonList.get(inv).yPosition = ScreenGrid.XCoordStart(
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
                    this.buttonList.get(data).xPosition = ScreenGrid.XCoordStart(
                            this.width,
                            2) + (data * 60) - 1150;
                    this.buttonList.get(data).yPosition = ScreenGrid.XCoordStart(
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
                    if(Data_World_Map){
                        //ItemMap map = new ItemMap(Items.FILLED_MAP);
                        //ItemMap.setupNewMap();
                    }

                }

                //RADIO START BUTTON
                {
                    for(int radio=24; radio<27;radio++) {
                        this.buttonList.get(radio).xPosition = ScreenGrid.XCoordStart(
                                this.width,
                                2) + 120;
                        this.buttonList.get(radio).yPosition = ScreenGrid.XCoordStart(
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

                        this.buttonList.get(27).xPosition = ScreenGrid.XCoordStart(
                                this.width,
                                2) + 125;
                        this.buttonList.get(27).yPosition = ScreenGrid.XCoordStart(
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
                    for(int vol=28;vol<= 29;vol++) {
                        this.buttonList.get(vol).xPosition = ScreenGrid.XCoordStart(
                                this.width,
                                2) + 245;
                        this.buttonList.get(vol).yPosition = ScreenGrid.XCoordStart(
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
            Radio_isOff= false;
            Radio_stopVisible= true;
            radioPLayer = new RadioPLayer("http://home.fallout-equestria.tk:8100/rcr.ogg");
        }
        //Radio start
        if (button == this.buttonList.get(25)) {
            Radio_isOff= false;
            Radio_stopVisible= true;

            radioPLayer = new RadioPLayer("http://192.99.131.205:8000/pvfm1.ogg");
        }
        //Radio start
        if (button == this.buttonList.get(26)) {
            Radio_isOff= false;
            Radio_stopVisible= true;

            radioPLayer = new RadioPLayer("http://62.210.138.34:8000/ogg");
       }
        //Radio stop
        if (button == this.buttonList.get(27)) {
            Radio_isOff= true;
            Radio_stopVisible= false;


            if (radioPLayer.player.isAlive()) {
                radioPLayer.running = false;
                radioPLayer.line.close();
                radioPLayer.din.close();
            }

        }
        //Vol up

        if (button == this.buttonList.get(28)) {

            if (radioPLayer.player.isAlive()) {
                gain = (FloatControl) radioPLayer.line.getControl(FloatControl.Type.MASTER_GAIN);

                if (gain.getValue() < (gain.getMaximum()-2.0F)) {
                    gain.setValue(gain.getValue() + 1.0F);
                }
            }
        }
        //Vol down
        if (button == this.buttonList.get(29)) {
            if (radioPLayer.player.isAlive()) {

                gain = (FloatControl) radioPLayer.line.getControl(FloatControl.Type.MASTER_GAIN);

                if (gain.getValue() > (gain.getMinimum()+2.0F)) {
                    gain.setValue(gain.getValue() - 1.0F);
                }
            }
        }




        super.actionPerformed(button);
    }
}

