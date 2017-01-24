package com.redsparkle.foe.playerrenderers;


import com.redsparkle.foe.items.guns.inits.ItemFirearm;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by NENYN on 1/23/2017.
 */
@SideOnly(Side.CLIENT)
public abstract class LayerGunsRenderBase<M extends ModelBase> implements LayerRenderer
{
    private RenderLiving modelBase;

    public LayerGunsRenderBase(RenderPlayer renderer) {
        this.modelBase = modelBase;
        if (!(this.modelBase.getMainModel() instanceof IModel)) {
            throw new IllegalArgumentException("Model must implement IModelBiped!");
        }
    }

    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entity.getHeldItem(EnumHand.MAIN_HAND);
        if (itemstack.getItem() instanceof ItemFirearm){
            GlStateManager.pushMatrix();
            if (modelBase.getMainModel().isChild){
                float f7 = 0.5F;
                GlStateManager.translate(0.0F, 0.625F, 0.0F);
                GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
                GlStateManager.scale(f7, f7, f7);
            }
            GlStateManager.translate(-0.0625F, 0.4375F, 0.0625F);
            if (entity instanceof EntityPlayer && ((EntityPlayer)entity).fishEntity != null) {
                itemstack = new ItemStack(Items.FISHING_ROD, 0);
            }
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.getItemRenderer().renderItem(entity, itemstack, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
    }


    public boolean shouldCombineTextures() {
        return false;
    }
}


//TODO: example https://github.com/coolAlias/ZeldaSwordSkills/blob/1.8/src/main/java/zeldaswordskills/client/render/entity/layers/LayerGenericHeldItem.java#L37