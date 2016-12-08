package com.redsparkle.foe.events;


import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsDefaultImpl;
import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessagePlayerProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.redsparkle.foe.Init.FOECapabilitiesInit.RADIATION_CAPABILITY;
import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandlerPre {


    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent.Entity event)
    {
        //Attach it! The resource location MUST be unique it's recommended that you tag it with your modid and what the cap is.
        if (!event.getEntity().hasCapability(RADIATION_CAPABILITY,null)) {
            event.addCapability(new ResourceLocation(MODID + ":Radiation_CAPABILITY"), new RadsFactoryProvider(new RadsDefaultImpl()));
        }
        
    }

    // LEFT HERE TO KILL MC GUI
    /*@SubscribeEvent(receiveCanceled=true)
    public void onEvent(RenderGameOverlayEvent.Pre event) {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().thePlayer;
        if (entityPlayerSP == null) return;  // just in case


        switch (event.getType()) {
            case HEALTH:
                event.setCanceled(true);
                break;
            case ARMOR:
                event.setCanceled(true);
                break;
            case EXPERIENCE:
                event.setCanceled(true);
            case FOOD:
                event.setCanceled(true);
                break;

            default: // If it's not one of the above cases, do nothing
                break;
        }
    }
    */
}
    
    