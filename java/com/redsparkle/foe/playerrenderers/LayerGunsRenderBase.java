package com.redsparkle.foe.playerrenderers;


import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.items.guns.TenMM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by NENYN on 1/23/2017.
 */
@SideOnly(Side.CLIENT)
public  class LayerGunsRenderBase implements LayerRenderer<AbstractClientPlayer> {

    private final RenderPlayer renderPlayer;

    public LayerGunsRenderBase(RenderPlayer renderPlayer) {
        this.renderPlayer = renderPlayer;
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer abstractClientPlayer, float v, float v1, float v2, float v3, float v4, float v5, float v6) {
        ItemStack itemstack = abstractClientPlayer.getHeldItem(EnumHand.MAIN_HAND);
        Item item = itemstack.getItem();
        Minecraft minecraft = Minecraft.getMinecraft();
        if(itemstack != null ){
            GlStateManager.pushMatrix();
            minecraft.getItemRenderer().renderItem(abstractClientPlayer,itemstack, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}


//TODO: example https://github.com/coolAlias/ZeldaSwordSkills/blob/1.8/src/main/java/zeldaswordskills/client/render/entity/layers/LayerGenericHeldItem.java#L37

//