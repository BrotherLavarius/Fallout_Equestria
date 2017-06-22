package com.redsparkle.foe.items.guns.bulletFired;

import com.redsparkle.foe.items.guns.bulletFired.models.ModelBullet;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 19.06.17.выв
 */
public class RenderBullet extends Render {

    private static final ResourceLocation textures = new ResourceLocation(GlobalNames.Domain + ":textures/entities/bulletrender.png");
    private ModelBase model;

    public RenderBullet(RenderManager renderManager, ModelBullet modelBullet, double v) {
        super(renderManager);
        model = new ModelBullet();

    }


    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {

        GL11.glPushMatrix();
        this.bindEntityTexture(entity);
        bindTexture(textures);
        GL11.glTranslated(x, y - 1.25D, z);
        model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();

    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return textures;
    }
}
