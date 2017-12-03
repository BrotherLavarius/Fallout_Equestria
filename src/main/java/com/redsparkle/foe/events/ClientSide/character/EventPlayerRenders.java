package com.redsparkle.foe.events.ClientSide.character;

import com.redsparkle.api.Capability.Player.Render.IRenders;
import com.redsparkle.api.Capability.Player.Render.RenderProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.foe.playerrenderers.ArmorLayerRender;
import com.redsparkle.foe.playerrenderers.GunRender;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by NENYN on 2/12/2017.
 */
public class EventPlayerRenders {


    @SubscribeEvent
    public void onRenderEntity(RenderPlayerEvent.Pre event) {
        if (event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityOtherPlayerMP) {
            IRenders render = event.getEntityPlayer().getCapability(RenderProvider.RENDER_CAP, null);
            EntityPlayer player = event.getEntityPlayer();

            if (!render.getGunRender()) {
                event.getRenderer().addLayer(new GunRender(event.getRenderer().getRenderManager().getSkinMap().get(player)));
                render.setGunRender(true);
            }

            if (!render.getArmorRender()) {
                event.getRenderer().addLayer(new ArmorLayerRender(event.getRenderer().getRenderManager().getSkinMap().get(player)));
                render.setArmorRender(true);
            }

        }
    }

    @SubscribeEvent(receiveCanceled = true)
    public void renderPlayerRightClick(PlayerInteractEvent.RightClickEmpty event) {
        if (event.getItemStack().getItem() instanceof Item_Firearm) {
            //event.getEntityPlayer().swingProgressInt = 0;
        }
    }

    @SubscribeEvent(receiveCanceled = true)
    public void onWeaponCarry(RenderPlayerEvent.Post event) {
        if (event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityOtherPlayerMP) {
            EntityPlayer player = event.getEntityPlayer();
            if (player.getHeldItemMainhand().getItem() instanceof Item_Firearm) {
                event.isCanceled();
                event.getRenderer().getMainModel().rightArmPose.equals(ModelBiped.ArmPose.EMPTY);
            }
        }
    }
}
