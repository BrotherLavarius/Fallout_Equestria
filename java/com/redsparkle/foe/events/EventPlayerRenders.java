package com.redsparkle.foe.events;

import com.redsparkle.foe.playerrenderers.GunRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by NENYN on 2/12/2017.
 */
public class EventPlayerRenders {

    public EntityPlayerSP player = Minecraft.getMinecraft().player;

    @SubscribeEvent
    public void onRenderEntity(RenderPlayerEvent.Pre event) {
        for(RenderPlayer render : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
            render.addLayer(new GunRender(render));

        }
    }

}
