package com.redsparkle.foe.block;

import com.redsparkle.api.block.TileEntityDefault;
import com.redsparkle.api.utils.GlobalNames;

public class LockerTileEntety extends TileEntityDefault {
    private String customInventoryName;

    public void setCustomInventoryName(String customInventoryName) {
        this.customInventoryName = GlobalNames.Locker;
    }
}