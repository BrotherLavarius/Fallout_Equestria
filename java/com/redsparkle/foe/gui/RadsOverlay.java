package com.redsparkle.foe.gui;



import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;


/**
 * Created by NENYN on 14.11.2016.
 */

public class RadsOverlay extends Gui {

    EntityPlayer EntityPlayerSP = FMLClientHandler.instance().getClient().thePlayer;
    //Integer radtext = EntityPlayerSP.getCapability(CapabilityInit.RADIATION_CAPABILITY, null).getRadiation();
    String text = "Rads:";
    public RadsOverlay(Minecraft mc, EntityPlayer player){

        ScaledResolution res = new ScaledResolution(mc);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int strw = mc.fontRendererObj.getStringWidth("Pitch: -90.00");
        int strh = mc.fontRendererObj.FONT_HEIGHT;
        mc.fontRendererObj.drawStringWithShadow(text /*+ Integer.toString(radtext)*/,width-20-strw, height - 20 - strh, 0x00FFFFFF);

    }
}
