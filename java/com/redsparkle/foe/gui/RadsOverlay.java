package com.redsparkle.foe.gui;


import com.redsparkle.foe.Init.FOECapabilitiesInit;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessagePlayerProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.client.FMLClientHandler;


/**
 * Created by NENYN on 14.11.2016.
 */

public class RadsOverlay extends Gui {

    EntityPlayer EntityPlayerSP = FMLClientHandler.instance().getClient().thePlayer;
    Integer radtext = EntityPlayerSP.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).getRadiation();
    String text = "Rads:";
    public RadsOverlay(Minecraft mc, EntityPlayer player){

        ScaledResolution res = new ScaledResolution(mc);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int strw = mc.fontRendererObj.getStringWidth("Pitch: -90.00");
        int strh = mc.fontRendererObj.FONT_HEIGHT;
        mc.fontRendererObj.drawStringWithShadow(text + Integer.toString(radtext),width-20-strw, height - 20 - strh, 0x00FFFFFF);
        MessagePlayerProperties messagePlayerProperties = new MessagePlayerProperties(player);
        System.out.println("Trying to send a message to server!");
        main.INSTANCE.sendToServer(messagePlayerProperties);
    }
}
