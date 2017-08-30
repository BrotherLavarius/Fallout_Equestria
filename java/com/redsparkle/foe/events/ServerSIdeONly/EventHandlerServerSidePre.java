package com.redsparkle.foe.events.ServerSIdeONly;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.FirtsTimeJoin.FTJFactoryProvider;
import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
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
import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Saddlebag_harness;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateAmmoHolders;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientTrigger_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.redsparkle.api.Capability.Player.level.LevelFactoryProvider.LEVEL_CAPABILITY;

/**
 * Created by hoijima on 24.05.17.
 */
public class EventHandlerServerSidePre {
    //TODO: make a sync for guns
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        InventoryPlayer inv = e.player.inventory;
        IAdvInventory iadv = e.player.getCapability(IAdvProvider.Adv_Inv,null);
        for(int i = 0; i <= inv.getSizeInventory(); i++){
            if (inv.getStackInSlot(i).getItem() instanceof Item_AmmoHolder && inv.getStackInSlot(i).hasCapability(AmmoFactoryProvider.AMMO_STORAGE,null)){
                IAmmoInterface iammo = inv.getStackInSlot(i).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(iammo.getAmmo(),iammo.getMaxAmmo(),i,0,0),(EntityPlayerMP) e.player);
            }
        }
        for(int g =0; g < iadv.getSlots(); g++){
            if(iadv.getStackInSlot(g).getItem() instanceof Item_AmmoHolder && inv.getStackInSlot(g).hasCapability(AmmoFactoryProvider.AMMO_STORAGE,null)){
                IAmmoInterface iammo = inv.getStackInSlot(g).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(iammo.getAmmo(),iammo.getMaxAmmo(),g,1,0), (EntityPlayerMP) e.player);
            }
        }
        for(int a = 0; a <= inv.getSizeInventory(); a++){
            if (inv.getStackInSlot(a).getItem() instanceof Item_Firearm && inv.getStackInSlot(a).hasCapability(GunFactoryProvider.GUN,null)){
                IGunInterface igun = inv.getStackInSlot(a).getCapability(GunFactoryProvider.GUN,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(igun.getAmmo(),igun.getMaxAmmo(),a,0,1),(EntityPlayerMP) e.player);
            }
        }
        for(int z = 0; z < iadv.getSlots(); z++){
            if(iadv.getStackInSlot(z).getItem() instanceof Item_Firearm && inv.getStackInSlot(z).hasCapability(GunFactoryProvider.GUN,null)){
                IGunInterface igun = inv.getStackInSlot(z).getCapability(GunFactoryProvider.GUN,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(igun.getAmmo(),igun.getMaxAmmo(),z,1,1), (EntityPlayerMP) e.player);
            }
        }



        if(e.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(5).getItem() instanceof Item_Saddlebag_harness){}
        else{
            e.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM,null).setStatus(false);
            main.simpleNetworkWrapper.sendTo(new MessageUpdateClientTrigger_Item(false), (EntityPlayerMP) e.player);
        }
    }

}
