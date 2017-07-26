package com.redsparkle.foe.items.food.fresh;
import net.minecraft.item.ItemFood;
/**
 * Created by hoijima on 09.06.17.
 */
public class Mutfruit extends ItemFood {
    public Mutfruit(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }
    public Mutfruit(int amount, boolean isWolfFood) {
        super(amount, isWolfFood);
    }
}
