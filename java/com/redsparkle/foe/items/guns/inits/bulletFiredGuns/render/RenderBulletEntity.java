package com.redsparkle.foe.items.guns.inits.bulletFiredGuns.render;

import com.redsparkle.foe.items.guns.inits.bulletFiredGuns.EntityBullet;
import com.redsparkle.foe.items.guns.inits.bulletFiredGuns.model.ModelBullet;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBulletEntity extends Render<EntityBullet> {
    private static final ResourceLocation textures = new ResourceLocation(GlobalNames.Domain + ":textures/entities/BulletRender.png");

    public RenderBulletEntity(RenderManager renderManager) {
        super(renderManager);
    }

    public RenderBulletEntity(RenderManager renderManager, ModelBullet modelBullet, double v) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBullet entity) {
        return textures;
    }


//    private static final ResourceLocation textures = new ResourceLocation(GlobalNames.Domain + ":textures/entities/BulletRender.png");
//
//    public RenderBulletEntity(RenderManager rendermanager, ModelBullet modelBullet, double v) {
//        super(rendermanager);
//    }
//
//    @Override
//    protected ResourceLocation getEntityTexture(EntityBullet entity) {
//        return textures;
//    }
//
    @Override
    public void doRender(EntityBullet entity, double d, double d1, double d2, float f, float f1) {
        if (entity.ticksExisted < 1) {
            return;
        }

        this.bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90F - entity.prevRotationPitch - (entity.rotationPitch - entity.prevRotationPitch) * f1, 1.0F, 0.0F, 0.0F);
        ModelBase model = new ModelBullet();

        if (model != null) {
            model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }

        GL11.glPopMatrix();
    }

}
