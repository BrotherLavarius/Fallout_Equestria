package com.redsparkle.foe;

import com.google.gson.JsonObject;
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
import com.redsparkle.api.utils.Lvlutil;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.events.ServerSIdeONly.EventHandlerServerSidePre;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.UnifiedMessage;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
        ISkillsCapability skills = SkillsFactoryProvider.instanceFor(playerEntity);
        skills.setAttribute(message.map);

    }

    public static void handleLevelMessage(MessageUpdateClientServerLevel message, EntityPlayerMP playerEntity) {
        ILevelCapability level = LevelFactoryProvider.instanceFor(playerEntity);
        level.setLevel(message.Level);
        level.setProgress(message.Progress);

    }


    public static void handleSLSOnDemand(MessageContext ctx) {
        final EntityPlayerMP player = ctx.getServerHandler().player;
        if (player.getCapability(LEVEL_CAPABILITY, null).getProgress() < player.experienceTotal) {
            player.getCapability(LEVEL_CAPABILITY, null).setProgress(player.experienceTotal);
        } else if (player.getCapability(LEVEL_CAPABILITY, null).getProgress() > player.experienceTotal) {
            player.getCapability(LEVEL_CAPABILITY, null).setProgress(
                    player.getCapability(LEVEL_CAPABILITY, null).getProgress() +
                            (player.getCapability(LEVEL_CAPABILITY, null).getProgress() -
                                    player.experienceTotal));
        }

        JsonObject message = new JsonObject();
        JsonObject body = new JsonObject();
        message.addProperty("type", "lvl_update_reply");

        body.addProperty("lvl", player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel());
        body.addProperty("progress", player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress());
        message.add("details", body);

        main.simpleNetworkWrapper.sendTo(new UnifiedMessage(message), player);
    }

    public static void handleOpenGuiMessage(JsonObject message, MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        EntityPlayer player = ctx.getServerHandler().player;

        mainThread.addScheduledTask(() -> {

            JsonObject newMessage = new JsonObject();
            JsonObject body = new JsonObject();
            newMessage.addProperty("type", "gui");
            body.addProperty("ID", message.getAsJsonObject("details").get("ID").getAsInt());

            main.simpleNetworkWrapper.sendTo(new UnifiedMessage(newMessage), (EntityPlayerMP) player);
        });
    }


    public static void handleSkillsLVLUPMessage(MessageUpdateClientServerSkills message, EntityPlayerMP player) {
        ISkillsCapability skills = SkillsFactoryProvider.instanceFor(player);
        ILevelCapability level = LevelFactoryProvider.instanceFor(player);
        ISpechialCapability spechial = SpechialFactoryProvider.instanceFor(player);
        int summ = 0;
        int prevSumm = 0;

        for (int f : message.map.values()) {
            summ += f;
        }
        summ = summ - 120;
        for (int f : skills.getFullMap().values()) {
            prevSumm += f;
        }
        prevSumm = prevSumm - 120;
        if (Lvlutil.ponitsAvailable(
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel(),
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress()) + prevSumm == summ
                ) {
            skills.setAttribute(message.map);
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

    public static void handleAdv(JsonObject message, MessageContext ctx) {
        EntityPlayerMP playerMP = ctx.getServerHandler().player;
        IAdvInventory advInventory = IAdvProvider.instanceFor(playerMP);
        String type = message.get("details").getAsString();
        if (type.equalsIgnoreCase("sync")) {

            JsonObject newMessage = new JsonObject();
            JsonObject body = new JsonObject();

            newMessage.addProperty("type", "sync_adv_inv");

            for (int i = 0; i < advInventory.getSlots(); i++) {
                JsonObject slot = new JsonObject();
                slot.addProperty("name", advInventory.getStackInSlot(i).getItem().delegate.name().toString());
                slot.addProperty("count", advInventory.getStackInSlot(i).getCount());
                slot.addProperty("damage", advInventory.getStackInSlot(i).getItemDamage());
                body.add("slot_" + i, slot);
            }
            newMessage.add("details", body);

            System.out.println(newMessage.toString());

            main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC(advInventory), playerMP);
        }
        if (type.equalsIgnoreCase("sync_and_gui")) {

            JsonObject newMessage = new JsonObject();
            JsonObject body = new JsonObject();

            newMessage.addProperty("type", "sync_adv_inv");

            for (int i = 0; i < advInventory.getSlots(); i++) {
                JsonObject slot = new JsonObject();
                slot.addProperty("name", advInventory.getStackInSlot(i).getItem().delegate.name().toString());
                slot.addProperty("count", advInventory.getStackInSlot(i).getCount());
                slot.addProperty("damage", advInventory.getStackInSlot(i).getItemDamage());
                body.add("slot_" + i, slot);
            }
            newMessage.add("details", body);

            System.out.println(newMessage.toString());

            main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC(advInventory), playerMP);

            playerMP.openGui(main.instance, 5, playerMP.world, (int) playerMP.posX, (int) playerMP.posY, (int) playerMP.posZ);
        }
        if (type.equalsIgnoreCase("close")) {
            playerMP.closeContainer();
        }
    }


    public static void handleTrigger_Item_Message(MessageUpdateClientTrigger_Item message, EntityPlayerMP player) {
        ITrigger_item status = player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null);
            status.setStatus(message.status);
            status.setInteraction(message.interaction_mode);
        main.simpleNetworkWrapper.sendTo(new MessageUpdateClientTrigger_Item(status), player);
    }

    public static void MessageGunFire_handler(JsonObject message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        mainThread.addScheduledTask(() -> {
            String gunType = message.get("detail").getAsString();
            if (gunType.equalsIgnoreCase("gun_main")) {
                if (!player.isCreative() && player.getHeldItemMainhand().getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0) {
                    player.getHeldItemMainhand().getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                    player.getHeldItemMainhand().setItemDamage(player.getHeldItemMainhand().getItemDamage() + 1);
                    GunFire.GunFire(player.world, player, gunType);
                } else if (player.isCreative()) {
                    GunFire.GunFire(player.world, player, gunType);
                }

            }
            if (gunType.equalsIgnoreCase("gun_saddlebagLS")) {

                if (!player.isCreative() && player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0) {
                    player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                    player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).setItemDamage(player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItemDamage() + 1);
                    GunFire.GunFire(player.world, player, gunType);
                } else if (player.isCreative()) {
                    GunFire.GunFire(player.world, player, gunType);
                }
            }

            if (gunType.equalsIgnoreCase("gun_saddlebagRS")) {
                if (!player.isCreative() && player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getCapability(GunFactoryProvider.GUN, null).getAmmo() > 0) {
                    player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getCapability(GunFactoryProvider.GUN, null).removeAmmo(1);
                    player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).setItemDamage(player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItemDamage() + 1);
                    GunFire.GunFire(player.world, player, gunType);
                } else if (player.isCreative()) {
                    GunFire.GunFire(player.world, player, gunType);
                }
            }

        });
    }

    public static void MessageGunReload_handler(JsonObject message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        mainThread.addScheduledTask(() -> {

            Reload.reload_processor(player, message.get("detail").getAsString());
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
        EntityPlayerMP player = ctx.getServerHandler().player;
        mainThread.addScheduledTask(() -> {

            IAdvInventory adv = player.getCapability(IAdvProvider.Adv_Inv, null);
            slotProcessor(message.item_id, message.item_count, message.item_damage, adv);

            JsonObject newMessage = new JsonObject();
            JsonObject body = new JsonObject();

            newMessage.addProperty("type", "sync_adv_inv");

            for (int i = 0; i < adv.getSlots(); i++) {
                JsonObject slot = new JsonObject();
                slot.addProperty("name", adv.getStackInSlot(i).getItem().delegate.name().toString());
                slot.addProperty("count", adv.getStackInSlot(i).getCount());
                slot.addProperty("damage", adv.getStackInSlot(i).getItemDamage());
                body.add("slot_" + i, slot);
            }
            newMessage.add("details", body);

            System.out.println(newMessage.toString());

            main.simpleNetworkWrapper.sendTo(new UnifiedMessage(newMessage), player);
            main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC(adv), player);
        });

    }
}
