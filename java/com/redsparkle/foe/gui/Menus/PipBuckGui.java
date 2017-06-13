package com.redsparkle.foe.gui.Menus;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.DATA.DataGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.ITEMS.InventoryGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.STATS.StatsGui;
import com.redsparkle.foe.utils.GlobalNames;
import com.redsparkle.foe.utils.Lvlutil;
import com.redsparkle.foe.utils.RadioPLayer;
import com.redsparkle.foe.utils.ScreenGrid;
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

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int startX = 0;
        int startY = 0;
        Item pipbuckI = ItemInit.pipbuck;
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
                    this.buttonList.get(3).enabled = Lvlutil.canLvlup(StatsGui.playerParams[0], StatsGui.playerParams[1]);
                } else {
                    this.buttonList.get(3).visible = false;
                    this.buttonList.get(3).enabled = false;
                }

            }
            if (Stats_STATUS) {
                // TOP INFO DISPLAY
                this.fontRendererObj.drawString(
                        "|LVL  " + Integer.toString(StatsGui.playerParams[0]) + "| " +
                                "HP  :" + Float.toString(player.getHealth()) + "/" + Float.toString(player.getMaxHealth()) + "| " +
                                "AP  :" + player.getFoodStats().getFoodLevel() + "| " +
                                "XP  :" + Integer.toString(StatsGui.playerParams[1]) + "/" +
                                Lvlutil.lvls[StatsGui.playerParams[0]] + "|",
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 195,
                        ScreenGrid.XCoordStart(
                                this.height,
                                2) + 70,
                        15465844, true
                );

                this.fontRendererObj.drawString(
                        player.getDisplayNameString() + " - " + Integer.toString(StatsGui.playerParams[0]),
                        ScreenGrid.XCoordStart(
                                this.width,
                                2) + 245,
                        ScreenGrid.XCoordStart(
                                this.height,
                                2) + 90,
                        15465844, true
                );

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
                        pip_buck_x + 135,
                        pip_buck_y + 145,
                        40,
                        55);
                GL11.glPopMatrix();

            }

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
        for (int i = 0; i < InventoryGui.buttonsINVmain.length; i++) {
            this.buttonList.add(InventoryGui.buttonsINVmain[i]);//15-21
        }
        for (int i = 0; i < DataGui.buttonsDATAmain.length; i++) {
            this.buttonList.add(DataGui.buttonsDATAmain[i]);//22-24
        }
        for (int i = 0; i < DataGui.buttonsDATARadio.length; i++) {
            this.buttonList.add(DataGui.buttonsDATARadio[i]);//25-28
        }

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

        //buttonsSTATNavigation 5-8
        if (button == this.buttonList.get(5)) {
        }
        if (button == this.buttonList.get(6)) {
        }
        if (button == this.buttonList.get(7)) {
        }
        if (button == this.buttonList.get(8)) {
        }
        //buttonsINVmain 15-21
        if (button == this.buttonList.get(15)) {
        }
        if (button == this.buttonList.get(16)) {
        }
        if (button == this.buttonList.get(17)) {
        }
        if (button == this.buttonList.get(18)) {
        }
        if (button == this.buttonList.get(19)) {
        }
        if (button == this.buttonList.get(20)) {
        }
        if (button == this.buttonList.get(21)) {
        }
        //buttonsDATAmain 22-24
        if (button == this.buttonList.get(22)) {
        }
        if (button == this.buttonList.get(23)) {
        }
        if (button == this.buttonList.get(24)) {
        }

//RadioButtonStart 25-28
        if (button == this.buttonList.get(25)) {

            //String[] address = new String[]{"http://10.0.0.12:8100/rcr.ogg"};
            radioPLayer = new RadioPLayer("http://fallout-equestria.tk/radio");


        }
        if (button == this.buttonList.get(26)) {

            if (radioPLayer.player.isAlive()) {
                radioPLayer.running = false;
                radioPLayer.line.close();
                radioPLayer.din.close();
            }

        }
        if (button == this.buttonList.get(27)) {

            if (radioPLayer.player.isAlive()) {
                gain = (FloatControl) radioPLayer.line.getControl(FloatControl.Type.MASTER_GAIN);

                if (gain.getValue() < gain.getMaximum()) {
                    gain.setValue(gain.getValue() + 1.0F);
                }
            }
        }
        if (button == this.buttonList.get(28)) {
            if (radioPLayer.player.isAlive()) {

                gain = (FloatControl) radioPLayer.line.getControl(FloatControl.Type.MASTER_GAIN);

                if (gain.getValue() > gain.getMinimum()) {
                    gain.setValue(gain.getValue() - 1.0F);
                }
            }
        }




        super.actionPerformed(button);
    }
}

