package com.redsparkle.api.items.helpers.guns;

import com.google.gson.JsonObject;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.Skill_names;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.items.guns.entitys.bass.EntityBass;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.UnifiedMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by hoijima on 02.09.17.
 */
@SuppressWarnings("ALL")
public class GunFire {

    static DecimalFormat df = new DecimalFormat("#.0");


    public static void GunFire(World world, EntityPlayer player, String type) {

        if (type.equalsIgnoreCase("gun_main")) {

            Item_Firearm gun = (Item_Firearm) player.getHeldItemMainhand().getItem();

            projectyleType(gun, world, player, type);
        }
        if (type.equalsIgnoreCase("gun_saddlebagLS")) {
            Item_SaggleBagGun gun = (Item_SaggleBagGun) player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem();

            projectyleType(gun, world, player, type);
        }
        if (type.equalsIgnoreCase("gun_saddlebagRS")) {
            Item_SaggleBagGun gun = (Item_SaggleBagGun) player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem();

            projectyleType(gun, world, player, type);
        }
        


    }

    public static List gunStats() {

        List params = new List();


        return params;
    }


    public static void bullet(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, String type, double x, double y, double z, double xHeading, double yHeading, double zHeading, float vel, float inac, boolean remote) {


        if (remote) {
            int damage_firearms = getSkills(playerIn).getAttribute(Skill_names.FIREARMS.getName());
            EntityBullet bullet = new EntityBullet(worldIn, playerIn);

            Float inaccuracy =  params.getInaccuracy() / damage_firearms;
            bullet.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), inaccuracy);
            bullet.setDamage(params.getDamage() + damage_firearms);
            worldIn.spawnEntity(bullet);
            SendRenderMessage(playerIn, params, type, 80);

        }

    }

    public static void pellet(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, String type, double x, double y, double z, double xHeading, double yHeading, double zHeading, float vel, float inac, boolean remote) {
        if (remote) {
            int damage_firearms = getSkills(playerIn).getAttribute(Skill_names.FIREARMS.getName());
            Pellet[] pellets = new Pellet[]{new Pellet_one(worldIn, playerIn), new Pellet_two(worldIn, playerIn), new Pellet_tree(worldIn, playerIn), new Pellet_four(worldIn, playerIn), new Pellet_five(worldIn, playerIn), new Pellet_six(worldIn, playerIn)};
            for (int i = 0; i <= (pellets.length - 1); i++) {
                pellets[i].shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), params.getInaccuracy() / damage_firearms);
                pellets[i].setRenderYawOffset(params.getYawOffset());
                pellets[i].setDamage(params.getDamage() + damage_firearms);
                worldIn.spawnEntity(pellets[i]);

            }
            SendRenderMessage(playerIn, params, type, 81);

        }
    }

    public static void laser(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, String type, boolean plasma, double x, double y, double z, double xHeading, double yHeading, double zHeading, float vel, float inac, boolean remote) {
        int sp_type = 82;
        if (plasma) {
            sp_type = 83;
        }
        if (remote) {
            float damage_magic_modif = getSkills(playerIn).getAttribute(Skill_names.MAGIC.getName());
            float damage_laser_weapons = getSkills(playerIn).getAttribute(Skill_names.ENERGY_WEAPONS.getName());
            EntityLaser laser = new EntityLaser(worldIn, playerIn, plasma);
            laser.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), params.getInaccuracy() / (damage_magic_modif + damage_laser_weapons));
            laser.setRenderYawOffset(params.getYawOffset());
            laser.setDamage(params.getDamage() + damage_laser_weapons + Math.round(damage_magic_modif / 2));
            worldIn.spawnEntity(laser);


            SendRenderMessage(playerIn, params, type, sp_type);

        }
    }

    public static void flame(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, String type, double x, double y, double z, double xHeading, double yHeading, double zHeading, float vel, float inac, boolean remote) {
        if (remote) {
            int damage_firearms = getSkills(playerIn).getAttribute(Skill_names.FIREARMS.getName());
            EntityFlame flame = new EntityFlame(worldIn, playerIn);
            flame.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), params.getInaccuracy() / damage_firearms);
            flame.setRenderYawOffset(params.getYawOffset());
            flame.setDamage(params.getDamage());
            worldIn.spawnEntity(flame);

            SendRenderMessage(playerIn, params, type, 84);

        }

    }

    public static void flare(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, String type, double x, double y, double z, double xHeading, double yHeading, double zHeading, float vel, float inac, boolean remote) {
        if (remote) {
            int damage_firearms = getSkills(playerIn).getAttribute(Skill_names.FIREARMS.getName());
            EntityFlare flare = new EntityFlare(worldIn, playerIn);

            flare.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), params.getInaccuracy() / damage_firearms);
            flare.setRenderYawOffset(params.getYawOffset());
            flare.setDamage(params.getDamage());
            worldIn.spawnEntity(flare);
            SendRenderMessage(playerIn, params, type, 85);

        }

    }


    public static void bass(World worldIn, EntityPlayer playerIn, Item item, GlobalsGunStats params, String type, double x, double y, double z, double xHeading, double yHeading, double zHeading, float vel, float inac, boolean remote) {
        if (remote) {
            int damage_firearms = getSkills(playerIn).getAttribute(Skill_names.FIREARMS.getName());
            EntityBass bass = new EntityBass(worldIn, playerIn);

            bass.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, params.getVelocity(), params.getInaccuracy() / damage_firearms);
            bass.setRenderYawOffset(params.getYawOffset());
            bass.setDamage(params.getDamage());
            worldIn.spawnEntity(bass);
            SendRenderMessage(playerIn, params, type, 86);

        }

    }

    public static void SendSoundMessage(EntityPlayer playerIn, String gunname, double x, double y, double z, String type) {
        JsonObject message, body;
        message = body = new JsonObject();
        message.addProperty("type", "sound");

        if (type.equalsIgnoreCase("gun_main")) {
            body.addProperty("type", "gun|main|" + gunname + "|fire");
            body.addProperty("position", x + "," + y + "," + z);
        }
        if (type.equalsIgnoreCase("gun_saddlebagLS")) {
            body.addProperty("type", "gun|saddlebagLS|" + gunname + "|fire");
            body.addProperty("position", x + "," + y + "," + z);
        }
        if (type.equalsIgnoreCase("gun_saddlebagRS")) {
            body.addProperty("type", "gun|saddlebagRS|" + gunname + "|fire");
            body.addProperty("position", x + "," + y + "," + z);
        }
        message.add("details", body);

        main.simpleNetworkWrapper.sendToAllAround(new UnifiedMessage(message), new NetworkRegistry.TargetPoint(0, x, y, z, 30.0));
        main.simpleNetworkWrapper.sendTo(new UnifiedMessage(message), (EntityPlayerMP) playerIn);
    }

    public static void SendRenderMessage(EntityPlayer playerIn, GlobalsGunStats params, String type, int sp_type) {
        SendSoundMessage(playerIn, params.getGunName(), playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ(), type);

    }

    public static ISkillsCapability getSkills(EntityPlayer playerIn) {
        return playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null);

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
    public static void projectyleType(Item item, World world, EntityPlayer player, String type) {
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
            bullet(world, player, item, params, type, 0D, 0D, 0D, 0D, 0D, 0D, 0F, 0F, true);
        }
        if (projectileType == "pellet") {
            pellet(world, player, item, params, type, 0D, 0D, 0D, 0D, 0D, 0D, 0F, 0F, true);

        }
        if (projectileType == "laser") {
            laser(world, player, item, params, type, false, 0D, 0D, 0D, 0D, 0D, 0D, 0F, 0F, true);
        }
        if (projectileType == "plasma") {
            laser(world, player, item, params, type, true, 0D, 0D, 0D, 0D, 0D, 0D, 0F, 0F, true);
        }
        if (projectileType == "flame") {
            flame(world, player, item, params, type, 0D, 0D, 0D, 0D, 0D, 0D, 0F, 0F, true);
        }
        if (projectileType == "flare") {
            flare(world, player, item, params, type, 0D, 0D, 0D, 0D, 0D, 0D, 0F, 0F, true);
        }
        if (projectileType == "bass") {
            bass(world, player, item, params, type, 0D, 0D, 0D, 0D, 0D, 0D, 0F, 0F, true);
        }
    }


}
