package com.redsparkle.foe.playerrenderers;

import com.redsparkle.foe.items.armor.powered.init.ItemBody;
import com.redsparkle.foe.items.armor.powered.init.ItemHelmet;
import com.redsparkle.foe.items.guns.inits.ItemFirearm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static java.lang.Math.abs;

/**
 * Created by NENYN on 2/12/2017.
 */
public class ArmorLayerRender implements LayerRenderer<EntityLivingBase> {
    private final RenderPlayer playerRenderer;
    private Float yawCorrector = 0F;
    private Float rotationPitch = 0F;
    public ArmorLayerRender(RenderPlayer playerRendererIn) {

        this.playerRenderer = playerRendererIn;
    }




    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstackHead = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        Item itemHead = itemstackHead.getItem();

        ItemStack itemstackBody = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        Item itemBody = itemstackBody.getItem();
        Minecraft minecraft = Minecraft.getMinecraft();


        if (MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) >= 0.0F) {
            yawCorrector = MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y);
        } else if (MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) < 0.0F) {
            yawCorrector = abs(MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) + 360);
        }


        if (itemstackHead != null && itemHead instanceof ItemHelmet) {
            GlStateManager.pushMatrix();



            GlStateManager.translate(0.115F, 0.15F, 0.15F);
            GlStateManager.rotate(netHeadYaw, 0, 1.0F, 0);
            GlStateManager.rotate(headPitch, 1.0F, 0, 0);


            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstackHead, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
        if (itemstackBody != null && itemBody instanceof ItemBody) {
            GlStateManager.pushMatrix();


            GlStateManager.translate(0.280F, 0.65F, 0F);


            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstackBody, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}
