package com.redsparkle.foe.handlers;

import com.redsparkle.foe.gui.Inventory_Crafting.Saddlebags;
import com.redsparkle.foe.gui.Menus.PipBuckGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.DataGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.InventoryGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.StatsGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.StatsGuiFirstJoin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 3/15/2017.
 */
public class GuiHandler implements IGuiHandler {
    public static final int PIPBUCK_GUI = 0;
    public static final int PIPBUCK_GUI_STAT = 1;
    public static final int PIPBUCK_GUI_INVE = 2;
    public static final int PIPBUCK_GUI_DATA = 3;
    public static final int FIRTS_TIME_LVLUP = 4;
    public static final int SADDLE_BAGS = 5;


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == PIPBUCK_GUI){return new PipBuckGui();}
        if (ID == PIPBUCK_GUI_STAT){return new StatsGui();}
        if (ID == PIPBUCK_GUI_INVE){return new InventoryGui();}
        if (ID == PIPBUCK_GUI_DATA){return new DataGui();}
        if (ID == FIRTS_TIME_LVLUP){return new StatsGuiFirstJoin();}
        if (ID == SADDLE_BAGS) {
            //return new Saddlebags(player, stats.getExtendedInventory());
            return new Saddlebags(player);

        }
        return null;
    }
}
