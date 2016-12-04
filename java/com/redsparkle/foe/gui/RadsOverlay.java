package com.redsparkle.foe.gui;


import com.redsparkle.foe.Init.FOECapabilitiesInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;


import static com.redsparkle.foe.capa.RadsFactoryProvider.RADIATION_CAPABILITY;


/**
 * Created by NENYN on 14.11.2016.
 */

public class RadsOverlay extends Gui {

    EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    Integer radtext = player.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).getRadiation();
    String text = "Rads:";
    public RadsOverlay(Minecraft mc, EntityPlayer player){

        ScaledResolution res = new ScaledResolution(mc);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int strw = mc.fontRendererObj.getStringWidth("Pitch: -90.00");
        int strh = mc.fontRendererObj.FONT_HEIGHT;
        mc.fontRendererObj.drawStringWithShadow(text + Integer.toString(radtext),width-20-strw, height - 20 - strh, 0x00FFFFFF);


    }
}
