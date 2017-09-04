package com.redsparkle.foe.keys;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
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
/**
 * Created by NENYN on 1/12/2017.
 */
public class KeyInputHandler {
    public boolean activated = false;
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
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
                    && player.getHeldItemMainhand() == ItemStack.EMPTY
                    && player.getHeldItemOffhand() == ItemStack.EMPTY) {
                if (mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()) {
                    main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(false));
                } else {
                    main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(true));
                }
            }

        }
        boolean triggered = false;
        if (keyHandler.shootLSB.isKeyDown()
                && mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem() instanceof Item_SaggleBagGun) {
            triggered = true;

            while (triggered) {
                main.simpleNetworkWrapper.sendToServer(new MessageGunFire("saddlebag_LS_once"));
            }
        }
        if (keyHandler.shootLSB.isKeyDown()
                && mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem() instanceof Item_SaggleBagGun
                && ((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem()).autofireSupport) {
            triggered = true;

            while (triggered) {
                main.simpleNetworkWrapper.sendToServer(new MessageGunFire("saddlebag_LS_cont"));
            }
        }

        if (keyHandler.shootRSB.isKeyDown()
                && mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem() instanceof Item_SaggleBagGun) {
            triggered = true;

            while (triggered) {
                main.simpleNetworkWrapper.sendToServer(new MessageGunFire("saddlebag_RS"));
            }

        }
        if (keyHandler.shootRSB.isKeyDown()
                && mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem() instanceof Item_SaggleBagGun
                && ((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem()).autofireSupport) {
            triggered = true;

            while (triggered) {
                main.simpleNetworkWrapper.sendToServer(new MessageGunFire("saddlebag_RS_cont"));
            }
        }


        if (keyHandler.shootRSB.isPressed()
                && !mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && player.getHeldItemMainhand().getItem() instanceof Item_Firearm
                && player.getHeldItemOffhand() == ItemStack.EMPTY) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunFire("main_gun_once"));
        }
        if (keyHandler.shootRSB.isKeyDown()
                && !mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && player.getHeldItemMainhand().getItem() instanceof Item_Firearm
                && player.getHeldItemOffhand() == ItemStack.EMPTY
                && ((Item_Firearm) player.getHeldItemMainhand().getItem()).autofireSupport
                ) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunFire("main_gun_cont"));
        }

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