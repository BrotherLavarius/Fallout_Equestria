package com.redsparkle.foe.block.containers.TileEntitys;
import com.redsparkle.api.block.TileEntityDefault;
import com.redsparkle.api.utils.GlobalNames;
/**
 * Created by hoijima on 09.07.16.
 */
public class SparkleColaMachineTileEntity extends TileEntityDefault {
    private String customInventoryName;
    public void setCustomInventoryName(String customInventoryName) {
        this.customInventoryName = GlobalNames.SPCmachine;
    }
}
