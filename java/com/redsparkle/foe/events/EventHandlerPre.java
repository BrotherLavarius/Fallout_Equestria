package com.redsparkle.foe.events;


import com.redsparkle.foe.capa.RadsDefaultImpl;
import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.main;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.redsparkle.foe.capa.RadsFactoryProvider.RADIATION_CAPABILITY;


/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandlerPre {


    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent.Entity event)
    {
        //Attach it! The resource location MUST be unique it's recommended that you tag it with your modid and what the cap is.
        if (!event.getEntity().hasCapability(RADIATION_CAPABILITY,null)) {
            event.addCapability(new ResourceLocation(main.MODID + ":Radiation_CAPABILITY"), new RadsFactoryProvider(new RadsDefaultImpl()));
        }

    }
}
    
    