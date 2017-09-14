package com.redsparkle.foe.events.ClientSide;

import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.utils.RadioThreadManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by hoijima on 01.08.17.
 */
public class CommonEventHandler {
    @SubscribeEvent
    public void onExit(PlayerEvent.PlayerLoggedOutEvent e) {
        RadioThreadManager.StopPlayer();
    }


    @SubscribeEvent
    public void onDamageRender(LivingHurtEvent e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            if (e.getSource() == DamageSource.MAGIC) {
                e.setCanceled(false);
                return;
            }
        }
    }
    @SubscribeEvent
    public void onDamageRender(LivingAttackEvent e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            if (e.getSource() == DamageSource.MAGIC) {
                e.setCanceled(false);
                return;
            }
        }
    }

    @SubscribeEvent
    public void onINteractOnAir(PlayerInteractEvent.LeftClickBlock e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            if ((((EntityPlayer) e.getEntityLiving()).inventory.getCurrentItem().getItem() instanceof Item_Firearm)) {
                e.setCanceled(true);
                e.setUseBlock(Event.Result.DENY);
                e.setUseItem(Event.Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public void onINteractOnAir(PlayerInteractEvent.RightClickBlock e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            if ((((EntityPlayer) e.getEntityLiving()).inventory.getCurrentItem().getItem() instanceof Item_Firearm)) {
                e.setCanceled(true);
                e.setUseBlock(Event.Result.DENY);
                e.setUseItem(Event.Result.DENY);

            }
        }
    }

}
