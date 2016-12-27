package com.redsparkle.foe.gui;



import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;


/**
 * Created by NENYN on 14.11.2016.
 */

public class RadsOverlay extends Gui {
    private final static ResourceLocation RadiationBar = new ResourceLocation(main.MODID,
            "/textures/gui/rads_hud_overlay.png");

    private Minecraft mc;
    private EntityPlayerSP player = mc.thePlayer;
    public RadsOverlay(Minecraft mc) {
        this.mc = mc;
    }

    public void renderStatusBar(int screenWidth, int screenHeight) {
        Integer radtext = player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation();
        String text = "Rads:";
        int strw = mc.fontRendererObj.getStringWidth("Pitch: -90.00");
        int strh = mc.fontRendererObj.FONT_HEIGHT;
        mc.fontRendererObj.drawStringWithShadow(text + Integer.toString(radtext),screenWidth-20-strw, screenHeight - 20 - strh, 0x00FFFFFF);

    }
}
