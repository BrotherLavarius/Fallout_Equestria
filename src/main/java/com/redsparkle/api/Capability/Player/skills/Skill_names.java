package com.redsparkle.api.Capability.Player.skills;

import java.util.stream.Stream;

public enum Skill_names {
    MAGIC("magic"),
    MELEE("melee"),
    FIREARMS("firearms"),
    ENERGY_WEAPONS("energy_weapons"),
    SADDLEBAG_GUNS("saddlebag_guns"),
    EXPLOSIVES("explosives"),
    REPAIR("repair"),
    MEDICINE("medicine"),
    LOCK_PICKING("lock_picking"),
    SCIENCE("science"),
    SNEAK("sneak"),
    BARTER("barter"),
    SURVIVAL("survival");

    String name;

    Skill_names(String name) {
        this.name = name;
    }

    public static String[] names() {
        return Stream.of(Skill_names.values()).map(Skill_names::name).toArray(String[]::new);
    }

    public String getName() {
        return name;
    }
}
