package com.redsparkle.api.utils;

import com.google.common.collect.ImmutableSet;
import com.redsparkle.foe.main;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Created by hoijima desu on 06.08.16 desu.
 */
public class Constants {
    public static final int HOTBAR_SIZE = 9;
    public static final int ARMOR_SIZE = 4;
    public static final int ROWS_VANILLA = 3;
    public static final int COLS_VANILLA = 9;
    public static final int V_INVO_SIZE = ROWS_VANILLA * COLS_VANILLA; // 36
    public static final int Slot_pipb = 140;
    public static final int Slot_dev1 = 141;
    public static final int Slot_dev2 = 142;
    public static final int Slot_dev3 = 143;
    public static final int Slot_dev4 = 144;
    public static final int Slot_harn = 145;
    public static final int Slot_gun1 = 146;
    public static final int Slot_gun2 = 147;
    public static final int Slot_amm1 = 148;
    public static final int Slot_amm2 = 149;
    public static final int Slot_amm3 = 150;
    public static final int Slot_amm4 = 151;
    public static final String RESOURCE_PREFIX = main.MODID + ":";
    /**
     * The armour equipment slots.
     */
    public static final Set<EntityEquipmentSlot> ARMOUR_SLOTS = ImmutableSet.copyOf(
            Stream.of(EntityEquipmentSlot.values())
                    .filter(equipmentSlot -> equipmentSlot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
                    .collect(Collectors.toList())
    );
    /**
     * {@link AttributeModifier} operation 0.
     * <p>
     * Add the modifier's amount to the attribute's amount.
     */
    public static final int ATTRIBUTE_MODIFIER_OPERATION_ADD = 0;
    /**
     * {@link AttributeModifier} operation 1.
     * <p>
     * Add the result of operation 0 multiplied by the modifier's amount to the attribute's amount.
     */
    public static final int ATTRIBUTE_MODIFIER_OPERATION_MULTIPLY_OLD_AMOUNT = 1;
    /**
     * {@link AttributeModifier} operation 2.
     * <p>
     * Multiply the attribute's amount by 1.0 + the modifier's amount.
     */
    public static final int ATTRIBUTE_MODIFIER_OPERATION_MULTIPLY_NEW_AMOUNT = 2;
    public static int[] slots = new int[]{
            140,
            141,
            142,
            143,
            144,
            145,
            146,
            147,
            148,
            149,
            150,
            151
    };
}