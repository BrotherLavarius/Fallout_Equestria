package com.redsparkle.foe.handlers;

import com.redsparkle.foe.gui.PipBuckGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 3/15/2017.
 */
public class GuiHandler implements IGuiHandler {
    public static final int PIPBUCK_GUI = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == PIPBUCK_GUI)
            return new PipBuckGui();
        return null;
    }
}
