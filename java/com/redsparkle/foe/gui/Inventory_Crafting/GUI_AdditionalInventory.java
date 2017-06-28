package com.redsparkle.foe.gui.Inventory_Crafting;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * Created by hoijima on 27.06.17.
 */
public class GUI_AdditionalInventory extends GuiContainer {

    public GUI_AdditionalInventory(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    /**
     * Draws the background layer of this container (behind the items).
     *
     * @param partialTicks
     * @param mouseX
     * @param mouseY
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
