package com.redsparkle.api.handlers;

import com.redsparkle.api.capa.StatsCapa.StatsCapabilityProvider;
import com.redsparkle.api.capa.StatsCapa.StatsFactoryStorage;
import com.redsparkle.api.utils.PlayerUtil;
import com.redsparkle.foe.containers.CONTAINER_AdditionalInventory;
import com.redsparkle.foe.gui.Inventory_Crafting.GUI_AdditionalInventory;
import com.redsparkle.foe.gui.Menus.PipBuckGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.DATA.DataGui;
import com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.ITEMS.InventoryGui;
import com.redsparkle.foe.gui.general.LvlUpGui;
import com.redsparkle.foe.gui.general.StatsGuiFirstJoin;
import com.sun.org.glassfish.external.probe.provider.StatsProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 3/15/2017.
 */
public class GuiHandler implements IGuiHandler {
    public static final int PIPBUCK_GUI = 0;
    public static final int LVL_UP_GUI = 1;
    public static final int PIPBUCK_GUI_INVE = 2;
    public static final int PIPBUCK_GUI_DATA = 3;
    public static final int FIRTS_TIME_LVLUP = 4;
    public static final int ADV_Inv = 5;


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        EntityPlayerMP playerBase = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);

        if (playerBase == null) {
            player.sendMessage(new TextComponentString("FOE player instance null server-side. This is a bug."));
            return null;
        }


        if (ID == ADV_Inv) {
            return new CONTAINER_AdditionalInventory(player,player.getCapability(StatsCapabilityProvider.STATS_CAPA,null).getAdditional_Inventory());
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == PIPBUCK_GUI){return new PipBuckGui();}
        if (ID == LVL_UP_GUI){return new LvlUpGui();}
        if (ID == PIPBUCK_GUI_INVE){return new InventoryGui();}
        if (ID == PIPBUCK_GUI_DATA){return new DataGui();}
        if (ID == FIRTS_TIME_LVLUP){return new StatsGuiFirstJoin();}
        if (ID == ADV_Inv) {
            return new GUI_AdditionalInventory(player,player.getCapability(StatsCapabilityProvider.STATS_CAPA,null).getAdditional_Inventory());
        }
        return null;
    }
}
