package com.redsparkle.api.utils;

import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 21.07.17.
 */
public class GunHelpers {
    public static int getGunDamage(EntityPlayer player){
        int damage = 0;
        ItemStack handStack = player.inventory.getCurrentItem();
        Item gun = handStack.getItem();
        if(gun instanceof Item_Firearm){

            if(gun == GlobalItemArray_For_init.AllInit[21]){
                damage = GlobalWeaponsStats.TenMMDamage;
            }
            if(gun == GlobalItemArray_For_init.AllInit[22]){
                damage = GlobalWeaponsStats.LaserDamage;
            }

            if(gun == GlobalItemArray_For_init.AllInit[23]){
                damage = GlobalWeaponsStats.FourDamage;
            }
            if(gun == GlobalItemArray_For_init.AllInit[24]){
                damage = GlobalWeaponsStats.db_shoutgunDamage;
            }
            if (gun == GlobalItemArray_For_init.AllInit[27]) {
                damage = GlobalWeaponsStats.flaregun_Damage;
            }

        }


        return damage;

    }
    public static int getGunDamageMP(EntityPlayerMP player){
        int damage = 0;
        ItemStack handStack = player.inventory.getCurrentItem();
        Item gun = handStack.getItem();
        if(gun instanceof Item_Firearm){

            if(gun == GlobalItemArray_For_init.AllInit[21]){
                damage = GlobalWeaponsStats.TenMMDamage;
            }
            if(gun == GlobalItemArray_For_init.AllInit[22]){
                damage = GlobalWeaponsStats.LaserDamage;
            }

            if(gun == GlobalItemArray_For_init.AllInit[23]){
                damage = GlobalWeaponsStats.FourDamage;
            }
            if(gun == GlobalItemArray_For_init.AllInit[24]){
                damage = GlobalWeaponsStats.db_shoutgunDamage;
            }
            if (gun == GlobalItemArray_For_init.AllInit[27]) {
                damage = GlobalWeaponsStats.flaregun_Damage;
            }

        }


        return damage;

    }
}

