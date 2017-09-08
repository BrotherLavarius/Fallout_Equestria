package com.redsparkle.foe.items.food.fresh;
import net.minecraft.item.ItemFood;
/**
 * Created by hoijima on 09.06.17.
 */
public class Tato extends ItemFood {
    public Tato(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }
    public Tato(int amount, boolean isWolfFood) {
        super(amount, isWolfFood);
    }
}
