package com.redsparkle.foe;

import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.IWaterCapability;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import com.redsparkle.api.utils.ItemCatalog;
import com.redsparkle.api.utils.Lvlutil;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.events.ServerSIdeONly.EventHandlerServerSidePre;
import com.redsparkle.foe.items.guns.*;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.MessageGunFire;
import com.redsparkle.foe.network.MessageUpdateSLSServerReplyOnDemand;
import com.redsparkle.foe.network.helpers.gunReload;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;

import static com.redsparkle.api.Capability.Player.level.LevelFactoryProvider.LEVEL_CAPABILITY;
import static com.redsparkle.api.utils.GunHelpers.getGunDamageMP;

/**
 * DedicatedServerProxy is used to set up the mod and start it running on dedicated servers.  It contains all the code that should run on the
 * dedicated servers.  This is almost never required.
 * For more background information see here http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
 */
public class DedicatedServerProxy extends CommonProxy {


    public static void handleSpechialMessage(MessageUpdateClientServerSPECHIAL message, EntityPlayerMP playerEntity) {
        ISpechialCapability spechial = SpechialFactoryProvider.instanceFor(playerEntity);
        spechial.setStreinght(message.Streinght);
        spechial.setPerception(message.Perception);
        spechial.setEndurance(message.Endurance);
        spechial.setCharisma(message.Charisma);
        spechial.setIntelligence(message.Intelligence);
        spechial.setAgility(message.Agility);
        spechial.setLuck(message.Luck);

        PlayerParamsSetup.normalizer(playerEntity);

        main.simpleNetworkWrapper.sendTo(new MessageUpdateClientServerSPECHIAL(spechial), playerEntity);

        /** DEBUG MESSAGE ENABLER
         * System.out.println("Client: "+message.radiation);
         */

    }

    public static void handleSkillsMessage(MessageUpdateClientServerSkills message, EntityPlayerMP playerEntity) {
        /**
         Magic
         Melee_Weapons
         Firearms
         EneryWeapons
         Saddlebag_Guns
         Explosives
         Repair
         Medicine
         Lockpicking
         Science
         Sneak
         Barter
         Survival
         */
        ISkillsCapability skills = SkillsFactoryProvider.instanceFor(playerEntity);

        skills.setMagic(message.skills.get(0));
        skills.setMelee(message.skills.get(1));
        skills.setFirearms(message.skills.get(2));
        skills.setEnergyWeapons(message.skills.get(3));
        skills.setSaddlebag_guns(message.skills.get(4));
        skills.setExplosives(message.skills.get(5));
        skills.setRepair(message.skills.get(6));
        skills.setMedicine(message.skills.get(7));
        skills.setLockpick(message.skills.get(8));
        skills.setScience(message.skills.get(9));
        skills.setSneak(message.skills.get(10));
        skills.setBarter(message.skills.get(11));
        skills.setSurvival(message.skills.get(12));





        /** DEBUG MESSAGE ENABLER
         * System.out.println("Client: "+message.radiation);
         */

    }

    public static void handleLevelMessage(MessageUpdateClientServerLevel message, EntityPlayerMP playerEntity) {
        ILevelCapability level = LevelFactoryProvider.instanceFor(playerEntity);
        level.setLevel(message.Level);
        level.setProgress(message.Progress);

        /** DEBUG MESSAGE ENABLER
         * System.out.println("Client: "+message.radiation);
         */


    }

    public static void handleReloadMessage(EntityPlayerMP player) {
        WorldServer mainThread = (WorldServer) (player.world);
        ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
        if (heldItem != null) {
            if (heldItem.getItem() instanceof TenMM || heldItem.getItem() instanceof FourTenMM || heldItem.getItem() instanceof LaserPistol) {
                gunReload.ClipLoaded(mainThread, heldItem, player);
            } else if (heldItem.getItem() instanceof SB_shoutgun || heldItem.getItem() instanceof FlareGun) {
                gunReload.BulletLoaded(mainThread, heldItem, player);
            }


        }
    }

    public static void handleSLSOnDemand(EntityPlayerMP player) {

        if (player.getCapability(LEVEL_CAPABILITY, null).getProgress() < player.experienceTotal) {
            player.getCapability(LEVEL_CAPABILITY, null).setProgress(player.experienceTotal);
        } else if (player.getCapability(LEVEL_CAPABILITY, null).getProgress() > player.experienceTotal) {
            player.getCapability(LEVEL_CAPABILITY, null).setProgress(
                    player.getCapability(LEVEL_CAPABILITY, null).getProgress() +
                            (player.getCapability(LEVEL_CAPABILITY, null).getProgress() -
                                    player.experienceTotal));
        }


        main.simpleNetworkWrapper.sendTo(new MessageUpdateSLSServerReplyOnDemand(
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel(),
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress()

        ), player);
    }

    public static void handleOpenGuiMessage(MessageOpenGuiClient message, EntityPlayerMP playerMP) {
        //Minecraft.getMinecraft().addScheduledTask(() -> {
        main.simpleNetworkWrapper.sendTo(new MessageOpenGuiClient(message.ID), playerMP);
        //});

    }

    public static void SendOpenGui(int guiId, EntityPlayerMP player) {
        main.simpleNetworkWrapper.sendTo(new MessageOpenGuiClient(guiId), player);
    }

    public static void handleSkillsLVLUPMessage(MessageUpdateClientServerSkills message, EntityPlayerMP player) {
        ISkillsCapability skills = SkillsFactoryProvider.instanceFor(player);
        ILevelCapability level = LevelFactoryProvider.instanceFor(player);
        ISpechialCapability spechial = SpechialFactoryProvider.instanceFor(player);
        int summ = (
                message.skills.get(0) +
                        message.skills.get(1) +
                        message.skills.get(2) +
                        message.skills.get(3) +
                        message.skills.get(4) +
                        message.skills.get(5) +
                        message.skills.get(6) +
                        message.skills.get(7) +
                        message.skills.get(8) +
                        message.skills.get(9) +
                        message.skills.get(10) +
                        message.skills.get(11) +
                        message.skills.get(12)
        ) - 120;
        int prevSumm = (
                        skills.getMagic() +
                        skills.getMelee() +
                        skills.getFirearms() +
                        skills.getEnergyWeapons() +
                        skills.getSaddlebag_guns() +
                        skills.getExplosives() +
                        skills.getRepair() +
                        skills.getMedicine() +
                        skills.getLockpick() +
                        skills.getScience() +
                        skills.getSneak() +
                        skills.getBarter() +
                        skills.getSurvival()
        ) - 120;
        if (Lvlutil.ponitsAvailable(
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel(),
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress()) + prevSumm == summ
                ) {


            skills.setMagic(message.skills.get(0));
            skills.setMelee(message.skills.get(1));
            skills.setFirearms(message.skills.get(2));
            skills.setEnergyWeapons(message.skills.get(3));
            skills.setSaddlebag_guns(message.skills.get(4));
            skills.setExplosives(message.skills.get(5));
            skills.setRepair(message.skills.get(6));
            skills.setMedicine(message.skills.get(7));
            skills.setLockpick(message.skills.get(8));
            skills.setScience(message.skills.get(9));
            skills.setSneak(message.skills.get(10));
            skills.setBarter(message.skills.get(11));
            skills.setSurvival(message.skills.get(12));
            level.setLevel((summ / 10));
            skills.updateClient(player);
            level.updateClient(player);

        }
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(
                20D + (15 * spechial.getStreinght() + (2 * spechial.getEndurance()) + level.getLevel() * (Math.round(spechial.getEndurance() / 2) + 2))
        );

        player.heal(player.getMaxHealth());

    }

    public static void handleFireMessage(MessageGunFire message, EntityPlayerMP player) {
        World worldIn = player.world;
        EntityPlayerMP playerIn = player;

        int damage_firearms = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null).getFirearms();
        int damage_laser_weapons = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null).getEnergyWeapons();
        int damage_magic_modif = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null).getMagic();



        if(message.type == 0){ //FIREARM
            EntityBullet bullet = new EntityBullet(worldIn, player);
            bullet.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 4.5F, 1.5F);
            bullet.setRenderYawOffset(5F);
            bullet.setDamage(getGunDamageMP(player) + damage_firearms);
            worldIn.spawnEntity(bullet);
        }

        if(message.type == 1){ //PELLET
            Pellet[] pellets = new Pellet[]{new Pellet_one(worldIn, playerIn), new Pellet_two(worldIn, playerIn), new Pellet_tree(worldIn, playerIn), new Pellet_four(worldIn, playerIn), new Pellet_five(worldIn, playerIn), new Pellet_six(worldIn, playerIn)};
            for (int i = 0; i <= (pellets.length - 1); i++) {
                pellets[i].setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 15.5F);
                pellets[i].setRenderYawOffset(10F);
                pellets[i].setDamage(getGunDamageMP(player) + damage_firearms);
                worldIn.spawnEntity(pellets[i]);
            }
        }
        if(message.type == 2){ //LASER
            EntityLaser laser = new EntityLaser(worldIn, playerIn);
            laser.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 0.5F);
            laser.setRenderYawOffset(5F);
            laser.setDamage(getGunDamageMP(player) + damage_laser_weapons + Math.round(damage_magic_modif/2));
            worldIn.spawnEntity(laser);
        }
        if(message.type == 3){ //FLAME
            EntityFlame flame = new EntityFlame(worldIn, playerIn);
            flame.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 3.0F);
            flame.setDamage(getGunDamageMP(player));
        }
        if(message.type == 4){ //FLARE
            EntityFlare flare = new EntityFlare(worldIn, playerIn);
            flare.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1F, 3.0F);
            flare.setDamage(getGunDamageMP(player));
            worldIn.spawnEntity(flare);
        }

    }


    public static void handleWaterMessage(MessageUpdateClientWater message, EntityPlayerMP playerMP) {
        IWaterCapability water = WaterFactoryProvider.instanceFor(playerMP);
        water.setWater(message.water);

        /** DEBUG MESSAGE ENABLER
         * System.out.println("Client: "+message.radiation);
         */


    }

    public static void handleAdv(MessageAdvInv message, EntityPlayerMP playerMP) {
        IAdvInventory advInventory = IAdvProvider.instanceFor(playerMP);

        if (message.type == 0) {
            advInventory.updateClient(playerMP);
        }
        if (message.type == 1) {
            advInventory.updateClient(playerMP);
            playerMP.openGui(main.instance, 5, playerMP.world, (int) playerMP.posX, (int) playerMP.posY, (int) playerMP.posZ);
            //playerMP.openGui(main.instance, 5, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);

        }
        if (message.type == 2) {

            playerMP.closeContainer();

        }
    }


    public static void handleAdv_SYNC(MessageAdvInv_SYNC message, EntityPlayerMP playerMP) {
        IAdvInventory advInventory = IAdvProvider.instanceFor(playerMP);
        for (int i = 0; i < 12; i++) {
            Item item = Item.getByNameOrId(message.item_id.get(i));//           ItemCatalog.Request(message.item_id.get(slot));
            ItemStack stack = ItemCatalog.RequestStack(item, message.item_count.get(i), message.item_damage.get(i));
            if (advInventory.getStackInSlot(i) == ItemStack.EMPTY && stack != ItemStack.EMPTY) {
                advInventory.insertItem(i, stack, false);
            } else if (advInventory.getStackInSlot(i) != ItemStack.EMPTY && stack != ItemStack.EMPTY) {
                advInventory.extractItem(i, advInventory.getStackInSlot(i).getCount(), false);
                advInventory.insertItem(i, stack, false);
            }
        }

    }

    public static void handleAdv_SLOT(MessageAdvInv_SLOT message, EntityPlayerMP playerMP) {
        IAdvInventory advInventory = IAdvProvider.instanceFor(playerMP);
        int slot = message.slot;
        Item item = Item.getByNameOrId(message.item_id.get(slot));//           ItemCatalog.Request(message.item_id.get(slot));
        ItemStack stack = ItemCatalog.RequestStack(item, message.item_count.get(slot), message.item_damage.get(slot));
        if (advInventory.getStackInSlot(slot) == ItemStack.EMPTY && stack != ItemStack.EMPTY) {
            advInventory.insertItem(slot, stack, false);
        } else if (advInventory.getStackInSlot(slot) != ItemStack.EMPTY && stack != ItemStack.EMPTY) {
            advInventory.extractItem(slot, advInventory.getStackInSlot(slot).getCount(), false);
            advInventory.insertItem(slot, stack, false);
        }
    }
    /**
     * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
     */
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new EventHandlerServerSidePre());
        super.preInit();


    }

    public void processer() {
    }

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes,
     * send FMLInterModComms messages to other mods.
     */
    public void init() {
        super.init();
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    public void postInit() {
        super.postInit();
    }

    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            return entityPlayerMP.interactionManager.isCreative();
        }
        return false;
    }

    @Override
    public boolean isDedicatedServer() {
        return true;
    }


}
