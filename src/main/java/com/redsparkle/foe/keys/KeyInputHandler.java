package com.redsparkle.foe.keys;

import com.google.gson.JsonObject;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.api.utils.GunFire_ThreadManager;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientTrigger_Item;
import com.redsparkle.foe.network.UnifiedMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Created by NENYN on 1/12/2017.
 */
public class KeyInputHandler {
    public boolean activated = false;
    public int count = 0;
    public int counter = 0;

    public Thread autofire;
    public boolean rs_pressed = false;
    public boolean ls_pressed = false;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyBoardInput(InputEvent.KeyInputEvent event) {
        Keyboard.enableRepeatEvents(true);
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        ITrigger_item status = player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null);

        if (keyHandler.pipbuck.isPressed()) {
            if (mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(0) != ItemStack.EMPTY) {
                JsonObject message = new JsonObject();
                message.addProperty("type", "lvl_update_request");
                main.simpleNetworkWrapper.sendToServer(new UnifiedMessage(message));
                player.openGui(main.instance, 0, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);
                activated = true;
            }

        }
        if (keyHandler.testButton.isPressed()) {

        }
        if (keyHandler.saddlebags.isPressed()) {
            JsonObject message = new JsonObject();
            message.addProperty("type", "gui_advInv");
            message.addProperty("details", "sync_and_gui");
            main.simpleNetworkWrapper.sendToServer(new UnifiedMessage(message));

        }


        if (keyHandler.sbag_shooter.isPressed()) {

            if (!player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(5).getItem().equals(Items.AIR)
                    && player.getHeldItemMainhand().getItem().equals(Items.AIR)
                    && player.getHeldItemOffhand().getItem().equals(Items.AIR)
                    ) {
                if (mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()) {
                    main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(false, status.getInteraction()));
                } else {
                    main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(true, status.getInteraction()));
                }
            }

        }
        if (keyHandler.interaction_mode.isPressed()) {
            if (status.getInteraction()) {
                main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(status.getStatus(), false));
            } else {
                main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(status.getStatus(), true));
            }
        }


        if (keyHandler.reload.isPressed()) {
            if (player.getHeldItemMainhand().getItem() instanceof Item_Firearm) {
                JsonObject message = new JsonObject();
                message.addProperty("type", "gun_reload");
                message.addProperty("detail", "gun_main");
                main.simpleNetworkWrapper.sendToServer(new UnifiedMessage(message));
            }
        }
        if (keyHandler.reloadLSB.isPressed()) {
            if (player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem() != Items.AIR) {
                JsonObject message = new JsonObject();
                message.addProperty("type", "gun_reload");
                message.addProperty("detail", "gun_saddlebagLS");
                main.simpleNetworkWrapper.sendToServer(new UnifiedMessage(message));
            }
        }
        if (keyHandler.reloadRSB.isPressed()) {
            if (player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem() != Items.AIR) {
                JsonObject message = new JsonObject();
                message.addProperty("type", "gun_reload");
                message.addProperty("detail", "gun_saddlebagRS");
                main.simpleNetworkWrapper.sendToServer(new UnifiedMessage(message));
            }
        }


    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;


        if (Mouse.getEventButtonState()) {
            if (Mouse.getEventButton() == 1 && !ls_pressed) {
                if (!mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                        && player.getHeldItemMainhand().getItem() instanceof Item_Firearm
                        && !mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getInteraction()) {
                    int bps = ((Item_Firearm) mc.player.getHeldItemMainhand().getItem()).params.getBps();
                    if (((Item_Firearm) mc.player.getHeldItemMainhand().getItem()).autofireSupport) {
                        GunFire_ThreadManager.SpawnGunFire("gun_main", bps);
                        rs_pressed = true;
                    } else {
                        ClientOnlyProxy.FireMessage("gun_main");
                        rs_pressed = false;
                    }

                }
            }

        } else {
            if (Mouse.getEventButton() == 1 && !rs_pressed) {
                GunFire_ThreadManager.StopGunFire("gun_main");
                rs_pressed = false;

            }
        }


        if (Mouse.getEventButtonState()) {
            if (Mouse.getEventButton() == 1 && !ls_pressed) {
                if (mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                        && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem() instanceof Item_SaggleBagGun
                        && !mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getInteraction()) {
                    int bps = ((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem()).params.getBps();
                    if (((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem()).autofireSupport) {
                        GunFire_ThreadManager.SpawnGunFire("gun_saddlebagRS", bps);
                        rs_pressed = true;

                    } else {
                        ClientOnlyProxy.FireMessage("gun_saddlebagRS");
                        rs_pressed = false;
                    }
                }
            }
        } else {
            if (Mouse.getEventButton() == 1 && !ls_pressed) {
                GunFire_ThreadManager.StopGunFire("gun_saddlebagRS");
                rs_pressed = false;
            }
        }

        if (Mouse.getEventButtonState()) {
            if (Mouse.getEventButton() == 0 && !rs_pressed) {
                if (mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                        && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem() instanceof Item_SaggleBagGun
                        && !mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getInteraction()) {
                    int bps = ((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem()).params.getBps();
                    if (((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem()).autofireSupport) {
                        GunFire_ThreadManager.SpawnGunFire("gun_saddlebagLS", bps);
                        ls_pressed = true;
                    } else {
                        ClientOnlyProxy.FireMessage("gun_saddlebagLS");
                        ls_pressed = false;
                    }
                }
            }
        } else {
            if (Mouse.getEventButton() == 0 && !rs_pressed) {

                GunFire_ThreadManager.StopGunFire("gun_saddlebagLS");
                ls_pressed = false;
            }
        }

        if (!rs_pressed) {
            GunFire_ThreadManager.StopGunFire("gun_main");
        }


    }
}

