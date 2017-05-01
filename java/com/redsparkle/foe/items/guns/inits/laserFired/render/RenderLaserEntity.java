package com.redsparkle.foe.items.guns.inits.laserFired.render;

import com.redsparkle.foe.items.guns.inits.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.inits.laserFired.model.ModelLaser;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

/**
 * Created by hoijima on 20.01.17.
 */
public class RenderLaserEntity extends Render<EntityLaser> {
    private static final ResourceLocation textures = new ResourceLocation(GlobalNames.Domain + ":" + "textures/entities/bulletrender.png");

    public RenderLaserEntity(RenderManager rendermanager) {
        super(rendermanager);
        this.shadowSize = 0.5F;
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityLaser entity) {
        return textures;
    }

    @Override
    public void doRender(EntityLaser entity, double d, double d1, double d2, float f, float f1) {


        if (entity.ticksExisted < 1) {

            return;
        }

        this.bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glFog(1, FloatBuffer.allocate(23));
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90F - entity.prevRotationPitch - (entity.rotationPitch - entity.prevRotationPitch) * f1, 1.0F, 0.0F, 0.0F);

        ModelBase model = new ModelLaser();

        if (model != null) {
            model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }

        GL11.glPopMatrix();

    }

}
