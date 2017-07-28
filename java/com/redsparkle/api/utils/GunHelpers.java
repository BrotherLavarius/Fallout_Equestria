package com.redsparkle.api.utils;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
/**
 * Created by hoijima on 21.07.17.
 */
@SuppressWarnings("Duplicates")
public class GunHelpers {
    public static int getGunDamage(EntityPlayer player){
        int damage = 0;
        ItemStack handStack = player.inventory.getCurrentItem();
        Item gun = handStack.getItem();
        if(gun instanceof Item_Firearm){
            if(gun == GlobalItemArray_For_init.tenMM){
                damage = GlobalsGunStats.TEN_MM.getDamage();
            }
            if(gun == GlobalItemArray_For_init.laserPistol){
                damage = GlobalsGunStats.LASER_PISTOL.getDamage();
            }
            if(gun == GlobalItemArray_For_init.fourTenMM){
                damage = GlobalsGunStats.FOUR_TEN_MM.getDamage();
            }
            if(gun == GlobalItemArray_For_init.sb_shoutgun){
                damage = GlobalsGunStats.DB_SHOUTGUN.getDamage();
            }
            if (gun == GlobalItemArray_For_init.fourTenMM) {
                damage = GlobalsGunStats.FOUR_TEN_MM.getDamage();
            }
        }
        return damage;
    }
    public static int getGunDamageMP(EntityPlayerMP player){
        int damage = 0;
        ItemStack handStack = player.inventory.getCurrentItem();
        Item gun = handStack.getItem();
        if(gun instanceof Item_Firearm){
            if(gun == GlobalItemArray_For_init.tenMM){
                damage = GlobalsGunStats.TEN_MM.getDamage();
            }
            if(gun == GlobalItemArray_For_init.laserPistol){
                damage = GlobalsGunStats.LASER_PISTOL.getDamage();
            }
            if(gun == GlobalItemArray_For_init.fourTenMM){
                damage = GlobalsGunStats.FOUR_TEN_MM.getDamage();
            }
            if(gun == GlobalItemArray_For_init.sb_shoutgun){
                damage = GlobalsGunStats.DB_SHOUTGUN.getDamage();
            }
            if (gun == GlobalItemArray_For_init.fourTenMM) {
                damage = GlobalsGunStats.FOUR_TEN_MM.getDamage();
            }
        }
        return damage;
    }
}
