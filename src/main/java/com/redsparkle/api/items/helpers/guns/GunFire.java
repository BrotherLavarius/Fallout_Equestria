package com.redsparkle.api.items.helpers.guns;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
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
import com.redsparkle.foe.network.MessageClientPlaySound;
import com.redsparkle.foe.network.MessageGunFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.awt.*;

/**
 * Created by hoijima on 02.09.17.
 */
@SuppressWarnings("ALL")
public class GunFire {


    public static void GunFire_clienHandler(World worldln, EntityPlayer player,int type,double x,double y,double z,double xHeading,double yHeading,double zHeading ,float vel,float inac){

        if (type == 80) {
            bullet(worldln,player,null,null,0,x,y,z,xHeading,yHeading,zHeading,vel,inac,false);
        }
        if (type == 81) {

        }
        if (type == 82) {

        }
        if (type == 83) {

        }
        if (type == 84) {

        }


    }

    public static void GunFire(World world, EntityPlayer player, int type){

        if (type == 0) {

            Item_Firearm gun = (Item_Firearm) player.getHeldItemMainhand().getItem();

            projectyleType(gun, world, player, type);
        }
        if (type >= 10 && type <= 29) {


            if (type == 10) {
                Item_SaggleBagGun gun = (Item_SaggleBagGun) player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem();

                projectyleType(gun, world, player, type);
            }

            if (type == 20) {
                Item_SaggleBagGun gun = (Item_SaggleBagGun) player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem();

                projectyleType(gun, world, player, type);
            }
        }


    }

    public static List gunStats() {

        List params = new List();


        return params;
    }


    public static void bullet(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, int type,double x,double y,double z,double xHeading,double yHeading,double zHeading ,float vel,float inac,boolean remote) {


        if (remote) {
            int damage_firearms = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
            EntityBullet bullet = new EntityBullet(worldIn, playerIn);
            bullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 1.5F);
            bullet.setDamage(params.getDamage() + damage_firearms);
            worldIn.spawnEntity(bullet);
            double xPl = playerIn.getPosition().getX();
            double yPl = playerIn.getPosition().getY();
            double zPl = playerIn.getPosition().getZ();
            xHeading=playerIn.getLookVec().x;
            yHeading=playerIn.getLookVec().y;
            zHeading=playerIn.getLookVec().z;
            SendSoundMessage(playerIn, xPl, yPl, zPl, type);
            main.simpleNetworkWrapper.sendToAll(new MessageGunFire(80,xPl,yPl,zPl,xHeading,yHeading,zHeading,params.getVelocity(),1.5F));
        }
        else {
//            EntityBullet bullet = new EntityBullet(worldIn,x,y+playerIn.getEyeHeight(),z);
//            bullet.setThrowableHeading(xHeading,yHeading,zHeading,vel,inac);
//            worldIn.spawnEntity(bullet);
        }
    }

    public static void pellet(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, int type) {
        int damage_firearms = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
        Pellet[] pellets = new Pellet[]{new Pellet_one(worldIn, playerIn), new Pellet_two(worldIn, playerIn), new Pellet_tree(worldIn, playerIn), new Pellet_four(worldIn, playerIn), new Pellet_five(worldIn, playerIn), new Pellet_six(worldIn, playerIn)};
        for (int i = 0; i <= (pellets.length - 1); i++) {
            pellets[i].setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 15.5F);
            pellets[i].setRenderYawOffset(params.getYawOffset());
            pellets[i].setDamage(params.getDamage() + damage_firearms);
            worldIn.spawnEntity(pellets[i]);
        }
        int x = playerIn.getPosition().getX();
        int y = playerIn.getPosition().getY();
        int z = playerIn.getPosition().getZ();
        if (!worldIn.isRemote & Side.SERVER.isServer()) {
            SendSoundMessage(playerIn, x, y, z, type);

            main.simpleNetworkWrapper.sendToAll(new MessageGunFire(incode_type(type)));
        }
    }

    public static void laser(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, int type, boolean plasma) {
        int damage_magic_modif = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMagic();
        int damage_laser_weapons = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getEnergyWeapons();
        EntityLaser laser = new EntityLaser(worldIn, playerIn, plasma);
        laser.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 0.5F);
        laser.setRenderYawOffset(params.getYawOffset());
        laser.setDamage(params.getDamage() + damage_laser_weapons + Math.round(damage_magic_modif / 2));
        worldIn.spawnEntity(laser);
        int x = playerIn.getPosition().getX();
        int y = playerIn.getPosition().getY();
        int z = playerIn.getPosition().getZ();
        if (!worldIn.isRemote & Side.SERVER.isServer()) {
            SendSoundMessage(playerIn, x, y, z, type);

            main.simpleNetworkWrapper.sendToAll(new MessageGunFire(incode_type(type)));
        }
    }

    public static void flame(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, int type) {
        EntityFlame flame = new EntityFlame(worldIn, playerIn);
        flame.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 3.0F);
        flame.setDamage(params.getDamage());
        int x = playerIn.getPosition().getX();
        int y = playerIn.getPosition().getY();
        int z = playerIn.getPosition().getZ();
        if (!worldIn.isRemote & Side.SERVER.isServer()) {
            SendSoundMessage(playerIn, x, y, z, type);

            main.simpleNetworkWrapper.sendToAll(new MessageGunFire(incode_type(type)));
        }
    }

    public static void flare(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, int type) {
        EntityFlare flare = new EntityFlare(worldIn, playerIn);
        flare.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), 3.0F);
        flare.setRenderYawOffset(params.getYawOffset());
        flare.setDamage(params.getDamage());
        worldIn.spawnEntity(flare);
        int x = playerIn.getPosition().getX();
        int y = playerIn.getPosition().getY();
        int z = playerIn.getPosition().getZ();
        if (!worldIn.isRemote & Side.SERVER.isServer()) {
            SendSoundMessage(playerIn, x, y, z, type);

            main.simpleNetworkWrapper.sendToAllAround(new MessageGunFire(incode_type(type)),new NetworkRegistry.TargetPoint(0, (double) x, (double) y,(double) z,  20.0));
        }
    }

    public static void SendSoundMessage(EntityPlayer playerIn, double x, double y, double z, int type) {
        if (type == 0) {
            main.simpleNetworkWrapper.sendToAllAround(new MessageClientPlaySound("gun_main_fire", x + "," + y + "," + z), new NetworkRegistry.TargetPoint(0,  x,  y,  z, 10.0));
            main.simpleNetworkWrapper.sendTo(new MessageClientPlaySound("gun_main_fire", x + "," + y + "," + z), (EntityPlayerMP) playerIn);
        }
        if (type == 10) {
            main.simpleNetworkWrapper.sendToAllAround(new MessageClientPlaySound("gun_saddlebagLS_fire", x + "," + y + "," + z), new NetworkRegistry.TargetPoint(0,  x,  y, z, 10.0));
            main.simpleNetworkWrapper.sendTo(new MessageClientPlaySound("gun_saddlebagLS_fire", x + "," + y + "," + z), (EntityPlayerMP) playerIn);
        }
        if (type == 20) {
            main.simpleNetworkWrapper.sendToAllAround(new MessageClientPlaySound("gun_saddlebagRS_fire", x + "," + y + "," + z), new NetworkRegistry.TargetPoint(0, x,  y,  z, 10.0));
            main.simpleNetworkWrapper.sendTo(new MessageClientPlaySound("gun_saddlebagRS_fire", x + "," + y + "," + z), (EntityPlayerMP) playerIn);
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
    public static void projectyleType(Item item, World world, EntityPlayer player, int type) {
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
        if (projectileType == "firearm") {
            bullet(world, player, item, params, type,0D,0D,0D,0D,0D,0D,0F,0F,true);
        }
        if (projectileType == "pellet") {
            pellet(world, player, item, params, type);

        }
        if (projectileType == "laser") {
            laser(world, player, item, params, type, false);
        }
        if (projectileType == "plasma") {
            laser(world, player, item, params, type, true);
        }
        if (projectileType == "flame") {
            flame(world, player, item, params, type);
        }
        if (projectileType == "flare") {
            flare(world, player, item, params, type);
        }
    }

    public static String incode_type(int type) {
        if (type == 0) {
            return "gun_main";
        }

        if (type == 10) {
            return "gun_saddlebagLS";
        }

        if (type == 20) {
            return "gun_saddlebagRS";
        }


        return "could not process :(";
    }

}
