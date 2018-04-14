package com.redsparkle.foe.Init;

import com.redsparkle.foe.block.alarm_lamp;
import com.redsparkle.foe.block.alarm_lamp_TE;

/**
 * Created by hoijima on 18.12.17.
 */
public enum ModBlocks_CStudio {

    ALARM_LAMP(new alarm_lamp("alarm_lamp", "1x1"), "alarm_lamp", alarm_lamp_TE.class);


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
