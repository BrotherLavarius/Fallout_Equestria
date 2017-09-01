package com.redsparkle.api.utils;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.awt.*;

/**
 * Created by hoijima on 21.07.17.
 */
@SuppressWarnings("Duplicates")
public class GunHelpers {

    public static void gunFire(World worldIn, EntityPlayerMP player, int type) {
        String fire_type = GunParams(player, type).getItem(0);
        int damage = Integer.parseInt(GunParams(player, type).getItem(1));


        int damage_firearms = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
        int damage_laser_weapons = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getEnergyWeapons();
        int damage_magic_modif = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMagic();


        if (fire_type == "firearm") { //FIREARM
            EntityBullet bullet = new EntityBullet(worldIn, player);
            bullet.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 4.5F, 1.5F);
            bullet.setRenderYawOffset(5F);
            bullet.setDamage(damage + damage_firearms);
            worldIn.spawnEntity(bullet);
        }
        if (fire_type == "pellet") { //PELLET
            Pellet[] pellets = new Pellet[]{new Pellet_one(worldIn, player), new Pellet_two(worldIn, player), new Pellet_tree(worldIn, player), new Pellet_four(worldIn, player), new Pellet_five(worldIn, player), new Pellet_six(worldIn, player)};
            for (int i = 0; i <= (pellets.length - 1); i++) {
                pellets[i].setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 4.5F, 15.5F);
                pellets[i].setRenderYawOffset(10F);
                pellets[i].setDamage(damage + damage_firearms);
                worldIn.spawnEntity(pellets[i]);
            }
        }
        if (fire_type == "laser") { //LASER
            EntityLaser laser = new EntityLaser(worldIn, player);
            laser.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 4.5F, 0.5F);
            laser.setRenderYawOffset(5F);
            laser.setDamage(damage + damage_laser_weapons + Math.round(damage_magic_modif / 2));
            worldIn.spawnEntity(laser);
        }
        if (fire_type == "flame") { //FLAME
            EntityFlame flame = new EntityFlame(worldIn, player);
            flame.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 0.5F, 3.0F);
            flame.setDamage(damage);
        }
        if (fire_type == "flare") { //FLARE
            EntityFlare flare = new EntityFlare(worldIn, player);
            flare.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1F, 3.0F);
            flare.setDamage(damage);
            worldIn.spawnEntity(flare);
        }

    }


    //   params(side,damage,type)
    public static List GunParams(EntityPlayer player, int type) {
        String side = "";
        ItemStack handStack = player.inventory.getCurrentItem();
        List params = new List();

        if (type <= 9) {
            Item gun = handStack.getItem();
            if (gun instanceof Item_Firearm) {
                if (gun == ItemInit.tenMM) {
                    params.add(String.valueOf(GlobalsGunStats.TEN_MM.getDamage()));
                    params.add("firearm");
                }
                if (gun == ItemInit.laserPistol) {
                    params.add(String.valueOf(GlobalsGunStats.LASER_PISTOL.getDamage()));
                    params.add("laser");
                }
                if (gun == ItemInit.fourTenMM) {
                    params.add(String.valueOf(GlobalsGunStats.FOUR_TEN_MM.getDamage()));
                    params.add("firearm");
                }
                if (gun == ItemInit.sb_shoutgun) {
                    params.add(String.valueOf(GlobalsGunStats.DB_SHOUTGUN.getDamage()));
                    params.add("pellet");
                }
                if (gun == ItemInit.flareGun) {
                    params.add(String.valueOf(GlobalsGunStats.FLARE_GUN.getDamage()));
                    params.add("flare");
                }

            }
        }


        if (type >= 10) {
            if (type >= 10 && type <= 19) {
                side = "LS";

            } else if (type == 20 && type <= 29) {
                side = "RS";
            }
            Item saddle_gun = Items.AIR;
            if (side == "LS") {
                saddle_gun = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem();
            } else if (side == "RS") {
                saddle_gun = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem();
            }

            if (saddle_gun instanceof Item_SaggleBagGun) {
                if (saddle_gun == ItemInit.seven_mm_rifle_LS || saddle_gun == ItemInit.seven_mm_rifle_RS) {
                    params.add(String.valueOf(GlobalsGunStats.SEVEN_MM_RIFLE.getDamage()));
                    params.add("firearm");
                }
            }
        }


        return params;
    }

}
