package com.redsparkle.foe;

import com.redsparkle.api.capa.StatsCapa.AddInvCapabilityProvider;
import com.redsparkle.api.capa.StatsCapa.IAddInvCapability;
import com.redsparkle.api.capa.level.ILevelCapability;
import com.redsparkle.api.capa.level.LevelFactoryProvider;
import com.redsparkle.api.capa.skills.ISkillsCapability;
import com.redsparkle.api.capa.skills.SkillsFactoryProvider;
import com.redsparkle.api.capa.spechial.ISpechialCapability;
import com.redsparkle.api.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.api.capa.water.IWaterCapability;
import com.redsparkle.api.capa.water.WaterFactoryProvider;
import com.redsparkle.api.utils.Lvlutil;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.events.ServerSIdeONly.EventHandlerServerSidePre;
import com.redsparkle.foe.items.guns.*;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.MessageFireToClientServer;
import com.redsparkle.foe.network.MessageOpenGuiClient;
import com.redsparkle.foe.network.MessageUpdateSLSServerReplyOnDemand;
import com.redsparkle.foe.network.helpers.gunReload;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;

import static com.redsparkle.api.capa.level.LevelFactoryProvider.LEVEL_CAPABILITY;

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

            ISkillsCapability skills = SkillsFactoryProvider.instanceFor(playerEntity);

            skills.setBigGuns(message.BigGuns);
            skills.setSmallGuns(message.SmallGuns);
            skills.setEnergyWeapons(message.EnergyWeapons);
            skills.setExplosives(message.Explosives);
            skills.setMeleeWeapons(message.MeleeWeapons);
            skills.setUnarmed(message.Unarmed);
            skills.setMedicine(message.Medicine);
            skills.setLockpick(message.Lockpick);
            skills.setRepair(message.Repair);
            skills.setScience(message.Science);
            skills.setSneak(message.Sneak);
            skills.setBarter(message.Barter);





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
        if (heldItem != null) { //&& heldItem.getTagCompound().getBoolean("isgun")
            if (heldItem.getItem() instanceof TenMM) {
                gunReload.TenMM(mainThread, heldItem, player);
            } else if (heldItem.getItem() instanceof LaserPistol) {
                gunReload.LaserPistol(mainThread, heldItem, player);
            } else if (heldItem.getItem() instanceof FourTenMM) {
                gunReload.FourTenMM(mainThread, heldItem, player);
            } else if (heldItem.getItem() instanceof SB_shoutgun){
                gunReload.Shotgun(mainThread, heldItem, player);
            }else if (heldItem.getItem() instanceof FlareGun){
                gunReload.FlareGun(mainThread, heldItem, player);
            }


        }
    }
    public static void handleSLSOnDemand(EntityPlayerMP player) {

            if (player.getCapability(LEVEL_CAPABILITY,null).getProgress() < player.experienceTotal){
                player.getCapability(LEVEL_CAPABILITY,null).setProgress(player.experienceTotal);
            } else
            if (player.getCapability(LEVEL_CAPABILITY,null).getProgress() > player.experienceTotal){
                player.getCapability(LEVEL_CAPABILITY,null).setProgress(
                        player.getCapability(LEVEL_CAPABILITY,null).getProgress() +
                                (player.getCapability(LEVEL_CAPABILITY,null).getProgress() -
                                        player.experienceTotal));
            }


        main.simpleNetworkWrapper.sendTo(new MessageUpdateSLSServerReplyOnDemand(
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getLevel(),
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getProgress()

        ),player);
    }

    public static void handleOpenGuiMessage(MessageOpenGuiClient message, EntityPlayerMP playerMP) {
        //Minecraft.getMinecraft().addScheduledTask(() -> {
            main.simpleNetworkWrapper.sendTo(new MessageOpenGuiClient(message.ID), playerMP);
        //});

    }

    public static void SendOpenGui(int guiId,EntityPlayerMP player){
        main.simpleNetworkWrapper.sendTo(new MessageOpenGuiClient(guiId),player);
    }
    public static void handleSkillsLVLUPMessage(MessageUpdateClientServerSkills message, EntityPlayerMP player) {
            ISkillsCapability skills = SkillsFactoryProvider.instanceFor(player);
            ILevelCapability level = LevelFactoryProvider.instanceFor(player);
            ISpechialCapability spechial = SpechialFactoryProvider.instanceFor(player);
            int summ = (
                    message.BigGuns+
                    message.SmallGuns+
                    message.EnergyWeapons+
                    message.Explosives+
                    message.MeleeWeapons+
                    message.Unarmed+
                    message.Medicine+
                    message.Lockpick+
                    message.Repair+
                    message.Science+
                    message.Sneak+
                    message.Barter
                    )-120;
            int prevSumm= (

                    skills.getBigGuns()+
                    skills.getSmallGuns()+
                    skills.getEnergyWeapons()+
                    skills.getExplosives()+
                    skills.getMeleeWeapons()+
                    skills.getUnarmed()+
                    skills.getMedicine()+
                    skills.getLockpick()+
                    skills.getRepair()+
                    skills.getScience()+
                    skills.getSneak()+
                    skills.getBarter()
                    )-120;
            if (Lvlutil.ponitsAvailable(
            player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getLevel(),
            player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY,null).getProgress()) + prevSumm == summ
                    ){


                skills.setBigGuns(message.BigGuns);
                skills.setSmallGuns(message.SmallGuns);
                skills.setEnergyWeapons(message.EnergyWeapons);
                skills.setExplosives(message.Explosives);
                skills.setMeleeWeapons(message.MeleeWeapons);
                skills.setUnarmed(message.Unarmed);
                skills.setMedicine(message.Medicine);
                skills.setLockpick(message.Lockpick);
                skills.setRepair(message.Repair);
                skills.setScience(message.Science);
                skills.setSneak(message.Sneak);
                skills.setBarter(message.Barter);
                level.setLevel((summ/10));
                skills.updateClient(player);
                level.updateClient(player);

            }
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(
                    20D + (15 * spechial.getStreinght() + (2 * spechial.getEndurance()) + level.getLevel() * (Math.round(spechial.getEndurance() / 2) + 2))
            );

        player.heal(player.getMaxHealth());

    }

    public static void handleFireMessage(MessageFireToClientServer message) {
    }

    public static void handleWaterMessage(MessageUpdateClientWater message, EntityPlayerMP playerMP) {
        IWaterCapability water = WaterFactoryProvider.instanceFor(playerMP);
        water.setWater(message.water);

        /** DEBUG MESSAGE ENABLER
         * System.out.println("Client: "+message.radiation);
         */


    }

    public static void handleAdvInv(MessageAdvInvSync message, EntityPlayerMP playerMP) {
        IAddInvCapability iAdvInv = playerMP.getCapability(AddInvCapabilityProvider.STATS_CAPA,null);
        String[] id = new String[]{
                iAdvInv.getPipBuckSlot().getDisplayName(),
                iAdvInv.getDeviceSlot1().getDisplayName(),
                iAdvInv.getDeviceSlot2().getDisplayName(),
                iAdvInv.getDeviceSlot3().getDisplayName(),
                iAdvInv.getDeviceSlot4().getDisplayName(),
                iAdvInv.getHarnessSlot().getDisplayName(),
                iAdvInv.getGunSlot1().getDisplayName(),
                iAdvInv.getGunSlot2().getDisplayName(),
                iAdvInv.getAmmoSlot1().getDisplayName(),
                iAdvInv.getAmmoSlot2().getDisplayName(),
                iAdvInv.getAmmoSlot3().getDisplayName(),
                iAdvInv.getAmmoSlot4().getDisplayName()
        };
        int[] count = new int[]{
                iAdvInv.getPipBuckSlot().getCount(),
                iAdvInv.getDeviceSlot1().getCount(),
                iAdvInv.getDeviceSlot2().getCount(),
                iAdvInv.getDeviceSlot3().getCount(),
                iAdvInv.getDeviceSlot4().getCount(),
                iAdvInv.getHarnessSlot().getCount(),
                iAdvInv.getGunSlot1().getCount(),
                iAdvInv.getGunSlot2().getCount(),
                iAdvInv.getAmmoSlot1().getCount(),
                iAdvInv.getAmmoSlot2().getCount(),
                iAdvInv.getAmmoSlot3().getCount(),
                iAdvInv.getAmmoSlot4().getCount()
        };
        int[] damage = new int[]{
                iAdvInv.getPipBuckSlot().getItemDamage(),
                iAdvInv.getDeviceSlot1().getItemDamage(),
                iAdvInv.getDeviceSlot2().getItemDamage(),
                iAdvInv.getDeviceSlot3().getItemDamage(),
                iAdvInv.getDeviceSlot4().getItemDamage(),
                iAdvInv.getHarnessSlot().getItemDamage(),
                iAdvInv.getGunSlot1().getItemDamage(),
                iAdvInv.getGunSlot2().getItemDamage(),
                iAdvInv.getAmmoSlot1().getItemDamage(),
                iAdvInv.getAmmoSlot2().getItemDamage(),
                iAdvInv.getAmmoSlot3().getItemDamage(),
                iAdvInv.getAmmoSlot4().getItemDamage()
        };
        main.simpleNetworkWrapper.sendTo(new MessageAdvInvToClientSync(id, count, damage), playerMP);
    }
    @SuppressWarnings("Duplicates")
    public static void handleAdvInvFromCLient(MessageAdvInvToServerSync message, EntityPlayerMP playerMP) {
        IAddInvCapability adv = AddInvCapabilityProvider.instanceFor(playerMP);

        for (int i = 0; i < 12; i++) {
            Item item = null;
            item = item.getByNameOrId(message.item_id[i]);
            ItemStack stack = new ItemStack(item);
            stack.setCount(message.item_count[i]);
            stack.setItemDamage(message.item_damage[i]);

            if (i == 0) {
                adv.setPipBuckSlot(stack);
            }
            if (i == 1) {
                adv.setDeviceSlot1(stack);
            }
            if (i == 2) {
                adv.setDeviceSlot2(stack);
            }
            if (i == 3) {
                adv.setDeviceSlot3(stack);
            }
            if (i == 4) {
                adv.setDeviceSlot4(stack);
            }
            if (i == 5) {
                adv.setHarnessSlot(stack);
            }
            if (i == 6) {
                adv.setGunSlot1(stack);
            }
            if (i == 7) {
                adv.setGunSlot2(stack);
            }
            if (i == 8) {
                adv.setAmmoSlot1(stack);
            }
            if (i == 9) {
                adv.setAmmoSlot2(stack);
            }
            if (i == 10) {
                adv.setAmmoSlot3(stack);
            }
            if (i == 11) {
                adv.setAmmoSlot4(stack);
            }

    }
    }
    /**
     * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
     */
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new EventHandlerServerSidePre());
        super.preInit();


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
