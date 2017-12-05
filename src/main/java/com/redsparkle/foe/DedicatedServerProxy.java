package com.redsparkle.foe;

import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.IWaterCapability;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import com.redsparkle.api.items.helpers.guns.GunFire;
import com.redsparkle.api.items.helpers.guns.Reload;
import com.redsparkle.api.utils.ItemCatalog;
import com.redsparkle.api.utils.Lvlutil;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.events.ServerSIdeONly.EventHandlerServerSidePre;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.MessageGunFire;
import com.redsparkle.foe.network.MessageGunReload;
import com.redsparkle.foe.network.MessageUpdateSLSServerReplyOnDemand;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static com.redsparkle.api.Capability.Player.level.LevelFactoryProvider.LEVEL_CAPABILITY;

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

    }

    public static void handleLevelMessage(MessageUpdateClientServerLevel message, EntityPlayerMP playerEntity) {
        ILevelCapability level = LevelFactoryProvider.instanceFor(playerEntity);
        level.setLevel(message.Level);
        level.setProgress(message.Progress);

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

    public static void handleOpenGuiMessage(MessageOpenGuiClient message, MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        EntityPlayer player = ctx.getServerHandler().player;
        mainThread.addScheduledTask(() -> {
            main.simpleNetworkWrapper.sendTo(new MessageOpenGuiClient(message.ID), (EntityPlayerMP) player);
        });
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


    public static void handleWaterMessage(MessageUpdateClientWater message, EntityPlayerMP playerMP) {
        IWaterCapability water = WaterFactoryProvider.instanceFor(playerMP);
        water.setWater(message.water);

    }

    public static void handleAdv(MessageAdvInv message, EntityPlayerMP playerMP) {
        IAdvInventory advInventory = IAdvProvider.instanceFor(playerMP);
        if (message.type == 0) {
            main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC(advInventory), playerMP);
        }
        if (message.type == 1) {
            main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC(advInventory), playerMP);
            playerMP.openGui(main.instance, 5, playerMP.world, (int) playerMP.posX, (int) playerMP.posY, (int) playerMP.posZ);
        }
        if (message.type == 2) {
            playerMP.closeContainer();
        }
    }


    public static void handleAdv_SLOT(MessageAdvInv_SLOT message, EntityPlayerMP playerMP) {
        IAdvInventory advInventory = IAdvProvider.instanceFor(playerMP);
        int slot = message.slot;
        Item item = Item.getByNameOrId(message.item_id.get(slot));//           ItemCatalog.Request(message.item_id.get(slot));
        ItemStack stack = ItemCatalog.RequestStack(item, message.item_count.get(slot), message.item_damage.get(slot));
        slotProcessor_sub(advInventory, slot, stack);
    }

    public static void handleTrigger_Item_Message(MessageUpdateClientTrigger_Item message, EntityPlayerMP player) {
        ITrigger_item status = player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null);
            status.setStatus(message.status);
            status.setInteraction(message.interaction_mode);
        main.simpleNetworkWrapper.sendTo(new MessageUpdateClientTrigger_Item(status), player);
    }

    public static void MessageGunFire_handler(MessageGunFire message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        mainThread.addScheduledTask(() -> {

            if (message.type == 0) {
                if (!player.isCreative() && player.getHeldItemMainhand().getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0) {
                    player.getHeldItemMainhand().getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                    player.getHeldItemMainhand().setItemDamage(player.getHeldItemMainhand().getItemDamage() + 1);
                    GunFire.GunFire(player.world, player, message.type);
                } else if (player.isCreative()) {
                    GunFire.GunFire(player.world, player, message.type);
                }

            }

            if (message.type >= 10 & message.type <= 29) {


                if (message.type == 10) {
                    if (!player.isCreative() && player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0) {
                        player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                        player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).setItemDamage(player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItemDamage() + 1);
                        GunFire.GunFire(player.world, player, message.type);
                    } else if (player.isCreative()) {
                        GunFire.GunFire(player.world, player, message.type);
                    }
                }

                if (message.type == 20) {
                    if (!player.isCreative() && player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0) {
                        player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                        player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).setItemDamage(player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItemDamage() + 1);
                        GunFire.GunFire(player.world, player, message.type);
                    } else if (player.isCreative()) {
                        GunFire.GunFire(player.world, player, message.type);
                    }
                }
            }
        });
    }

    public static void MessageGunReload_handler(MessageGunReload message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        mainThread.addScheduledTask(() -> {

            Reload.reload_processor(player, message.type);
        });
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

    public static void handleAdv_SYNC(MessageAdvInv_SYNC message, MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        EntityPlayer player = ctx.getServerHandler().player;
        mainThread.addScheduledTask(() -> {

            IAdvInventory advInventory = player.getCapability(IAdvProvider.Adv_Inv, null);
            slotProcessor(message.item_id, message.item_count, message.item_damage, advInventory);
            main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC(advInventory), (EntityPlayerMP) player);
        });

    }
}
