package gui;

import org.lwjgl.opengl.GL11;

import com.redsparkle.foe.main;
import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsDefaultImpl;
import com.redsparkle.foe.capa.RadsFactoryProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHealthBar extends Gui {
	private Minecraft mc;
	private static final ResourceLocation texturepath = new ResourceLocation(main.MODID.toLowerCase(), "textures/gui/bar.png");
	public GuiHealthBar(Minecraft mc){
		super();
		this.mc = mc;
	}
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		// We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
		// will return true from isCancelable.  If you call event.setCanceled(true) in
		// that case, the portion of rendering which this event represents will be canceled.
		// We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
		// false and that the eventType represents the ExperienceBar event.
		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE)
		{
			return;
		}
		IRadiationCapability Radiation = mc.thePlayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null);
		
		if (Radiation == null || Radiation.getRadiation() == 0)
		{
			return;
		}
		int xPos = 2;
		int yPos = 2;
		// setting all color values to 1.0F will render the texture as it appears in your texture file
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		// Somewhere in Minecraft vanilla code it says to do this because of a lighting bug
		GL11.glDisable(GL11.GL_LIGHTING);
		
		// Bind your texture to the render engine
		this.mc.getTextureManager().bindTexture(texturepath);
		
		this.drawTexturedModalRect(xPos, yPos, 0,0, 83, 24);
		// Then draw the foreground; it's located just below the background in my
		// texture file, so it starts at x=0, y=4, is only 2 pixels thick and 50 length
		// Why y=4 and not y=5? Y starts at 0, so 0,1,2,3 = 4 pixels for the background
		
		// However, we want the length to be based on current mana, so we need a new variable:
		int manabarwidth = (int)(((float) Radiation.getRadiation() / 90 * 50));
		System.out.println("[GUI RADS] Current RADS bar width: " + manabarwidth);
		// Now we can draw our mana bar at yPos+1 so it centers in the background:
		this.drawTexturedModalRect(xPos +3, yPos, 0,25, manabarwidth, 9);
	}
}

