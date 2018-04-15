package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.block.alarm_lamp;
import com.redsparkle.foe.block.alarm_lamp_TE;
import com.redsparkle.foe.block.generator_small;
import com.redsparkle.foe.block.generator_small_TE;

/**
 * Created by hoijima on 18.12.17.
 */
public enum ModBlocks_CStudio {

    ALARM_LAMP(new alarm_lamp("alarm_lamp", "1x1"), "alarm_lamp", alarm_lamp_TE.class),
    GENERATOR_SMALL(new generator_small(GlobalNames.generator_small, "1x1"), GlobalNames.generator_small, generator_small_TE.class);


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
