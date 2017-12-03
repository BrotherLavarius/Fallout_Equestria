package com.redsparkle.foe;

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
import com.redsparkle.api.items.helpers.guns.GunFire;
import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.events.ClientSide.CommonEventHandler;
import com.redsparkle.foe.events.ClientSide.character.EventPlayerRenders;
import com.redsparkle.foe.events.ClientSide.gui.EventHandlerOverlayAEM;
import com.redsparkle.foe.events.ClientSide.gui.EventHandlerOverlayPipBuck;
import com.redsparkle.foe.events.ClientSide.gui.EventPlayerGuiHandler;
import com.redsparkle.foe.keys.KeyInputHandler;
import com.redsparkle.foe.keys.keyHandler;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.MessageClientPlaySound;
import com.redsparkle.foe.network.MessageGunFire;
import com.redsparkle.foe.network.MessageUpdateSLSServerReplyOnDemand;
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

    public static void handleRadMessage(MessageUpdateClientRads message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IRadiationCapability rad = RadsFactoryProvider.instanceFor(player);
            rad.setRadiation(message.radiation);
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
        });
    }

    public static void handleLevelMessage(MessageUpdateClientServerLevel message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ILevelCapability level = LevelFactoryProvider.instanceFor(player);
            level.setLevel(message.Level);
            level.setProgress(message.Progress);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */
        });
    }


    public static void FireMessage(String type) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            boolean cont_fire = false;

            EntityPlayer player = mc.player;

            if (type.equalsIgnoreCase("gun_main")) {
                main.simpleNetworkWrapper.sendToServer(new MessageGunFire("gun_main"));
            }
            if (type.equalsIgnoreCase("gun_saddlebagRS")) {
                main.simpleNetworkWrapper.sendToServer(new MessageGunFire("gun_saddlebagRS"));
            }
            if (type.equalsIgnoreCase("gun_saddlebagLS")) {
                main.simpleNetworkWrapper.sendToServer(new MessageGunFire("gun_saddlebagLS"));
            }

        });
    }


    public static void handleLevelMessageOnDemand(MessageUpdateSLSServerReplyOnDemand message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ILevelCapability level = LevelFactoryProvider.instanceFor(player);
            player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).setLevel(message.Level);
            player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).setProgress(message.Progress);
            level.setLevel(message.Level);
            level.setProgress(message.Progress);
            System.out.println("player progress: " + player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress());
        });
    }

    public static void handleOpenGui(MessageOpenGuiClient message) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;
        mainThread.addScheduledTask(() -> {
            player.openGui(main.instance, message.ID, mc.world, (int) player.posX, (int) player.posY, (int) player.posZ);
        });
    }

    public static void handleWaterMessage(MessageUpdateClientWater message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IWaterCapability water = WaterFactoryProvider.instanceFor(player);
            water.setWater(message.water);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */
        });
    }



    public static void handleAdv_SYNC_op(MessageAdvInv_SYNC_op message) {
        IThreadListener mainThread = Minecraft.getMinecraft();

        mainThread.addScheduledTask(() -> {

            List<Entity> list = Minecraft.getMinecraft().world.getLoadedEntityList();
            EntityPlayer player = null;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityOtherPlayerMP) {
                    player = (EntityPlayer) list.get(i);
                    if (player.getGameProfile().getName() == message.playerName) {


                        System.out.println(player);
                    }
                }
            }
            if (player.hasCapability(IAdvProvider.Adv_Inv, null)) {
                IAdvInventory advInventory = IAdvProvider.instanceFor(player);

                slotProcessor(message.item_id, message.item_count, message.item_damage, advInventory);
            } else {

            }
        });

    }

    public static void handleAdv_SYNC(MessageAdvInv_SYNC message) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;
        mainThread.addScheduledTask(() -> {
            IAdvInventory advInventory = player.getCapability(IAdvProvider.Adv_Inv, null);
            slotProcessor(message.item_id, message.item_count, message.item_damage, advInventory);
        });

    }

    public static void handleSync_AmmoItems(MessageUpdateAmmoHolders message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IAdvInventory advInventory = IAdvProvider.instanceFor(player);
            IAmmoInterface AmmoCapa;
            IGunInterface GunCapa;
            if (message.type == 0) {
                if (message.invType == 0) {
                    ItemStack stack = player.inventory.getStackInSlot(message.slot);
                    AmmoCapa = stack.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);
                    AmmoCapa.setMaxAmmo(message.maxAmmo);
                    AmmoCapa.setAmmo(message.ammo);
                }
                if (message.invType == 1) {
                    IAdvInventory stack = player.getCapability(IAdvProvider.Adv_Inv, null);
                    AmmoCapa = stack.getStackInSlot(message.slot).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);
                    AmmoCapa.setMaxAmmo(message.maxAmmo);
                    AmmoCapa.setAmmo(message.ammo);
                }
            }
            if (message.type == 1) {
                if (message.invType == 0) {
                    ItemStack stack = player.inventory.getStackInSlot(message.slot);
                    GunCapa = stack.getCapability(GunFactoryProvider.GUN, null);
                    GunCapa.setMaxAmmo(message.maxAmmo);
                    GunCapa.setAmmo(message.ammo);
                }
                if (message.invType == 1) {
                    IAdvInventory stack = player.getCapability(IAdvProvider.Adv_Inv, null);
                    GunCapa = stack.getStackInSlot(message.slot).getCapability(GunFactoryProvider.GUN, null);
                    GunCapa.setMaxAmmo(message.maxAmmo);
                    GunCapa.setAmmo(message.ammo);
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

    public static void MessageClientPlaySound_handler(MessageClientPlaySound message, MessageContext ctx) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;
        mainThread.addScheduledTask(() -> {

            String whatToPlay = message.type;
            String position = message.position;
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


    public static void MessageGunFire_hadnler(MessageGunFire message, MessageContext ctx) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;
        mainThread.addScheduledTask(() -> {
            GunFire.GunFire_clienHandler(player.world, player, message.type, message.x, message.y, message.z, message.xHeading, message.yHeading, message.zHeading, message.vel, message.inac);
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
