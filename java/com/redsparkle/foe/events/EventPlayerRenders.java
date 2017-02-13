package com.redsparkle.foe.events;

import com.redsparkle.foe.playerrenderers.GunRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by NENYN on 2/12/2017.
 */
public class EventPlayerRenders {

    public EntityPlayerSP player = Minecraft.getMinecraft().player;
    private boolean done = false;
    @SubscribeEvent
    public void onRenderEntity(RenderPlayerEvent.Pre event) {

        if(event.getEntity() instanceof EntityPlayer && !done) {
            EntityPlayer player = (EntityPlayer)event.getEntity();
            event.getRenderer().getRenderManager();
            //event.getRenderer().getMainModel().bipedHeadwear.addChild();
            event.getRenderer().addLayer(new GunRender(event.getRenderer().getRenderManager().getSkinMap().get(player)));
            //TODO: add this layer as child layer SYKA BLYAT
            done = true;
        }
    }

}
