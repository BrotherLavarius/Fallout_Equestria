package com.redsparkle.foe.block.interractable.TileEntitys;
import com.redsparkle.api.block.TileEntityDefault;
import com.redsparkle.api.utils.GlobalNames;
/**
 * Created by hoijima on 09.07.16.
 */
public class DesktopTerminalTileEntity extends TileEntityDefault {
    private String customName;
    public void setCustomName(String name) {
        this.customName = GlobalNames.Terminal;
    }
    @Override
    public String getName() {
        return customName;
    }
    public void setCustomInventoryName(String displayName) {
        String displayName1 = GlobalNames.Terminal;
    }
}
