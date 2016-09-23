package com.redsparkle.foe.gui;

import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.main;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiHealthBar extends GuiIngame {
	private static final ResourceLocation texturepath = new ResourceLocation(main.MODID.toLowerCase(), "textures/gui/bar.png");
	private static String registryName;
	private Minecraft mc;
	private MinecraftServer server;
	private GuiHealthBar instance;
	private int pRads;
	private int RADS_CAP = 500;
	public GuiHealthBar(Minecraft mc, MinecraftServer minecraftServer){
		super(mc);
		this.mc = mc;
	}

	public static GuiHealthBar getInstance(){
		return null;
	}

	public void setInstance(GuiHealthBar instance){
		this.instance = instance;
	}

	public static void setRegistryName(String registryName) {
		GuiHealthBar.registryName = registryName;
	}

	public void setRads(int rads){
		pRads = rads;
	}

	@SubscribeEvent
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {

		if(this.mc.thePlayer.isInsideOfMaterial(Material.PORTAL))return;
		if(true){
		int xPos = 10;
		int yPos = 2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		FontRenderer fontrenderer = this.mc.fontRendererObj;
		this.mc.renderEngine.bindTexture(texturepath);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ScaledResolution scaledresolution = new ScaledResolution(this.mc);
		int k = scaledresolution.getScaledWidth();
		int l = scaledresolution.getScaledHeight();
		int rads;
		try {
			rads = server.getServer().getEntityWorld().getPlayerEntityByName(mc.thePlayer.getDisplayNameString()).getEntityWorld().getCapability(RadsFactoryProvider.RADIATION_CAPABILITY,null).getRadiation();
		}
		catch(NullPointerException e){
			rads = 0;
			System.out.println("NullPtrExeption");
		}
		int barCalc;
		
		barCalc = Math.round(RADS_CAP*rads/100);//calculates percentage of full
		
		this.drawTexturedModalRect(20, 150, 0, 0, 83, 9);
		
		if (barCalc < 500){
			this.drawTexturedModalRect(23,153,0,25,59,9);
			String s1 = Integer.toString(barCalc);
			 fontrenderer.drawStringWithShadow("Rads:", 0, 0, 5010157);
		}
		
		

		}
	}
}



//TODO: Rewrite according to https://github.com/TVoidS/MANA-Mod/blob/master/java/com/tvoids/firstmod/init/GuiBuffBar.java
//TODO: NO REWRITE LIKE THIS GUY https://minecraft.curseforge.com/projects/health-bar

//TODO : OR THIS ONE https://github.com/TheGreyGhost/MinecraftByExample/blob/master/src/main/java/minecraftbyexample/mbe40_hud_overlay/Notes.txt

