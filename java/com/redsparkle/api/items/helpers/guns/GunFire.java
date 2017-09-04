package com.redsparkle.api.items.helpers.guns;

import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.awt.*;

/**
 * Created by hoijima on 02.09.17.
 */
@SuppressWarnings("ALL")
public class GunFire {


    public static void GunFire(World world, EntityPlayer player, int type) {

        if (type == 0 && type == 1) {

            Item_Firearm gun = (Item_Firearm) player.getHeldItemMainhand().getItem();

            projectyleType(gun, world, player);
        }
        if (type >= 10 & type <= 29) {


            if (type == 10 & type == 11) {

            }

            if (type == 20 && type == 21) {

            }
        }
    }

    public static List gunStats() {

        List params = new List();


        return params;
    }


    public static void bullet(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params) {
        int damage_firearms = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
        EntityBullet bullet = new EntityBullet(worldIn, playerIn);
        bullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 1.5F);
        bullet.setRenderYawOffset(params.getYawOffset());
        bullet.setDamage(params.getDamage() + damage_firearms);
        worldIn.spawnEntity(bullet);
        if (!worldIn.isRemote) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunFire(params.getProjectileType()));
        }
    }

    public static void pellet(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params) {
        int damage_firearms = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
        Pellet[] pellets = new Pellet[]{new Pellet_one(worldIn, playerIn), new Pellet_two(worldIn, playerIn), new Pellet_tree(worldIn, playerIn), new Pellet_four(worldIn, playerIn), new Pellet_five(worldIn, playerIn), new Pellet_six(worldIn, playerIn)};
        for (int i = 0; i <= (pellets.length - 1); i++) {
            pellets[i].setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 15.5F);
            pellets[i].setRenderYawOffset(params.getYawOffset());
            pellets[i].setDamage(params.getDamage() + damage_firearms);
            worldIn.spawnEntity(pellets[i]);
        }
        if (!worldIn.isRemote) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunFire(params.getProjectileType()));
        }
    }

    public static void laser(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params) {
        int damage_magic_modif = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMagic();
        int damage_laser_weapons = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getEnergyWeapons();
        EntityLaser laser = new EntityLaser(worldIn, playerIn);
        laser.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 0.5F);
        laser.setRenderYawOffset(params.getYawOffset());
        laser.setDamage(params.getDamage() + damage_laser_weapons + Math.round(damage_magic_modif / 2));
        worldIn.spawnEntity(laser);
        if (!worldIn.isRemote) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunFire(params.getProjectileType()));
        }
    }

    public static void flame(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params) {
        EntityFlame flame = new EntityFlame(worldIn, playerIn);
        flame.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 3.0F);
        flame.setDamage(params.getDamage());
        if (!worldIn.isRemote) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunFire(params.getProjectileType()));
        }
    }

    public static void flare(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params) {
        EntityFlare flare = new EntityFlare(worldIn, playerIn);
        flare.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 3.0F);
        flare.setRenderYawOffset(params.getYawOffset());
        flare.setDamage(params.getDamage());
        worldIn.spawnEntity(flare);
        if (!worldIn.isRemote) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunFire(params.getProjectileType()));
        }
    }


    /**
     * int Damage;
     * int Clipsize;
     * Item ClipItem;
     * Item AmmoItem;
     * float velocity;
     * float YawOffset;
     * boolean autofireSupport;
     * String ProjectileType;
     **/
    public static void projectyleType(Item item, World world, EntityPlayer player) {
        String projectileType = "";
        GlobalsGunStats params = null;
        if (item instanceof Item_Firearm) {
            projectileType = ((Item_Firearm) item).ProjectileType;
            params = ((Item_Firearm) item).params;
        }
        if (item instanceof Item_SaggleBagGun) {
            projectileType = ((Item_SaggleBagGun) item).ProjectileType;
            params = ((Item_SaggleBagGun) item).params;

        }
        if (projectileType == "bullet") {
            bullet(world, player, item, params);
        }
        if (projectileType == "pellet") {
            pellet(world, player, item, params);

        }
        if (projectileType == "laser") {
            laser(world, player, item, params);
        }
        if (projectileType == "flame") {
            flame(world, player, item, params);
        }
        if (projectileType == "flare") {
            flare(world, player, item, params);
        }
    }


}
