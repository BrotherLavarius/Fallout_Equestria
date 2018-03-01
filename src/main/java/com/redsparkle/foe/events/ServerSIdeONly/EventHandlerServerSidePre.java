package com.redsparkle.foe.events.ServerSIdeONly;

import com.google.gson.JsonObject;
import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageAdvInv_SYNC_op;
import com.redsparkle.foe.network.UnifiedMessage;
import com.sun.media.jfxmedia.logging.Logger;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by hoijima on 24.05.17.
 */
public class EventHandlerServerSidePre {
    //TODO: make a sync for guns
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        InventoryPlayer inv = e.player.inventory;
        IAdvInventory iadv = e.player.getCapability(IAdvProvider.Adv_Inv, null);
        JsonObject message = new JsonObject();
        message.addProperty("type", "ammo_holder_udpate");

        JsonObject body = new JsonObject();


        for (int i = 0; i <= inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i).getItem() instanceof Item_AmmoHolder && inv.getStackInSlot(i).hasCapability(AmmoFactoryProvider.AMMO_STORAGE, null)) {
                IAmmoInterface iammo = inv.getStackInSlot(i).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);

                body.addProperty("ammo", iammo.getAmmo());
                body.addProperty("maxAmmo", iammo.getMaxAmmo());
                body.addProperty("slot", i);
                body.addProperty("invType", 0);
                body.addProperty("type", 0);
                message.add("details", body);

                main.simpleNetworkWrapper.sendTo(new UnifiedMessage(message), (EntityPlayerMP) e.player);
            }
        }
        for (int g = 0; g < iadv.getSlots(); g++) {
            if (iadv.getStackInSlot(g).getItem() instanceof Item_AmmoHolder && inv.getStackInSlot(g).hasCapability(AmmoFactoryProvider.AMMO_STORAGE, null)) {
                IAmmoInterface iammo = inv.getStackInSlot(g).getCapability(AmmoFactoryProvider.AMMO_STORAGE, null);

                body.addProperty("ammo", iammo.getAmmo());
                body.addProperty("maxAmmo", iammo.getMaxAmmo());
                body.addProperty("slot", g);
                body.addProperty("invType", 1);
                body.addProperty("type", 0);
                message.add("details", body);

                main.simpleNetworkWrapper.sendTo(new UnifiedMessage(message), (EntityPlayerMP) e.player);
            }
        }
        for (int a = 0; a <= inv.getSizeInventory(); a++) {
            if (inv.getStackInSlot(a).getItem() instanceof Item_Firearm && inv.getStackInSlot(a).hasCapability(GunFactoryProvider.GUN, null)) {
                IGunInterface igun = inv.getStackInSlot(a).getCapability(GunFactoryProvider.GUN, null);

                body.addProperty("ammo", igun.getAmmo());
                body.addProperty("maxAmmo", igun.getMaxAmmo());
                body.addProperty("slot", a);
                body.addProperty("invType", 0);
                body.addProperty("type", 1);
                message.add("details", body);

                main.simpleNetworkWrapper.sendTo(new UnifiedMessage(message), (EntityPlayerMP) e.player);



            }
        }
        for (int z = 0; z < iadv.getSlots(); z++) {
            if (iadv.getStackInSlot(z).getItem() instanceof Item_Firearm && inv.getStackInSlot(z).hasCapability(GunFactoryProvider.GUN, null)) {
                IGunInterface igun = inv.getStackInSlot(z).getCapability(GunFactoryProvider.GUN, null);

                body.addProperty("ammo", igun.getAmmo());
                body.addProperty("maxAmmo", igun.getMaxAmmo());
                body.addProperty("slot", z);
                body.addProperty("invType", 1);
                body.addProperty("type", 1);
                message.add("details", body);

                main.simpleNetworkWrapper.sendTo(new UnifiedMessage(message), (EntityPlayerMP) e.player);

            }
        }


//        if (e.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(5).getItem() instanceof Item_Saddlebag_harness) {
//
//        } else {
//            e.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).setStatus(false);
//            main.simpleNetworkWrapper.sendTo(new MessageUpdateClientTrigger_Item(false, e.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getInteraction()), (EntityPlayerMP) e.player);
//        }

        if (e.player.getEntityWorld().getTotalWorldTime() % 15000 == 0) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, ItemInit.scrap.size());
            Item item = ItemInit.scrap.get(randomNum);
            e.player.inventory.add(randomNum, new ItemStack(item));
            Logger.logMsg(Logger.INFO, "Added item to player: " + item.getUnlocalizedName());


        }

        Object[] listiners = ((WorldServer) e.player.world).getEntityTracker().getTrackingPlayers(e.player).toArray();

        //TODO: change this to events, this is ugly and may cause problems in future
        if (e.player.getEntityWorld().getTotalWorldTime() % 100 == 0) {
            for (int i = 0; i < listiners.length; i++) {

                main.simpleNetworkWrapper.sendTo(new MessageAdvInv_SYNC_op(e.player), (EntityPlayerMP) listiners[i]);

            }
        }


    }


}
