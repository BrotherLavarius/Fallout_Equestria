package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.*;

/**
 * Created by hoijima on 18.12.17.
 */
public enum ModBlocks {

    DESKTOP_TERMINAL(new desktop_terminal(GlobalNames.desktop_terminal, "1x1"), GlobalNames.desktop_terminal, desktop_terminal_TE.class),
    SPARKLE_COLA_MACHINE(new sparkle_cola_machine(GlobalNames.sparkle_cola_machine, "2x2"), GlobalNames.sparkle_cola_machine, sparkle_cola_machine_TE.class),
    WORKBENCH(new workbench(GlobalNames.workbench, "2x1"), GlobalNames.workbench, workbench_TE.class),
    LOCKER(new locker(GlobalNames.locker, "1x2"), GlobalNames.locker, locker_TE.class),
    WB_HM(new workbench_handmade(GlobalNames.workbench_handmade, "2x1"), GlobalNames.workbench_handmade, workbench_handmade_TE.class),
    WB_SW(new workbench_sawing(GlobalNames.workbench_sawing, "2x1"), GlobalNames.workbench_sawing, workbench_sawing_TE.class),
    SAFE(new safe(GlobalNames.safe, "1x1"), GlobalNames.safe, safe_TE.class);


    public Object BLOCK;
    public String NAME;
    public Class TILEENTITY;

    ModBlocks(Object object, String name, Class tileentity) {
        this.BLOCK = object;
        this.NAME = name;
        this.TILEENTITY = tileentity;
    }

    public Object getBLOCK() {
        return BLOCK;
    }

    public String getNAME() {
        return NAME;
    }

    public Class getTILEENTITY() {
        return TILEENTITY;
    }
}
