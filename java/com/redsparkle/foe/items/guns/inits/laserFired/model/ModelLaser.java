package com.redsparkle.foe.items.guns.inits.laserFired.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Created by hoijima on 20.01.17.
 */
public class ModelLaser extends ModelBase {
    public ModelRenderer model;

    public ModelLaser() {

        model = new ModelRenderer(this, 0, 0);
        model.addBox(1F, 1F, 1F, 1, 1, 1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glScalef(0.1F, 0.1F, 0.1F);
        model.render(f5);
    }
}