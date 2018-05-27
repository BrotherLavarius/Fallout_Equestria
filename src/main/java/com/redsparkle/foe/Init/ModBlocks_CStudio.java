package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.*;

/**
 * Created by hoijima on 18.12.17.
 */
public enum ModBlocks_CStudio {

    ALARM_LAMP(new alarm_lamp("alarm_lamp", "1x1"), "alarm_lamp", alarm_lamp_TE.class),
    GENERATOR_SMALL(new generator_small(GlobalNames.generator_small, "1x1"), GlobalNames.generator_small, generator_small_TE.class),
    ENERGY_ROUTER(new power_router(GlobalNames.power_router, "1x1"), GlobalNames.power_router, power_router_TE.class);

    public Object BLOCK;
    public String NAME;
    public Class TE_CLASS;

    ModBlocks_CStudio(Object object, String name, Class clas) {
        this.BLOCK = object;
        this.NAME = name;
        this.TE_CLASS = clas;
    }

    public Object getBLOCK() {
        return BLOCK;
    }

    public String getNAME() {
        return NAME;
    }

    public Class getTE_CLASS() {
        return TE_CLASS;
    }
}
