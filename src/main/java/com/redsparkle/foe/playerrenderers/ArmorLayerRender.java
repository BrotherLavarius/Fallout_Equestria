package com.redsparkle.foe.playerrenderers;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.items.helpers.armor.ItemBody;
import com.redsparkle.api.items.helpers.armor.ItemHelmet;
import com.redsparkle.foe.items.saddlebags.Saddlebags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by NENYN on 2/12/2017.
 */
@SideOnly(Side.CLIENT)
public class ArmorLayerRender implements LayerRenderer<EntityLivingBase> {
    public ArmorLayerRender(RenderPlayer playerRendererIn) {
        RenderPlayer playerRenderer = playerRendererIn;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstackHead = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        Item itemHead = itemstackHead.getItem();
        ItemStack itemstackBody = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        Item itemBody = itemstackBody.getItem();

        ItemStack harnes = entitylivingbaseIn.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(5);
        Item itemHarness = harnes.getItem();

        Minecraft minecraft = Minecraft.getMinecraft();

        if (itemstackHead != ItemStack.EMPTY && itemHead instanceof ItemHelmet) {
            GlStateManager.pushMatrix();

            if (entitylivingbaseIn.isSneaking()) {
                GlStateManager.translate(0, 0.35F, -0.17F);
            } else {
                GlStateManager.translate(0F, 0F, 0F);
            }
            GlStateManager.rotate(netHeadYaw, 0, 1.0F, 0);
            if (headPitch >= 32.0) {
                GlStateManager.rotate(32, 1.0F, 0, 0);
            } else if (headPitch < -66.5) {
                GlStateManager.rotate(-66, 1.0F, 0, 0);
            } else {
                GlStateManager.rotate(headPitch, 1.0F, 0, 0);
            }
            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstackHead, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
        if (itemstackBody != ItemStack.EMPTY && itemBody instanceof ItemBody) {
            GlStateManager.pushMatrix();
            if (entitylivingbaseIn.isSneaking()) {
                GlStateManager.rotate(20, 1, 0, 0);
                GlStateManager.translate(0.280F, 0.85F, -0.40F);
            } else {
                GlStateManager.translate(0.280F, 0.65F, 0F);
            }
            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstackBody, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
        if (itemHarness != Items.AIR && itemHarness instanceof Saddlebags) {
            GlStateManager.pushMatrix();
            if (entitylivingbaseIn.isSneaking()) {
                GlStateManager.translate(0.280F, 0.85F, 0F);
            } else {
                GlStateManager.translate(0.280F, 0.65F, 0F);
            }
            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, harnes, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
