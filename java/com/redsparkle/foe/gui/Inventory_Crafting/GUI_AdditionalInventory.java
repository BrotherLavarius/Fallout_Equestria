package com.redsparkle.foe.gui.Inventory_Crafting;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.containers.CONTAINER_AdditionalInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by hoijima on 27.06.17.
 */
public class GUI_AdditionalInventory extends GuiContainer {

    //TODO: finish this class
    private static ResourceLocation GUI_TEXTURE = new ResourceLocation(GlobalNames.Domain + ":textures/gui/ainv.png");


    public GUI_AdditionalInventory(EntityPlayer player) {
        super(new CONTAINER_AdditionalInventory(player));


    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);


    }

    @Override
    public void onGuiClosed() {
        if (this.mc.player != null) {
            System.out.println("GUI CLOSED!");
            this.inventorySlots.onContainerClosed(this.mc.player);
        }
    }
}
