package com.redsparkle.foe;


import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.rad.IRadiationCapability;
import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.IWaterCapability;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import com.redsparkle.api.utils.ItemCatalog;
import com.redsparkle.foe.Init.ClientOnlyStartup;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.keys.KeyInputHandler;
import com.redsparkle.foe.keys.keyHandler;
import com.redsparkle.foe.network.ClientServerOneClass.*;
import com.redsparkle.foe.network.MessageGunReloadReply;
import com.redsparkle.foe.network.MessageUpdateSLSServerReplyOnDemand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by hoijima on 14.12.16.
 */
@SuppressWarnings("ALL")
public class ClientOnlyProxy extends CommonProxy {

    public static Minecraft mc = Minecraft.getMinecraft();
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

    public static void handleGundMessageReload(MessageGunReloadReply message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            System.out.println("Sound int: " + message.soundname);
            EntityPlayer player = Minecraft.getMinecraft().player;
            World world = Minecraft.getMinecraft().world;
//-------------------------------------------------------------
            if (message.soundname == 0) {
                //10 mm reload
                world.playSound(player, player.getPosition(), SoundInit.tenmm_reload, SoundCategory.HOSTILE, 1.0F, 1.0F);
            } else if (message.soundname == 1) {
                //10 mm clip_out
                world.playSound(player, player.getPosition(), SoundInit.tenmm_clip_out, SoundCategory.HOSTILE, 1.0F, 1.0F);
            }
//-------------------------------------------------------------
            else if (message.soundname == 2) {
                // 14mm reload
                world.playSound(player, player.getPosition(), SoundInit.four_tenmm_reload, SoundCategory.HOSTILE, 1.0F, 1.0F);

            } else if (message.soundname == 3) {
                // 14mm clip_out
                world.playSound(player, player.getPosition(), SoundInit.four_tenmm_clip_out, SoundCategory.HOSTILE, 1.0F, 1.0F);

            }
//-------------------------------------------------------------
            else if (message.soundname == 4) {
                // db_shotgun reload
                world.playSound(player, player.getPosition(), SoundInit.db_shotgun_reload, SoundCategory.HOSTILE, 1.0F, 1.0F);

            } else if (message.soundname == 5) {
                // db_shotgun clip_out
                world.playSound(player, player.getPosition(), SoundInit.db_shotgun_clip_out, SoundCategory.HOSTILE, 1.0F, 1.0F);

            }
//-------------------------------------------------------------
            else if (message.soundname == 6) {
                // flaregun reload
                world.playSound(player, player.getPosition(), SoundInit.flaregun_reload, SoundCategory.HOSTILE, 1.0F, 1.0F);

            } else if (message.soundname == 7) {
                // flaregun clip_out
                world.playSound(player, player.getPosition(), SoundInit.flaregun_clip_out, SoundCategory.HOSTILE, 1.0F, 1.0F);

            }
//-------------------------------------------------------------
            else if (message.soundname == 8) {
                // laser reload
                world.playSound(player, player.getPosition(), SoundInit.laser_reload, SoundCategory.HOSTILE, 1.0F, 1.0F);

            } else if (message.soundname == 9) {
                // laser clip_out
                world.playSound(player, player.getPosition(), SoundInit.laser_clip_out, SoundCategory.HOSTILE, 1.0F, 1.0F);

            }
//-------------------------------------------------------------
            else if (message.soundname == 10) {
                // plasma reload
                world.playSound(player, player.getPosition(), SoundInit.plasma_reload, SoundCategory.HOSTILE, 1.0F, 1.0F);

            } else if (message.soundname == 11) {
                // plasma clip_out
                world.playSound(player, player.getPosition(), SoundInit.plasma_clip_out, SoundCategory.HOSTILE, 1.0F, 1.0F);

            }
//-------------------------------------------------------------
            else if (message.soundname == 12) {
                // flamer reload
                world.playSound(player, player.getPosition(), SoundInit.flamer_reload, SoundCategory.HOSTILE, 1.0F, 1.0F);

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
        Minecraft.getMinecraft().addScheduledTask(() -> {
            mc.player.openGui(main.instance, message.ID, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);
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


    public static void handleAdv_SYNC(MessageAdvInv_SYNC message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IAdvInventory advInventory = IAdvProvider.instanceFor(player);
            for (int i = 0; i < 12; i++) {
                Item item = Item.getByNameOrId(message.item_id.get(i));
                ItemStack stack = ItemCatalog.RequestStack(item, message.item_count.get(i), message.item_damage.get(i));
                if (advInventory.getStackInSlot(i) == ItemStack.EMPTY && stack != ItemStack.EMPTY) {
                    advInventory.insertItem(i, stack, false);
                } else if (advInventory.getStackInSlot(i) != ItemStack.EMPTY && stack != ItemStack.EMPTY) {
                    advInventory.extractItem(i, advInventory.getStackInSlot(i).getCount(), false);
                    advInventory.insertItem(i, stack, false);
                }
            }
        });
    }


    public void preInit() {
        super.preInit();
        SoundInit.registerSounds();

        ClientOnlyStartup.preInitClientOnly();
        keyHandler.register();
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());


    }

    public void Init() {
        super.init();
        ClientOnlyStartup.initClientOnly();
    }

    public void postInit() {
        super.postInit();
        ClientOnlyStartup.postInitClientOnly();
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


