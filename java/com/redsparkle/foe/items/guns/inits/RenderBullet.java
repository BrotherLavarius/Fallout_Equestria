package com.redsparkle.foe.items.guns.inits;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by hoijima on 19.06.17.
 */
public class RenderBullet extends Render<EntityBullet> {


    public RenderBullet(RenderManager renderManager) {
        super(renderManager);
        shadowSize = 0.0F;
    }


    public void doRender(@Nonnull EntityBullet entityBullet, double x, double y, double z, float rotationYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);


        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entityBullet, x, y, z, rotationYaw, partialTicks);
    }


    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBullet entityBullet) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
