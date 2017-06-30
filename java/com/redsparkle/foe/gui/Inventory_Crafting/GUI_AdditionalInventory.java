package com.redsparkle.foe.gui.Inventory_Crafting;

import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by hoijima on 27.06.17.
 */
public class GUI_AdditionalInventory extends GuiContainer {

    //TODO: finish this class
    private static ResourceLocation GUI_TEXTURE;
    final EntityPlayer thePlayer;
    private CONTAINER_AdditionalInventory container;


    public GUI_AdditionalInventory(EntityPlayer player, InventoryPlayer inventoryPlayer) {
        // the player.inventory gets passed in here
        super(new CONTAINER_AdditionalInventory(player, inventoryPlayer));
        container = (CONTAINER_AdditionalInventory) this.inventorySlots;
        thePlayer = player;
        GUI_TEXTURE = new ResourceLocation(GlobalNames.Domain + ":textures/gui/ainv.png");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //this.fontRendererObj.drawString(new TextComponentTranslation("container.", new Object[0]).getUnformattedText(), 8, 6, 4210752);
        //this.fontRendererObj.drawString(new TextComponentTranslation("container.inventory", new Object[0]).getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);


    }
}
