package com.redsparkle.foe.block.interractable.TileEntitys;

import com.redsparkle.foe.block.TileEntityDefault;
import com.redsparkle.foe.utils.GlobalNames;

/**
 * Created by hoijima on 09.07.16.
 */
public class TileEntity_workbench_handmade extends TileEntityDefault {

    private String customName;

    public void setCustomName(String name) {

        this.customName = GlobalNames.Workbench_handmade;
    }


    @Override
    public String getName() {
        return customName;
    }

    public void setCustomInventoryName(String displayName) {
        String displayName1 = GlobalNames.Workbench_handmade;
    }
}
