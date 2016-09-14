package com.redsparkle.foe;


import com.redsparkle.foe.capa.RadsFactoryProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandler {


    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent.Entity event)
    {
        //Attach it! The resource location MUST be unique it's recomneded that you tag it with your modid and what the cap is.
        event.addCapability(new ResourceLocation(main.MODID+":Radiation_CAPABILITY"), new RadsFactoryProvider(event.getEntity()));
        
    }



}
