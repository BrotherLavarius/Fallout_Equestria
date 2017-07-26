package com.redsparkle.foe.events.ServerSIdeONly;
import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateAmmoHolders;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
/**
 * Created by hoijima on 24.05.17.
 */
public class EventHandlerServerSidePre {
    //TODO: make a sync for guns
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        InventoryPlayer inv = e.player.inventory;
        IAdvInventory iadv = e.player.getCapability(IAdvProvider.Adv_Inv,null);
        for(int i = 0;i <= inv.getSizeInventory();i++){
            if (inv.getStackInSlot(i).getItem() instanceof Item_AmmoHolder && inv.getStackInSlot(i).hasCapability(AmmoFactoryProvider.AMMO_STORAGE,null)){
                IAmmoInterface iammo = inv.getStackInSlot(i).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(iammo.getAmmo(),iammo.getMaxAmmo(),i,0,0),(EntityPlayerMP) e.player);
            }
        }
        for(int g =0; g < iadv.getSlots();g++){
            if(iadv.getStackInSlot(g).getItem() instanceof Item_AmmoHolder && inv.getStackInSlot(g).hasCapability(AmmoFactoryProvider.AMMO_STORAGE,null)){
                IAmmoInterface iammo = inv.getStackInSlot(g).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(iammo.getAmmo(),iammo.getMaxAmmo(),g,1,0), (EntityPlayerMP) e.player);
            }
        }
        for(int a = 0;a <= inv.getSizeInventory();a++){
            if (inv.getStackInSlot(a).getItem() instanceof Item_Firearm && inv.getStackInSlot(a).hasCapability(GunFactoryProvider.GUN,null)){
                IGunInterface igun = inv.getStackInSlot(a).getCapability(GunFactoryProvider.GUN,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(igun.getAmmo(),igun.getMaxAmmo(),a,0,1),(EntityPlayerMP) e.player);
            }
        }
        for(int z =0; z < iadv.getSlots();z++){
            if(iadv.getStackInSlot(z).getItem() instanceof Item_Firearm && inv.getStackInSlot(z).hasCapability(GunFactoryProvider.GUN,null)){
                IGunInterface igun = inv.getStackInSlot(z).getCapability(GunFactoryProvider.GUN,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(igun.getAmmo(),igun.getMaxAmmo(),z,1,1), (EntityPlayerMP) e.player);
            }
        }
    }
}
