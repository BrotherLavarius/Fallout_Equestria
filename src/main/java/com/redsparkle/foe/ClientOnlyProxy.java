package com.redsparkle.foe;

import com.google.gson.JsonObject;
import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.rad.IRadiationCapability;
import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.IWaterCapability;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.events.ClientSide.CommonEventHandler;
import com.redsparkle.foe.events.ClientSide.character.EventPlayerRenders;
import com.redsparkle.foe.events.ClientSide.gui.EventHandlerOverlayAEM;
import com.redsparkle.foe.events.ClientSide.gui.EventHandlerOverlayPipBuck;
import com.redsparkle.foe.events.ClientSide.gui.EventPlayerGuiHandler;
import com.redsparkle.foe.keys.KeyInputHandler;
import com.redsparkle.foe.keys.keyHandler;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSPECHIAL;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSkills;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientTrigger_Item;
import com.redsparkle.foe.network.UnifiedMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 14.12.16.
 */
@SuppressWarnings("ALL")
@SideOnly(Side.CLIENT)
public class ClientOnlyProxy extends CommonProxy {
    private final static Minecraft mc = Minecraft.getMinecraft();
    public static IThreadListener mainThread = Minecraft.getMinecraft();
    public static World world = mc.world;

    public static void handleRadMessage(JsonObject message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IRadiationCapability rad = RadsFactoryProvider.instanceFor(player);
            rad.setRadiation(message.get("rads").getAsInt());
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */
        });
    }

    public static void handleSpechialMessage(MessageUpdateClientServerSPECHIAL message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ISpechialCapability spechial = SpechialFactoryProvider.instanceFor(player);
            spechial.setStreinght(message.Streinght);
            spechial.setPerception(message.Perception);
            spechial.setEndurance(message.Endurance);
            spechial.setCharisma(message.Charisma);
            spechial.setIntelligence(message.Intelligence);
            spechial.setAgility(message.Agility);
            spechial.setLuck(message.Luck);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */
        });
    }

    public static void handleSkillsMessage(MessageUpdateClientServerSkills message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ISkillsCapability skills = SkillsFactoryProvider.instanceFor(player);
            skills.setAttribute(message.map);

            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */
        });
    }

    public static void handleLevelMessage(JsonObject message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ILevelCapability level = LevelFactoryProvider.instanceFor(player);
            level.setLevel(message.getAsJsonObject("details").get("lvl").getAsInt());
            level.setProgress(message.getAsJsonObject("details").get("progress").getAsInt());
        });
    }


    public static void FireMessage(String type) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            boolean cont_fire = false;

            EntityPlayer player = mc.player;

            if (type.matches("gun_main|gun_saddlebagRS|gun_saddlebagLS")) {

                JsonObject message = new JsonObject();
                message.addProperty("type", "gun_fire");
                message.addProperty("detail", type.toString());
                main.simpleNetworkWrapper.sendToServer(new UnifiedMessage(message));
            }
        });
    }


    public static void handleLevelMessageOnDemand(JsonObject message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            Integer lvl = message.getAsJsonObject("details").get("lvl").getAsInt();
            Integer progress = message.getAsJsonObject("details").get("progress").getAsInt();
            EntityPlayer player = Minecraft.getMinecraft().player;
            ILevelCapability level = LevelFactoryProvider.instanceFor(player);
            player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).setLevel(lvl);
            player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).setProgress(progress);
            level.setLevel(lvl);
            level.setProgress(progress);
            System.out.println("player progress: " + player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress());
        });
    }

    public static void handleOpenGui(JsonObject message) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;
        mainThread.addScheduledTask(() -> {

            int id = message.getAsJsonObject("details").get("ID").getAsInt();

            player.openGui(main.instance, id, mc.world, (int) player.posX, (int) player.posY, (int) player.posZ);
        });
    }

    public static void handleWaterMessage(JsonObject message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IWaterCapability water = WaterFactoryProvider.instanceFor(player);
            water.setWater(message.get("water").getAsInt());
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */
        });
    }


    public static void handleAdv_SYNC_op(JsonObject message) {
        IThreadListener mainThread = Minecraft.getMinecraft();


        mainThread.addScheduledTask(() -> {

            List<Entity> list = Minecraft.getMinecraft().world.getLoadedEntityList();
            EntityPlayer player = null;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityOtherPlayerMP &&
                        ((EntityPlayer) list.get(i)).getName().equalsIgnoreCase(message.get("player").getAsString())) {
                    player = (EntityPlayer) list.get(i);
                }
            }
            if (player.hasCapability(IAdvProvider.Adv_Inv, null)) {
                IAdvInventory advInventory = IAdvProvider.instanceFor(player);
                JsonSlotProcessor(message, advInventory);
            }
        });

    }

    public static void handleAdv_SYNC(JsonObject message) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;
        mainThread.addScheduledTask(() -> {
            IAdvInventory advInventory = player.getCapability(IAdvProvider.Adv_Inv, null);

            JsonSlotProcessor(message, advInventory);

        });

    }

    public static void handleSync_AmmoItems(JsonObject message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IAdvInventory advInventory = IAdvProvider.instanceFor(player);
            IAmmoInterface AmmoCapa;
            IGunInterface GunCapa;

            JsonObject body = message.getAsJsonObject("details");

            Integer ammo = body.get("ammo").getAsInt();
            Integer maxAmmo = body.get("maxAmmo").getAsInt();
            Integer slot = body.get("slot").getAsInt();
            Integer invType = body.get("invType").getAsInt();
            Integer type = body.get("type").getAsInt();


            if (type == 0) {
                if (invType == 0) {
                    ItemStack stack = player.inventory.getStackInSlot(slot);
                    AmmoCapa = stack.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);
                    AmmoCapa.setMaxAmmo(maxAmmo);
                    AmmoCapa.setAmmo(ammo);
                }
                if (invType == 1) {
                    IAdvInventory stack = player.getCapability(IAdvProvider.Adv_Inv, null);
                    AmmoCapa = stack.getStackInSlot(slot).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);
                    AmmoCapa.setMaxAmmo(maxAmmo);
                    AmmoCapa.setAmmo(ammo);
                }
            }
            if (type == 1) {
                if (invType == 0) {
                    ItemStack stack = player.inventory.getStackInSlot(slot);
                    GunCapa = stack.getCapability(GunFactoryProvider.GUN, null);
                    GunCapa.setMaxAmmo(maxAmmo);
                    GunCapa.setAmmo(ammo);
                }
                if (invType == 1) {
                    IAdvInventory stack = player.getCapability(IAdvProvider.Adv_Inv, null);
                    GunCapa = stack.getStackInSlot(slot).getCapability(GunFactoryProvider.GUN, null);
                    GunCapa.setMaxAmmo(maxAmmo);
                    GunCapa.setAmmo(ammo);
                }
            }
        });
    }

    public static void handleTrigger_Item_Message(MessageUpdateClientTrigger_Item message) {
        mainThread.addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ITrigger_item status = player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null);
            status.setStatus(message.status);
            status.setInteraction(message.interaction_mode);
        });
    }

    public static void MessageClientPlaySound_handler(JsonObject message, MessageContext ctx) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;
        mainThread.addScheduledTask(() -> {


            String whatToPlay = message.getAsJsonObject("details").get("type").getAsString();
            String position = message.getAsJsonObject("details").get("position").getAsString();
            // Types of things vary from sound_env_rads to gun_tenmm_fire
            String[] whatToPlayArray = whatToPlay.split("\\|");
            String[] positionArray = position.split(",");
            GlobalsGunStats gunStats = null;

            if (whatToPlayArray[0].equalsIgnoreCase("gun")) {
                if (whatToPlayArray[1].equalsIgnoreCase("main") || whatToPlayArray[1].equalsIgnoreCase("saddlebagLS") || whatToPlayArray[1].equalsIgnoreCase("saddlebagRS")) {
                    String search = whatToPlayArray[2];
                    gunStats = GlobalsGunStats.lookup.get(search);
                }
                if (whatToPlayArray[3].equalsIgnoreCase("fire")) {
                    player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                            SoundInit.lookup.get(gunStats.getGunName()).get(0), SoundCategory.AMBIENT, 0.2F, 1.0F, true);
                } else if (whatToPlayArray[3].equalsIgnoreCase("dry")) {
                    player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                            SoundInit.lookup.get(gunStats.getGunName()).get(1), SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                } else if (whatToPlayArray[3].equalsIgnoreCase("reload")) {
                    player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                            SoundInit.lookup.get(gunStats.getGunName()).get(2), SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                } else if (whatToPlayArray[3].equalsIgnoreCase("clipout")) {
                    player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                            SoundInit.lookup.get(gunStats.getGunName()).get(3), SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                }


                if (whatToPlayArray[1].equalsIgnoreCase("clipReload")) {
                    player.world.playSound(Double.parseDouble(positionArray[0]), Double.parseDouble(positionArray[1]), Double.parseDouble(positionArray[2]),
                            SoundInit.clip_load, SoundCategory.AMBIENT, 1.0F, 1.0F, true);
                }
            }


        });
    }


    public void preInit() {
        super.preInit();
        keyHandler.register();
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    }

    public void Init() {
        super.init();
    }

    public void postInit() {
        super.postInit();
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlerOverlayPipBuck());
        MinecraftForge.EVENT_BUS.register(new EventHandlerOverlayAEM());
        MinecraftForge.EVENT_BUS.register(new EventPlayerGuiHandler());
        MinecraftForge.EVENT_BUS.register(new EventPlayerRenders());

    }

    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            return entityPlayerMP.interactionManager.isCreative();
        } else if (player instanceof EntityPlayerSP) {
            return Minecraft.getMinecraft().playerController.isInCreativeMode();
        }
        return false;
    }

    @Override
    public boolean isDedicatedServer() {
        return false;
    }


}
