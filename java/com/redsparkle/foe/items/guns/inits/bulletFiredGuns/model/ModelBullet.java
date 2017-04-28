package com.redsparkle.foe.items.guns.inits.bulletFiredGuns.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelBullet extends ModelBase {
    public ModelRenderer model;

    public ModelBullet() {
        model = new ModelRenderer(this, 0, 0);

        model.addBox(3.5F, 3.5F, 3.5F, 1, 1, 1);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glScalef(0.1F, 0.1F, 0.1F);
        model.render(f3);
    }
}