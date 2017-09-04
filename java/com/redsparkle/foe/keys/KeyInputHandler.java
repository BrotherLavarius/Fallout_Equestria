package com.redsparkle.foe.keys;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageAdvInv;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientTrigger_Item;
import com.redsparkle.foe.network.MessageGunFire;
import com.redsparkle.foe.network.MessageGunReload;
import com.redsparkle.foe.network.MessageUpdateSLSClientOnDemand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

/**
 * Created by NENYN on 1/12/2017.
 */
public class KeyInputHandler {
    public boolean activated = false;
    public int count = 0;
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Keyboard.enableRepeatEvents(true);
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;

        if (keyHandler.pipbuck.isPressed()) {
            if (mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(0) != ItemStack.EMPTY) {
                main.simpleNetworkWrapper.sendToServer(new MessageUpdateSLSClientOnDemand());
                player.openGui(main.instance, 0, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);
                activated = true;
            }

        }
        if (keyHandler.testButton.isPressed()) {

        }
        if (keyHandler.saddlebags.isPressed()) {
            main.simpleNetworkWrapper.sendToServer(new MessageAdvInv("sync_and_gui"));

        }


        if (keyHandler.sbag_shooter.isPressed()) {

            if (player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(5).getItem() != Items.AIR
                    && player.getHeldItemMainhand().getItem() == Items.AIR
                    && player.getHeldItemOffhand().getItem() == Items.AIR
                    ) {
                if (mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()) {
                    main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(false));
                } else {
                    main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(true));
                }
            }

        }

        if (Keyboard.getEventKey() == keyHandler.fire_RSB.getKeyCode()
                && !mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && player.getHeldItemMainhand().getItem() instanceof Item_Firearm
                ) {


            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.isRepeatEvent()) {
                        // Key held down
                        if (count == 0) {
                            //                      if (((Item_Firearm) player.getHeldItemMainhand().getItem()).autofireSupport) {

                            main.simpleNetworkWrapper.sendToServer(new MessageGunFire("main_gun_cont"));


                            count = 1;
                            //                      }
                        }
                    } else {
                        // Key pressed
                        main.simpleNetworkWrapper.sendToServer(new MessageGunFire("main_gun_once"));

                    }
                    count = 0;
                    ClientOnlyProxy.FireMessage("STOP");
                }
            }
        }


//        if (Keyboard.getEventKey() == keyHandler.fire_RSB.getKeyCode()
//                && mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
//                && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem() instanceof Item_SaggleBagGun){
//
//                if (Keyboard.getEventKeyState()) {
//                    if (Keyboard.isRepeatEvent()) {
//                        // Key held down
//                        if(((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem()).autofireSupport){
//                            main.simpleNetworkWrapper.sendToServer(new MessageGunFire("saddlebag_RS_cont"));
//                        }
//                    } else {
//                        // Key pressed
//                        main.simpleNetworkWrapper.sendToServer(new MessageGunFire("saddlebag_RS_once"));
//                    }
//                    // Key released
//                }
//        }
//
//        if (Keyboard.getEventKey() == keyHandler.fire_LSB.getKeyCode()
//                && mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
//                && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem() instanceof Item_SaggleBagGun){
//
//            if (Keyboard.getEventKeyState()) {
//                if (Keyboard.isRepeatEvent()) {
//                    // Key held down
//                    if(((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem()).autofireSupport) {
//                        main.simpleNetworkWrapper.sendToServer(new MessageGunFire("saddlebag_LS_cont"));
//                    }
//                } else {
//                    // Key pressed
//                    main.simpleNetworkWrapper.sendToServer(new MessageGunFire("saddlebag_LS_once"));
//                }
//                // Key released
//            }
//        }

        if (keyHandler.reload.isPressed()) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunReload(0));
        }
        if (keyHandler.reloadRSB.isPressed()) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunReload(1));
        }
        if (keyHandler.reloadLSB.isPressed()) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunReload(2));
        }

    }
}
